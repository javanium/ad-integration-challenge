package com.adchallenge.service.dataprovider;

import com.adchallenge.domain.entities.UserAccount;

public class UserAccountDataProvider {

  public static UserAccount getUserAccount() {
    UserAccount userAccount = new UserAccount();
    userAccount.setAccountIdentifier("dummy-account");
    userAccount.setEmail("dummy-email");
    return userAccount;
  }

}
