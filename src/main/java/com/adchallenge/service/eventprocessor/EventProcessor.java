package com.adchallenge.service.eventprocessor;

import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.event.Event;

public interface EventProcessor {
	
	EventNotificationResponse process(Event event);

}
