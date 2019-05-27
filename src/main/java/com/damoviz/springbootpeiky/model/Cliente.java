/**
 * 
 */
package com.damoviz.springbootpeiky.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import hello.wsdl.DocumentType;
import lombok.Data;

/**
 * Clase que representa la tabla Cliente
 * @author edgarmogollon
 *
 */
@Data
@Entity
@Table(name = "cliente")
@NamedQuery(name="Cliente.findByDocumentNumber",query="Select c from Cliente c where c.documentNumber = ?1")
public class Cliente {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid2")
	private String idCli;
	private String code;
	private String documentNumber;
	private DocumentType documentType;
	private String fullName;
	
	@OneToMany(mappedBy="cliente")
	private Set<CreditCard> creditcard;
	
}
