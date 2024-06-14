package com.prismamp.api.coupons.visa.services;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.api.coupons.visa.dto.GetCouponsDTO;
import com.prismamp.api.coupons.visa.exceptions.ErrorResponse.ETypeError;
import com.prismamp.api.coupons.visa.exceptions.ErrorResponse.ExceptionResponse;
import com.prismamp.api.coupons.visa.model.EFilterDate;
import com.prismamp.api.coupons.visa.model.EOrderBy;
import com.prismamp.api.coupons.visa.model.ETypeOrder;
import com.prismamp.api.coupons.visa.model.VisaCoupon;
import com.prismamp.api.coupons.visa.repository.ICouponRepository;
import com.prismamp.api.coupons.visa.repository.IJobService;

@Service
public class VisaCouponService {
	
    @Autowired
    private IJobService iJobService;

	@Autowired
	private ICouponRepository couponRepository;

	public List<VisaCoupon> getCoupons(GetCouponsDTO findObject) throws ExceptionResponse {

		if (findObject.getAccountNumber() == null && findObject.getCardNumber() == null)
			throw new ExceptionResponse(ETypeError.BAD_REQUEST, "Invalid params.");
		
		if(findObject.getCardNumber() != null) {
			try {
				findObject.setCardNumber(iJobService.encrypt(findObject.getCardNumber()));
			} catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException
					| IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException
					| BadPaddingException e) {
				System.out.println(e);
			}
		}

		Comparator<VisaCoupon> comparator = null;

		if (findObject.getOrderBy() != null) {

			if (findObject.getOrderBy() == EOrderBy.PRESENTATION_DATE)
				comparator = Comparator.comparing(VisaCoupon::getPayment_date);
			if (findObject.getOrderBy() == EOrderBy.MOVEMENT_DATE)
				comparator = Comparator.comparing(VisaCoupon::getPurchase_date);

			if (comparator != null && findObject.getTypeOrder() == ETypeOrder.DESC)
				comparator = comparator.reversed();
		}

		EFilterDate filterDate = findObject.getFilterDate();
		
		
	    if(findObject.getDateTo() == null && findObject.getDateFrom() == null) {

         Calendar c = Calendar.getInstance();
         Date now = c.getTime();
         c.add(Calendar.DATE, -90);
         Date calculo = c.getTime();
         	    
 	    java.sql.Date DateFrom = new java.sql.Date(now.getTime());
	    
 	    java.sql.Date DateTo = new java.sql.Date(calculo.getTime());

 	    findObject.setDateFrom(DateFrom);
 	   
 	    findObject.setDateTo(DateTo); 
         }

		List<VisaCoupon> coupons = couponRepository.findCoupon(findObject.getAccountNumber(),
				findObject.getCardNumber(), findObject.getAuthorizationCode(), findObject.getMerchantName(),
				findObject.getArn(), findObject.getAmount(),
				findObject.getCurrency() != null ? findObject.getCurrency().getName() : null,
				findObject.getFirstsix(),findObject.getLastfour(),findObject.getTransactionReceipt(),
				filterDate == EFilterDate.MOVEMENT_DATE ? findObject.getDateFrom() : null,
				filterDate == EFilterDate.MOVEMENT_DATE ? findObject.getDateTo() : null,
				filterDate == EFilterDate.PRESENTATION_DATE ? findObject.getDateFrom() : null,
				filterDate == EFilterDate.PRESENTATION_DATE ? findObject.getDateTo() : null);

		if (comparator != null)
			return coupons.stream().sorted(comparator).toList();
		else
			coupons.forEach(coupon -> {
			    String decryptedCardNumber = "";
				try {
					decryptedCardNumber = iJobService.decryptString(coupon.getCard_number());
				    coupon.setCard_number(decryptedCardNumber);
				} catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException
						| IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException
						| BadPaddingException e) {
					System.out.println(e);
				}
			});
		
		return coupons;
	}

}
