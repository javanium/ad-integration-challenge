package com.adchallenge.service.eventprocessor.subscription;

import org.springframework.stereotype.Service;

import com.adchallenge.domain.entities.UserAccount;
import com.adchallenge.domain.repository.UserAccountRepository;
import com.adchallenge.dto.EventNotificationFailedResponse;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.EventNotificationSuccessResponse;
import com.adchallenge.dto.event.Creator;
import com.adchallenge.dto.event.Event;
import com.adchallenge.dto.event.payload.order.Order;
import com.adchallenge.enums.EventNotificationErrorCode;
import com.adchallenge.service.eventprocessor.EventProcessor;

@Service
public class SubscriptionOrderEventProcessor implements EventProcessor {

	private UserAccountRepository userAccountRepository;

	public SubscriptionOrderEventProcessor() {
	}

	public SubscriptionOrderEventProcessor(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public EventNotificationResponse process(Event event) {
		UserAccount userAccount;
		Creator creator = event.getCreator();
		Order order = event.getPayload().getOrder();
		if (userAccountRepository.findByUuid(creator.getUuid()) == null) {
			userAccount = new UserAccount(creator, order);
			userAccount = userAccountRepository.save(userAccount);
		} else {
			return new EventNotificationFailedResponse("Subscription creation failed",
			    EventNotificationErrorCode.USER_ALREADY_EXISTS);
		}
		return new EventNotificationSuccessResponse(userAccount.getAccountIdentifier(),
		    "Subscription created successfully");
	}

}
