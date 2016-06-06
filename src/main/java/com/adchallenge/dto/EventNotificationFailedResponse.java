package com.adchallenge.dto;

import com.adchallenge.enums.EventNotificationErrorCode;

public class EventNotificationFailedResponse extends EventNotificationResponse {

  private EventNotificationErrorCode errorCode;

  public EventNotificationFailedResponse(String message, EventNotificationErrorCode errorCode) {
    super(false, message);
    this.errorCode = errorCode;
  }

  public EventNotificationErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(EventNotificationErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String toString() {
    return "EventNotificationFailedResponse [errorCode=" + errorCode + ", getMessage()=" + getMessage()
        + ", getSuccess()=" + getSuccess() + "]";
  }
}