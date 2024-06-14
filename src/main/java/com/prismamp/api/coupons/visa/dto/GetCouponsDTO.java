package com.prismamp.api.coupons.visa.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.prismamp.api.coupons.visa.model.ECurrency;
import com.prismamp.api.coupons.visa.model.EFilterDate;
import com.prismamp.api.coupons.visa.model.EOrderBy;
import com.prismamp.api.coupons.visa.model.ETypeOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCouponsDTO {

	String cardNumber;
	Long accountNumber;
	String authorizationCode;
	String merchantName;
	String arn;
	BigDecimal amount;
	Long transactionReceipt;
	Long firstsix;
	Long lastfour;
	Date dateFrom;
	Date dateTo;
	ECurrency currency;
	EOrderBy orderBy;
	ETypeOrder typeOrder;
	EFilterDate filterDate;

}
