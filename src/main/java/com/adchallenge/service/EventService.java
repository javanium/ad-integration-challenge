package com.adchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adchallenge.clients.AppDirectClient;
import com.adchallenge.domain.repository.UserAccountRepository;
import com.adchallenge.dto.EventNotificationFailedResponse;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.event.Event;
import com.adchallenge.enums.EventNotificationErrorCode;
import com.adchallenge.service.eventprocessor.EventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionCancelEventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionChangeEventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionOrderEventProcessor;

@Service
public class EventService {

  private AppDirectClient adClient;

  private UserAccountRepository userAccountRepository;

  @Autowired
  public EventService(AppDirectClient adClient, UserAccountRepository userAccountRepository) {
    this.adClient = adClient;
    this.userAccountRepository = userAccountRepository;
  }

  /**
   * Process the event according to the event type
   *
   * @param eventUrl
   * @return EventNotificationResponse
   */
  public EventNotificationResponse processEvent(String eventUrl) {
    EventProcessor eventProcessor;
    Event eventDetails = adClient.getEventDetails(eventUrl);
    switch (eventDetails.getType()) {
    case SUBSCRIPTION_ORDER:
      eventProcessor = new SubscriptionOrderEventProcessor(userAccountRepository);
      break;
    case SUBSCRIPTION_CHANGE:
      eventProcessor = new SubscriptionChangeEventProcessor(userAccountRepository);
      break;
    case SUBSCRIPTION_CANCEL:
      eventProcessor = new SubscriptionCancelEventProcessor(userAccountRepository);
      break;
    case SUBSCRIPTION_NOTICE:
    case USER_ASSIGNMENT:
    case USER_UNASSIGNMENT:
    case USER_UPDATED:
      return new EventNotificationFailedResponse("API:" + eventDetails.getType() + " is valid but not integrated yet",
          EventNotificationErrorCode.BINDING_NOT_FOUND);
    default:
      return new EventNotificationFailedResponse("API:" + eventDetails.getType() + " is invalid",
          EventNotificationErrorCode.BINDING_NOT_FOUND);
    }

    return eventProcessor.process(eventDetails);
  }

}
