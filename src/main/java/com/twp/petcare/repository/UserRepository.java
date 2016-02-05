package com.twp.petcare.repository;

import com.twp.petcare.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

    List<User> findByName(@Param("name") String name);

    List<User> findByDocumentNumber(@Param("documentNumber") String documentNumber);

}
