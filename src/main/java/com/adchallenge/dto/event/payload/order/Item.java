package com.adchallenge.dto.event.payload.order;

import com.adchallenge.enums.ItemUnit;

public class Item {

	private ItemUnit unit;

	private int quantity;

	public ItemUnit getUnit() {
		return unit;
	}

	public void setUnit(ItemUnit unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
