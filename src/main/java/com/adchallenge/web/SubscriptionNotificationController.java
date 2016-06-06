package com.adchallenge.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.service.EventService;

@RestController
public class SubscriptionNotificationController {

	private static Logger LOGGER = Logger.getLogger(SubscriptionNotificationController.class);

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/subscription_notifications", method = RequestMethod.GET)
	public EventNotificationResponse subscriptionEventProcessor(@RequestParam String eventUrl,
	    @RequestHeader String authorization) throws Exception {
		LOGGER.info(eventUrl);
		LOGGER.info(authorization);
		return eventService.processEvent(eventUrl);
	}

}