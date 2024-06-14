package com.prismamp.api.coupons.visa.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.prismamp.api.coupons.visa.exceptions.ErrorResponse.ETypeError;
import com.prismamp.api.coupons.visa.exceptions.ErrorResponse.ExceptionResponse;
import com.prismamp.api.coupons.visa.model.ECurrency;
import com.prismamp.api.coupons.visa.model.EFilterDate;
import com.prismamp.api.coupons.visa.model.EOrderBy;
import com.prismamp.api.coupons.visa.model.ETypeOrder;

public final class Utils {

	static Logger logger = Logger.getLogger(Utils.class.getName());

	public static <T> void mapObject(T instance, Map<String, Object> input, Class<T> _class) throws ExceptionResponse {

		Map<String, Method> methods = Arrays.asList(_class.getMethods()).stream()
				.filter(m -> m.getName().contains("set")).collect(Collectors.toMap(Method::getName, e -> e));

		for (Entry<String, Method> entry : methods.entrySet()) {
			try {
				String keyInputPre = entry.getKey();
				String keyInput = String.valueOf(keyInputPre.charAt(3)).toLowerCase()
						+ keyInputPre.substring(4, keyInputPre.length());
				if (input.containsKey(keyInput) && entry.getValue() != null)
					entry.getValue().invoke(instance, autoCast(entry.getValue(), input.get(keyInput)));
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	private static Object autoCast(Method method, Object value) throws ExceptionResponse {
		String key = method.getParameterTypes()[0].toString();
		try {

			switch (key) {
			case "class java.lang.Long": {
				return Long.valueOf(String.valueOf(value));
			}
			case "class java.lang.String": {
				return String.valueOf(value);
			}
			case "class java.lang.Float": {
				return Float.valueOf(String.valueOf(value));
			}
			case "class java.math.BigDecimal": {
				return BigDecimal.valueOf(Double.valueOf(String.valueOf(value)));
			}
			case "class com.prismamp.api.coupons.visa.model.EOrderBy": {
				return EOrderBy.valueOf(String.valueOf(value));
			}
			case "class com.prismamp.api.coupons.visa.model.ETypeOrder": {
				return ETypeOrder.valueOf(String.valueOf(value));
			}
			case "class com.prismamp.api.coupons.visa.model.EFilterDate": {
				return EFilterDate.valueOf(String.valueOf(value));
			}
			case "class com.prismamp.api.coupons.visa.model.ECurrency": {
				return ECurrency.valueOf(String.valueOf(value));
			}
			case "class java.sql.Date": {
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(value));
				return new Date(date.getTime());
			}
			default:
				throw new ExceptionResponse(ETypeError.BAD_REQUEST, "");
			}
		} catch (ExceptionResponse e) {
			logger.log(Level.SEVERE, "Unexpected value: " + key + " - " + e.getMessage());
			throw new ExceptionResponse(ETypeError.BAD_REQUEST, "Unexpected value: " + key);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new ExceptionResponse(ETypeError.BAD_REQUEST, "Invalid parameter : " + key + " - " + e.getMessage());
		}
	}
}
