package com.adchallenge.dto.event.payload.order;

import java.util.List;

import com.adchallenge.enums.OrderEditionCode;
import com.adchallenge.enums.OrderPricingDuration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Order {

	private OrderEditionCode editionCode;

	private String addonOfferingCode;

	private OrderPricingDuration pricingDuration;

	private List<Item> items;

	public OrderEditionCode getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(OrderEditionCode editionCode) {
		this.editionCode = editionCode;
	}

	public void setPricingDuration(OrderPricingDuration pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public String getAddonOfferingCode() {
		return addonOfferingCode;
	}

	public void setAddonOfferingCode(String addonOfferingCode) {
		this.addonOfferingCode = addonOfferingCode;
	}

	public OrderPricingDuration getPricingDuration() {
		return pricingDuration;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
