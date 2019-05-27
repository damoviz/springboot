package com.damoviz.springbootpeiky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.damoviz.springbootpeiky.model.Cliente;
import com.damoviz.springbootpeiky.model.CreditCard;

/**
 * Interface para definir las operaciones de bdd relacionadas con creditcard
 * @author edgarmogollon
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, String>{
	
	/*@Query("Select c from creditcard c where c.idCli =:idcli")
	public List<CreditCard> find(@Param("idcli") String idcli);*/
	
	public CreditCard findByIdCredit(String idCredit);

}
