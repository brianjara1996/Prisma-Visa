package com.prismamp.api.coupons.visa.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prismamp.api.coupons.visa.dto.GetCouponsDTO;
import com.prismamp.api.coupons.visa.dto.GetSearchCouponsResponse;
import com.prismamp.api.coupons.visa.exceptions.ErrorResponse;
import com.prismamp.api.coupons.visa.exceptions.ErrorResponse.ExceptionResponse;
import com.prismamp.api.coupons.visa.generic.Utils;
import com.prismamp.api.coupons.visa.services.VisaCouponService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/visa")
public class SearchController {
	

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private VisaCouponService visaCouponService;	

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public GetSearchCouponsResponse getCoupon(@RequestParam Map<String, Object> allRequestParams) throws ExceptionResponse {

		GetCouponsDTO findObject = new GetCouponsDTO();
		Utils.mapObject(findObject, allRequestParams, GetCouponsDTO.class);

		return new GetSearchCouponsResponse(visaCouponService.getCoupons(findObject));
	}	

	@ExceptionHandler({ ExceptionResponse.class })
	public ResponseEntity<ErrorResponse> handleException(ExceptionResponse e) {
		return ResponseEntity.status(e.getError().getStatus()).body(new ErrorResponse(e, request));
	}
}