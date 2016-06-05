package com.adchallenge.dto.event.payload;

import com.adchallenge.dto.event.payload.order.Order;

public class Payload {

	// private User user;

	private Company company;

	private Account account;

	// private AddonInstance addonInstance;

	// private AddonBinding addonBinding;

	private Order order;

	private Notice notice;

	private Configuration configuration;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
