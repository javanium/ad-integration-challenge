package com.adchallenge.service.dataprovider;

import com.adchallenge.dto.event.Creator;
import com.adchallenge.dto.event.Event;
import com.adchallenge.dto.event.Marketplace;
import com.adchallenge.dto.event.payload.Account;
import com.adchallenge.dto.event.payload.Payload;
import com.adchallenge.enums.EventType;
import com.adchallenge.enums.Flag;

public class EventNotificationDataProvider {

  public static Event getEventNotification(EventType eventType, String accountIdentifier) {
    Event eventDetails = new Event();
    eventDetails.setApplicationUuid("uuid");
    eventDetails.setCreator(new Creator());
    eventDetails.setFlag(Flag.STATELESS);
    eventDetails.setMarketplace(new Marketplace());
    Payload payload = new Payload();
    Account account = new Account();
    account.setAccountIdentifier(accountIdentifier);
    payload.setAccount(account);
    eventDetails.setPayload(payload);
    eventDetails.setReturnUrl("returnUrl");
    eventDetails.setType(eventType);
    return eventDetails;
  }

}
