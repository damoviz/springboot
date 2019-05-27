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

import com.damoviz.springbootpeiky.model.CreditCard;
import com.damoviz.springbootpeiky.resources.vo.CreditCardVO;
import com.damoviz.springbootpeiky.services.CreditCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el servicio web de creditcard
 * @author edgarmogollon
 *
 */
@RestController
@RequestMapping("/api/creditcard")
@Api(tags = "creditcard")
public class CreditCardResource {
	
	private final CreditCardService creditCardService;
	
	public CreditCardResource(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@PostMapping
	@ApiOperation(value = "Crear tarjeta", notes = "Servicio para crear tarjeta del cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Tarjeta creada correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida")})
	public ResponseEntity<CreditCard> createTarjeta(@RequestBody CreditCardVO creditCardVO){
		CreditCard card = new CreditCard();
		card.setNumber(creditCardVO.getNumber());
		card.setFranchise(creditCardVO.getFranchise());
		card.setToken(creditCardVO.getToken());
		card.setExpDate(creditCardVO.getExpDate());
		card.setCodeCli(creditCardVO.getCodeCli());
		return new ResponseEntity<>(this.creditCardService.create(card), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idCredit}")
	@ApiOperation(value = "Crear tarjeta", notes = "Servicio para crear tarjeta del cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Tarjeta creada correctamente"),
			@ApiResponse(code = 404, message = "Solicitud Inválida")})
	public ResponseEntity<CreditCard> updateTarjeta(@PathVariable("idCredit") String idCredit,@RequestBody CreditCardVO creditCardVO){
		CreditCard card = this.creditCardService.findByIdCredit(idCredit);
		
		if(card == null) {
			return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
		}else {
			card.setNumber(creditCardVO.getNumber());
			card.setFranchise(creditCardVO.getFranchise());
			card.setToken(creditCardVO.getToken());
			card.setExpDate(creditCardVO.getExpDate());
			card.setCodeCli(creditCardVO.getCodeCli());
		}
		return new ResponseEntity<>(this.creditCardService.update(card), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCredit}")
	@ApiOperation(value = "Eliminar tarjeta", notes = "Servicio para eliminar tarjeta del cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Tarjeta eliminada correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida")})
	public void removeTarjeta(@PathVariable("idCredit") String idCredit,@RequestBody CreditCardVO creditCardVO){
		CreditCard card = this.creditCardService.findByIdCredit(idCredit);
		
		if(card != null) {
			this.creditCardService.delete(card);
		}
	}
	
	@GetMapping
	@ApiOperation(value = "Listar tarjetas", notes = "Servicio para listar las tarjetas")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Tarjetas encontradas"),
			@ApiResponse(code = 404, message = "Tarjetas no encontrados")})
	public ResponseEntity<List<CreditCard>> findAll(){
		return ResponseEntity.ok(this.creditCardService.findAll());
	}
}
