package com.prismamp.api.coupons.visa.dto;

import java.util.List;

import com.prismamp.api.coupons.visa.model.VisaCoupon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetSearchCouponsResponse {

	List<VisaCoupon> coupons;

}
