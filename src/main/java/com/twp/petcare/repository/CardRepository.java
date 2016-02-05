package com.twp.petcare.repository;

import com.twp.petcare.bean.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cards", path = "cards")
public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> findByCardId(@Param("cardid") String cardid);
}
