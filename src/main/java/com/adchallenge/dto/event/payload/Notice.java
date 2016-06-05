package com.adchallenge.dto.event.payload;

import com.adchallenge.enums.NoticeType;

public class Notice {
	
	private NoticeType type;

	public NoticeType getType() {
		return type;
	}

	public void setType(NoticeType type) {
		this.type = type;
	}

}
