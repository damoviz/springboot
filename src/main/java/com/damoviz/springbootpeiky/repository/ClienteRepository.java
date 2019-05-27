/**
 * 
 */
package com.damoviz.springbootpeiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damoviz.springbootpeiky.model.Cliente;

/**
 * Interface para definir las operaciones de bdd relacionadas con cliente
 * @author edgarmogollon
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
	/**
	 * Definicion de metodo para buscar el cliente por el codigo (code)
	 * @param code
	 * @return
	 */
	public Cliente findByCode(String code);
	
	public Cliente findByDocumentNumber(String documentNumber);

}
