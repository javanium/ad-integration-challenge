package com.adchallenge.domain.entities;

import org.springframework.data.annotation.Id;

import com.adchallenge.dto.event.Creator;
import com.adchallenge.dto.event.payload.order.Order;
import com.adchallenge.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserAccount {

	@Id
	private String accountIdentifier;

	private String uuid;

	private String openId;

	private String email;

	private String firstName;

	private String lastName;

	private String language;

	private AccountStatus status;

	private Order order;

	public UserAccount() {
		super();
	}

	public UserAccount(Creator creator, Order order) {
		super();
		// this.accountIdentifier = creator.getFirstName() + "-" +
		// creator.getLastName()
		// + ThreadLocalRandom.current().nextInt(0, 100 + 1);
		this.accountIdentifier = "dummy-account";
		this.uuid = creator.getUuid();
		this.openId = creator.getOpenId();
		this.email = creator.getEmail();
		this.firstName = creator.getFirstName();
		this.lastName = creator.getLastName();
		this.language = creator.getLanguage();
		this.status = AccountStatus.INITIALIZED;
		this.order = order;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
