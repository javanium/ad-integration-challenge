package com.adchallenge.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;

import com.adchallenge.dto.event.Event;

/**
 * AppDirect API Client
 */
@Component
public class AppDirectClient {

  @Autowired
  OAuthRestTemplate appDirectRestTemplate;

  /**
   * Fetch event details from the passed eventUrl
   *
   * @param eventUrl
   * @return Event data
   */
  public Event getEventDetails(String eventUrl) {
    return appDirectRestTemplate.getForObject(eventUrl, Event.class);
  }

}
