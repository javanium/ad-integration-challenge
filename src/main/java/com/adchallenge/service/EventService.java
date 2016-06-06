package com.adchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adchallenge.clients.ADClient;
import com.adchallenge.domain.repository.UserAccountRepository;
import com.adchallenge.dto.EventNotificationFailedResponse;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.event.Event;
import com.adchallenge.enums.EventNotificationErrorCode;
import com.adchallenge.service.eventprocessor.EventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionCancelEventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionChangeEventProcessor;
import com.adchallenge.service.eventprocessor.subscription.SubscriptionOrderEventProcessor;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@Service
public class EventService {

  private ADClient adClient;

  private UserAccountRepository userAccountRepository;

  @Autowired
  public EventService(ADClient adClient, UserAccountRepository userAccountRepository) {
    this.adClient = adClient;
    this.userAccountRepository = userAccountRepository;
  }

  /**
   *
   * @param eventUrl
   * @return
   * @throws OAuthCommunicationException
   * @throws OAuthExpectationFailedException
   * @throws OAuthMessageSignerException
   * @throws Exception
   */
  public EventNotificationResponse processEvent(String eventUrl)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    EventProcessor eventProcessor;
    EventNotificationResponse eventNotificationResponse;
    Event eventDetails = adClient.getEventDetails(eventUrl);

    switch (eventDetails.getType()) {
    case SUBSCRIPTION_ORDER:
      eventProcessor = new SubscriptionOrderEventProcessor(userAccountRepository);
      eventNotificationResponse = eventProcessor.process(eventDetails);
      break;
    case SUBSCRIPTION_CHANGE:
      eventProcessor = new SubscriptionChangeEventProcessor(userAccountRepository);
      eventNotificationResponse = eventProcessor.process(eventDetails);
      break;
    case SUBSCRIPTION_CANCEL:
      eventProcessor = new SubscriptionCancelEventProcessor(userAccountRepository);
      eventNotificationResponse = eventProcessor.process(eventDetails);
      break;
    case SUBSCRIPTION_NOTICE:
    case USER_ASSIGNMENT:
    case USER_UNASSIGNMENT:
    case USER_UPDATED:
      eventNotificationResponse = new EventNotificationFailedResponse(
          "API:" + eventDetails.getType() + " is valid but not integrated yet",
          EventNotificationErrorCode.BINDING_NOT_FOUND);
      break;
    default:
      eventNotificationResponse = new EventNotificationFailedResponse("API:" + eventDetails.getType() + " is invalid",
          EventNotificationErrorCode.BINDING_NOT_FOUND);
      break;
    }
    return eventNotificationResponse;
  }

}
