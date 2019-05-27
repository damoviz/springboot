/**
 * 
 */
package com.damoviz.springbootpeiky.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damoviz.springbootpeiky.model.Cliente;
import com.damoviz.springbootpeiky.repository.ClienteRepository;

/**
 * Clase para definir los servicios de cliente
 * @author edgarmogollon
 *
 */
@Service
@Transactional(readOnly = true)
public class ClienteService {
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	/**
	 * Metodo para realizar la operacion de guardar un cliente
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente create(Cliente cliente){
		return this.clienteRepository.save(cliente);
	}
	
	/**
	 * Metodo para actualizar un cliente
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente update(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	/**
	 * Metodo para eliminar un cliente
	 * @param cliente
	 */
	@Transactional
	public void delete(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	
	/**
	 * Metodo para consultar un cliente por su code
	 * @param code
	 * @return
	 */
	public Cliente findByCode(String code) {
		return this.clienteRepository.findByCode(code);
	}
	
	/**
	 * Metodo para consultar un cliente por su numero de identificacion
	 * @param documentNumber
	 * @return
	 */
	public Cliente findByDocumentNumber(String documentNumber) {
		return this.clienteRepository.findByDocumentNumber(documentNumber);
	}
	
	/**
	 * Metodo que devuelve todos los clientes
	 * @return
	 */
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
}
