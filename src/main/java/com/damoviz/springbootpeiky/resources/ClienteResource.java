/**
 * 
 */
package com.damoviz.springbootpeiky.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damoviz.springbootpeiky.model.Cliente;
import com.damoviz.springbootpeiky.resources.vo.ClienteVO;
import com.damoviz.springbootpeiky.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el servicio web de cliente
 * @author edgarmogollon
 *
 */
@RestController
@RequestMapping("/api/cliente")
@Api(tags = "cliente")
public class ClienteResource {

	private final ClienteService clienteService;
	
	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida")})
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clienteVo){
		Cliente cliente = new Cliente();
		cliente.setCode(clienteVo.getCode());
		cliente.setDocumentNumber(clienteVo.getDocumentNumber());
		cliente.setDocumentType(clienteVo.getDocumentType());
		cliente.setFullName(clienteVo.getFullName());
		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping("/{code}")
	@ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<Cliente> updateCliente(@PathVariable("code") String code,
			ClienteVO clienteVo){
		
		Cliente cliente = this.clienteService.findByCode(code);
		if(cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else {
			cliente.setCode(clienteVo.getCode());
			cliente.setDocumentNumber(clienteVo.getDocumentNumber());
			cliente.setDocumentType(clienteVo.getDocumentType());
			cliente.setFullName(clienteVo.getFullName());
		}
		return new ResponseEntity<>(this.clienteService.update(cliente), HttpStatus.OK);
	}
	
	@DeleteMapping("/{code}")
	@ApiOperation(value = "Eliminar Cliente", notes = "Servicio para eliminar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void removeCliente(@PathVariable("code") String code,
			ClienteVO clienteVo) {
		Cliente cliente = this.clienteService.findByCode(code);
		if(cliente != null) {
			this.clienteService.delete(cliente);
		}
	}
	
	@GetMapping
	@ApiOperation(value = "Listar Cliente", notes = "Servicio para listar todos los cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente encontrados"),
			@ApiResponse(code = 404, message = "Cliente no encontrados")})
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
