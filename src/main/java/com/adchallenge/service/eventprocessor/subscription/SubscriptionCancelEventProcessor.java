package com.adchallenge.service.eventprocessor.subscription;

import org.springframework.stereotype.Service;

import com.adchallenge.domain.entities.UserAccount;
import com.adchallenge.domain.repository.UserAccountRepository;
import com.adchallenge.dto.EventNotificationFailedResponse;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.EventNotificationSuccessResponse;
import com.adchallenge.dto.event.Event;
import com.adchallenge.enums.EventNotificationErrorCode;
import com.adchallenge.service.eventprocessor.EventProcessor;

@Service
public class SubscriptionCancelEventProcessor implements EventProcessor {

	private UserAccountRepository userAccountRepository;

	public SubscriptionCancelEventProcessor() {
	}

	public SubscriptionCancelEventProcessor(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public EventNotificationResponse process(Event event) {
		EventNotificationResponse eventNotificationResponse;
		UserAccount userAccount = userAccountRepository.findOne(event.getPayload().getAccount().getAccountIdentifier());

		if (userAccount != null) {
			userAccount.setOrder(null);
			userAccountRepository.save(userAccount);
			eventNotificationResponse = new EventNotificationSuccessResponse(userAccount.getAccountIdentifier(),
			    "Subscription cancelled");
		} else {
			eventNotificationResponse = new EventNotificationFailedResponse("Cancel failed",
			    EventNotificationErrorCode.ACCOUNT_NOT_FOUND);
		}
		return eventNotificationResponse;
	}

}
