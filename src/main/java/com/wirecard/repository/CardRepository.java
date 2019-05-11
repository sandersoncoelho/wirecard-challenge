package com.wirecard.repository;

import com.wirecard.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {
	
	public Card findByNumber(String number);
}
