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
public class SubscriptionChangeEventProcessor implements EventProcessor {

	private UserAccountRepository userAccountRepository;

	public SubscriptionChangeEventProcessor() {
	}

	public SubscriptionChangeEventProcessor(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public EventNotificationResponse process(Event event) {
		UserAccount userAccount = userAccountRepository.findOne(event.getPayload().getAccount().getAccountIdentifier());

		if (userAccount != null) {
			userAccount.setOrder(event.getPayload().getOrder());
			userAccountRepository.save(userAccount);
		} else {
			return new EventNotificationFailedResponse("Subscription update failed",
			    EventNotificationErrorCode.ACCOUNT_NOT_FOUND);
		}
		
		return new EventNotificationSuccessResponse(userAccount.getAccountIdentifier(),
		    "Subscription updated successfully");
	}
}
