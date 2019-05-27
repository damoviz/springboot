/**
 * 
 */
package com.damoviz.springbootpeiky.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damoviz.springbootpeiky.model.Cliente;
import com.damoviz.springbootpeiky.model.CreditCard;
import com.damoviz.springbootpeiky.repository.CreditCardRepository;

/**
 * Clase para definir los servicios de creditcard
 * @author edgarmogollon
 *
 */
@Service
@Transactional(readOnly = true)
public class CreditCardService {
	private final CreditCardRepository creditCardRepository;

	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}
	
	/**
	 * Metodo para guardar las creditcard del cliente
	 * @param creditCard
	 * @return
	 */
	@Transactional
	public CreditCard create(CreditCard creditCard) {
		return this.creditCardRepository.save(creditCard);
	}
	
	/**
	 * Metodo para actualizar la creditcard del cliente
	 * @param creditCard
	 * @return
	 */
	@Transactional
	public CreditCard update(CreditCard creditCard) {
		return this.creditCardRepository.save(creditCard);
	}
	
	/**
	 * Metodo para eliminar la creditcard del cliente
	 * @param creditCard
	 * @return
	 */
	@Transactional
	public void delete(CreditCard creditCard) {
		this.creditCardRepository.delete(creditCard);
	}
	
	/*public List<CreditCard> find(String idcli){
		return this.creditCardRepository.find(idcli);
	}*/
	
	public CreditCard findByIdCredit(String idCredit) {
		return this.creditCardRepository.findByIdCredit(idCredit);
	}
	
	public List<CreditCard> findAll(){
		return this.creditCardRepository.findAll();
	}
}
