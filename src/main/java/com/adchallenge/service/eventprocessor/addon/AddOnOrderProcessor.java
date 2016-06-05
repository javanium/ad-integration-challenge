package com.adchallenge.service.eventprocessor.addon;

import org.springframework.stereotype.Service;

import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.EventNotificationSuccessResponse;
import com.adchallenge.dto.event.Event;
import com.adchallenge.service.eventprocessor.EventProcessor;

@Service
public class AddOnOrderProcessor implements EventProcessor {

	@Override
	public EventNotificationResponse process(Event event) {
		return new EventNotificationSuccessResponse("accountIdentifierTest", "AddOnOrderProcessor successfully");
	}

}
