/**
 * 
 */
package com.damoviz.springbootpeiky.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import hello.wsdl.Franchise;
import lombok.Data;

/**
 * Clase que representa la tabla creditcard
 * @author edgarmogollon
 *
 */
@Data
@Entity
@Table(name = "creditcard")
public class CreditCard {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid2")
	private String idCredit;
	private String number;
	private Franchise franchise;
	private String token;
	private String expDate;
	private String codeCli;
	@ManyToOne
	@JoinColumn(name="idCli")
	private Cliente cliente;
}
