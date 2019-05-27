package com.damoviz.springbootpeiky.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damoviz.springbootpeiky.consumer.SOAPConnector;
import com.damoviz.springbootpeiky.model.Cliente;
import com.damoviz.springbootpeiky.model.CreditCard;
import com.damoviz.springbootpeiky.resources.vo.UserLoginVO;
import com.damoviz.springbootpeiky.services.ClienteService;
import com.damoviz.springbootpeiky.services.CreditCardService;

import hello.wsdl.UserLoginRequest;
import hello.wsdl.UserLoginResponse;
import hello.wsdl.CreditCardGetByUserCodeRequest;
import hello.wsdl.CreditCardGetByUserCodeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/login")
@Api(tags = "login")
public class UserLoginResource {
	
    @Autowired
    SOAPConnector soapConnector;
	
	private final ClienteService clienteService;
	private final CreditCardService creditCardService;
	
	public UserLoginResource(ClienteService clienteService,CreditCardService creditCardService) {
		this.clienteService = clienteService;
		this.creditCardService = creditCardService;
	}
	
	@PostMapping
	@ApiOperation(value = "Logear cliente", notes = "Servicio para logear cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente logeado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida")})
	public ResponseEntity<Cliente> login(@RequestBody UserLoginVO userLoginVO){		
		UserLoginResponse response = validateUser(userLoginVO);
		Cliente cliente = new Cliente();
		cliente.setCode(response.getUser().getCode());
		cliente.setDocumentNumber(response.getUser().getDocumentNumber());
		cliente.setDocumentType(response.getUser().getDocumentType());
		cliente.setFullName(response.getUser().getFullname());
		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}
	
	/**
	 * Metodo que va y consume el servicio SOAP de UserLogin Peiky
	 */
	public UserLoginResponse validateUser(UserLoginVO userLoginVO) {
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername(userLoginVO.getUsername());
        request.setPassword(userLoginVO.getPassword());
        UserLoginResponse response =(UserLoginResponse) soapConnector.callWebService("http://18.222.184.108:8080/ws/test/UserLogin", request);
        System.out.println("Got Response As below ========= : ");
        System.out.println("Code : "+response.getUser().getCode());
        System.out.println("FullName : "+response.getUser().getFullname());
        getCreditCardUser(response.getUser().getCode());
	  return response;
	}
	
	/**
	 * Metodo para insertar las tarjetas del cliente una vez el usuario exista
	 * @param code
	 */
	public void getCreditCardUser(String code) {
		CreditCardGetByUserCodeRequest request = new CreditCardGetByUserCodeRequest();
		request.setCode(code);
		CreditCardGetByUserCodeResponse response =(CreditCardGetByUserCodeResponse) soapConnector.callWebService("http://18.222.184.108:8080/ws/test/CreditCardGetByUserCode", request);
        System.out.println("Got Response As getCreditCards ========= : ");
        System.out.println("getCreditCards : "+ response.getCreditCards().get(0).getNumber());
        
        List<hello.wsdl.CreditCard> listCard = response.getCreditCards();
        
        for(hello.wsdl.CreditCard str : listCard)
        {           
        	CreditCard card = new CreditCard();
    		card.setNumber(str.getNumber());
    		card.setFranchise(str.getFranchise());
    		card.setToken(str.getToken());
    		card.setExpDate(str.getExpDate());
    		card.setCodeCli(code);
    		
    		this.creditCardService.create(card);
        }
	}
}




