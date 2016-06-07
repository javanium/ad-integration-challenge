package com.adchallenge.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.service.EventService;

@RestController
public class SubscriptionNotificationController {

  public static final String INTEGRATION_PATH = "/api/v1/notifications";

  private static Logger LOGGER = Logger.getLogger(SubscriptionNotificationController.class);

  @Autowired
  private EventService eventService;

  /**
   * Subscription notifications unified end point
   *
   * @param eventUrl
   * @return
   */
  @RequestMapping(value = SubscriptionNotificationController.INTEGRATION_PATH, method = RequestMethod.GET)
  public EventNotificationResponse subscriptionEventProcessor(@RequestParam String eventUrl) {
    LOGGER.info(eventUrl);
    return eventService.processEvent(eventUrl);
  }

}