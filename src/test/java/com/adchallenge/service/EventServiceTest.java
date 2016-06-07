package com.adchallenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adchallenge.clients.AppDirectClient;
import com.adchallenge.domain.entities.UserAccount;
import com.adchallenge.domain.repository.UserAccountRepository;
import com.adchallenge.dto.EventNotificationFailedResponse;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.EventNotificationSuccessResponse;
import com.adchallenge.enums.EventNotificationErrorCode;
import com.adchallenge.enums.EventType;
import com.adchallenge.service.dataprovider.EventNotificationDataProvider;
import com.adchallenge.service.dataprovider.UserAccountDataProvider;

public class EventServiceTest {

  @Mock
  private AppDirectClient adClient;

  @Mock
  private UserAccountRepository userAccountRepository;

  private EventService eventService;

  @Before
  public void initMethods() {
    MockitoAnnotations.initMocks(this);
    eventService = new EventService(adClient, userAccountRepository);
  }

  @Test
  public void processEvent_EventTypeSubscriptionOrder_CorrectProcessing() {
    String eventUrl = "dummyOrder";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationSuccessResponse("dummy-account",
        "Subscription created successfully");
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_ORDER, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(UserAccountDataProvider.getUserAccount()).when(userAccountRepository)
        .save(Mockito.any(UserAccount.class));
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findByUuid(Mockito.any());
    Mockito.verify(userAccountRepository).save(Mockito.any(UserAccount.class));
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeSubscriptionOrderUserAlreadyExists_CorrectProcessing() {
    String eventUrl = "dummyOrder";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationFailedResponse(
        "Subscription creation failed", EventNotificationErrorCode.USER_ALREADY_EXISTS);
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_ORDER, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(UserAccountDataProvider.getUserAccount()).when(userAccountRepository).findByUuid(Mockito.any());
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findByUuid(Mockito.any());
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeSubscriptionChangeAndUserFound_CorrectProcessing() {
    String eventUrl = "dummyChange";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationSuccessResponse("dummy-account",
        "Subscription updated successfully");
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_CHANGE, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(UserAccountDataProvider.getUserAccount()).when(userAccountRepository).findOne(Mockito.any());
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findOne(Mockito.any());
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeSubscriptionChangeAndUserNotFound_CorrectProcessing() {
    String eventUrl = "dummyChange";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationFailedResponse(
        "Subscription update failed", EventNotificationErrorCode.ACCOUNT_NOT_FOUND);
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_CHANGE, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(null).when(userAccountRepository).findOne(Mockito.any());
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findOne(Mockito.any());
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeSubscriptionCancelAndUserFound_CorrectProcessing() {
    String eventUrl = "dummyCancel";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationSuccessResponse("dummy-account",
        "Subscription cancelled");
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_CANCEL, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(UserAccountDataProvider.getUserAccount()).when(userAccountRepository).findOne(Mockito.any());
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findOne(Mockito.any());
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeSubscriptionCancelAndUserNotFound_CorrectProcessing() {
    String eventUrl = "dummyCancel";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationFailedResponse("Cancel failed",
        EventNotificationErrorCode.ACCOUNT_NOT_FOUND);
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_CANCEL, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    Mockito.doReturn(null).when(userAccountRepository).findOne(Mockito.any());
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Mockito.verify(userAccountRepository).findOne(Mockito.any());
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeNotIntegrated_CorrectProcessing() {
    String eventUrl = "dummyNotice";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationFailedResponse(
        "API:SUBSCRIPTION_NOTICE is valid but not integrated yet", EventNotificationErrorCode.BINDING_NOT_FOUND);
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.SUBSCRIPTION_NOTICE, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

  @Test
  public void processEvent_EventTypeUnknown_CorrectProcessing() {
    String eventUrl = "dummyUnknown";
    EventNotificationResponse expectedEventNotificationResponse = new EventNotificationFailedResponse("API:UNKNOWN is invalid",
        EventNotificationErrorCode.BINDING_NOT_FOUND);
    Mockito.doReturn(EventNotificationDataProvider.getEventNotification(EventType.UNKNOWN, "dummy-account"))
        .when(adClient).getEventDetails(eventUrl);
    EventNotificationResponse actualEventNotificationResponse = eventService.processEvent(eventUrl);
    Mockito.verify(adClient).getEventDetails(eventUrl);
    Assert.assertEquals(expectedEventNotificationResponse.toString(), actualEventNotificationResponse.toString());
  }

}
