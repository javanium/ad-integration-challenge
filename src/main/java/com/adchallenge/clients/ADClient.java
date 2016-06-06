package com.adchallenge.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adchallenge.config.ADOauthConfig;
import com.adchallenge.dto.event.Event;

@Component
public class ADClient {

  @Autowired
  private ADOauthConfig adConfig;

  private RestTemplate restTemplate = new RestTemplate();

  public Event getEventDetails(String eventUrl) {
    return restTemplate.getForObject(eventUrl, Event.class);
  }

}
