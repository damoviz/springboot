package com.damoviz.springbootpeiky.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import hello.wsdl.UserLoginRequest;
import hello.wsdl.UserLoginResponse;
 
//@SpringBootApplication
public class SpringBootSoapClientApplication {
 
    /*public static void main(String[] args) {
        SpringApplication.run(SpringBootSoapClientApplication.class, args);
    }*/
     
    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {
            String name = "Sajal";//Default Name
            if(args.length>0){
                name = args[0];
            }
            UserLoginRequest request = new UserLoginRequest();
            request.setUsername("karen");
            request.setPassword("karen2");
            UserLoginResponse response =(UserLoginResponse) soapConnector.callWebService("http://18.222.184.108:8080/ws/test/UserLogin", request);
            System.out.println("Got Response As below ========= : ");
            System.out.println("Code : "+response.getUser().getCode());
            System.out.println("FullName : "+response.getUser().getFullname());

        };
    }
}

