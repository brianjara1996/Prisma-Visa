package com.prismamp.api.coupons.visa.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tx_visa")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisaCoupon {
	
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "account_number")
	private Long account_number;

	@Column(name = "card_number")
	private String card_number;

	@Column(name = "arn")
	private String arn;

	@Column(name = "purchase_date")
	private Date purchase_date;
	
	@Column(name = "currency")
	private String currency;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "amount_usd")
	private BigDecimal amount_usd;

	@Column(name = "merchant_name")
	private String merchant_name;

	@Column(name = "merchant_city")
	private String merchant_city;

	@Column(name = "merchant_country")
	private String merchant_country;
	
	@Column(name = "merchant_province")
	private String merchant_province;

	@Column(name = "merchant_category_code")
	private int merchant_category_code;

	@Column(name = "issuer_bank_code")
	private int issuer_bank_code;

	@Column(name = "issuer_branch_code")
	private int issuer_branch_code;

	@Column(name = "bill_cycle")
	private int bill_cycle;

	@Column(name = "payment_date")
	private Date payment_date;

	@Column(name = "authorization_code")
	private String authorization_code;

	@Column(name = "plan_type")
	private String plan_type;

	@Column(name = "total_instalments")
	private int total_instalments;

	@Column(name = "instalment_number")
	private int instalment_number;

	@Column(name = "moto_eci")
	private String moto_eci;

	@Column(name = "pos_terminal_capabilitiy")
	private String pos_terminal_capabilitiy;

	@Column(name = "pos_entry_mode")
	private String pos_entry_mode;

	@Column(name = "card_product")
	private String card_product;

	@Column(name = "card_type")
	private String card_type;

	@Column(name = "transaction_code")
	private String transaction_code;

	@Column(name = "merchant_number")
	private String merchant_number;

	@Column(name = "processing_date")
	private Date processing_date;

	@Column(name = "cie_number")
	private String cie_number;

	@Column(name = "cardholder_id_method_used")
	private String cardholder_id_method_used;

	@Column(name = "terminal_id")
	private String terminal_id;

	@Column(name = "cashback_amount")
	private Float cashback_amount;

	@Column(name = "cardholder_activated_terminal_indicator")
	private String cardholder_activated_terminal_indicator;

	@Column(name = "merchant_zip_code")
	private String merchant_zip_code;

	@Column(name = "reimbursement_attribute")
	private String reimbursement_attribute;

	@Column(name = "pos_environment")
	private String pos_environment;

	@Column(name = "original_cp_date")
	private String original_cp_date;

	@Column(name = "transaction_id")
	private String transaction_id;

	@Column(name = "no_show_indicator")
	private String no_show_indicator;

	@Column(name = "clean_summary_indicator")
	private String clean_summary_indicator;

	@Column(name = "mvv")
	private String mvv;

	@Column(name = "product_id")
	private String product_id;

	@Column(name = "multiple_clearing_seq_number")
	private String multiple_clearing_seq_number;

	@Column(name = "multiple_clearing_seq_count")
	private String multiple_clearing_seq_count;

	@Column(name = "surcharge_amount")
	private Float surcharge_amount;

	@Column(name = "surcharge_indicator")
	private String surcharge_indicator;

	@Column(name = "instalment_type")
	private String instalment_type;

	@Column(name = "acquirer_indicator")
	private String acquirer_indicator;

	@Column(name = "cvm_limit_indicator")
	private String cvm_limit_indicator;

	@Column(name = "total_amount")
	private Float total_amount;

	@Column(name = "instalment_interest")
	private float instalment_interest;

	@Column(name = "vat_interest_amount")
	private Float vat_interest_amount;

	@Column(name = "risk_fee_amount")
	private Float risk_fee_amount;

	@Column(name = "vat_risk_fee_amount")
	private Float vat_risk_fee_amount;

	@Column(name = "irf_indicator")
	private String irf_indicator;

	@Column(name = "settlement_indicator")
	private String settlement_indicator;

	@Column(name = "deferred_settlement_date")
	private int deferred_settlement_date;

	@Column(name = "tip_amount")
	private Float tip_amount;

	@Column(name = "irf_amount")
	private Float irf_amount;

	@Column(name = "vat_irf_amount")
	private Float vat_irf_amount;

	@Column(name = "promotion_indicator")
	private String promotion_indicator;

	@Column(name = "promotion_id")
	private String promotion_id;

	@Column(name = "user_discount_percentage")
	private Float user_discount_percentage;

	@Column(name = "merchant_discount_percentage")
	private Float merchant_discount_percentage;

	@Column(name = "campaing_bank_code")
	private String campaing_bank_code;

	@Column(name = "promotion_rest")
	private String promotion_rest;

	@Column(name = "merchant_bank_code")
	private String merchant_bank_code;

	@Column(name = "financial_indicator")
	private String financial_indicator;

	@Column(name = "token_level")
	private String token_level;

	@Column(name = "token_requestor_id")
	private String token_requestor_id;

	@Column(name = "pan_token")
	private String pan_token;

	@Column(name = "mail_phone_indicator")
	private String mail_phone_indicator;

	@Column(name = "auth_source_code")
	private String auth_source_code;

	@Column(name = "dcc_indicator")
	private String dcc_indicator;

	@Column(name = "automatic_debit_client_id")
	private String automatic_debit_client_id;

	@Column(name = "fee_program_indicator")
	private String fee_program_indicator;

	@Column(name = "wallet_id")
	private String wallet_id;

	@Column(name = "oct_transaction_indicator")
	private String oct_transaction_indicator;

	@Column(name = "bai_code")
	private String bai_code;

	@Column(name = "vat_rate")
	private Float vat_rate;

	@Column(name = "merchant_volume_indicator")
	private String merchant_volume_indicator;

	@Column(name = "interchange_fee_amount")
	private Float interchange_fee_amount;

	@Column(name = "interchange_fee_amount_sign")
	private String interchange_fee_amount_sign;
	
	@Column(name = "transaction_receipt")
	private Long transaction_receipt;
	
	@Column(name = "first_six")
	private Long first_six;
	
	@Column(name = "last_four")
	private Long last_four;

}
