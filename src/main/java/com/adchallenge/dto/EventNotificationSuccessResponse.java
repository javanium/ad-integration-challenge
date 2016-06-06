package com.adchallenge.dto;

public class EventNotificationSuccessResponse extends EventNotificationResponse {

  private String accountIdentifier;

  public EventNotificationSuccessResponse(String accountIdentifier, String message) {
    super(true, message);
    this.accountIdentifier = accountIdentifier;
  }

  public String getAccountIdentifier() {
    return accountIdentifier;
  }

  public void setAccountIdentifier(String accountIdentifier) {
    this.accountIdentifier = accountIdentifier;
  }

  @Override
  public String toString() {
    return "EventNotificationSuccessResponse [accountIdentifier=" + accountIdentifier + ", getMessage()=" + getMessage()
        + ", getSuccess()=" + getSuccess() + "]";
  }

}