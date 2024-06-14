package com.prismamp.api.coupons.visa.model;

import lombok.Getter;

@Getter
public enum ECurrency {
	ARS("ARS"), USD("USD");

	private String name;

	ECurrency(String name) {
		this.name = name;
	}

}
