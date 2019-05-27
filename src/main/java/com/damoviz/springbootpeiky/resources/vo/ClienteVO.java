/**
 * 
 */
package com.damoviz.springbootpeiky.resources.vo;

import lombok.Data;
import hello.wsdl.DocumentType;

/**
 * Clase para mapear al servicio
 * @author edgarmogollon
 *
 */
@Data
public class ClienteVO {
	private String code;
	private String documentNumber;
	private DocumentType documentType;
	private String fullName;
}
