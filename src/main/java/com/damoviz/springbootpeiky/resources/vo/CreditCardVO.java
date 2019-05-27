package com.damoviz.springbootpeiky.resources.vo;
import hello.wsdl.Franchise;
import lombok.Data;

@Data
public class CreditCardVO {
	private String number;
	private Franchise franchise;
	private String token;
	private String expDate;
	private String codeCli;
}
