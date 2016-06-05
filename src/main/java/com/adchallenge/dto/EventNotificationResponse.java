package com.adchallenge.dto;

public class EventNotificationResponse {

	private Boolean success;

	private String message;

	public EventNotificationResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}