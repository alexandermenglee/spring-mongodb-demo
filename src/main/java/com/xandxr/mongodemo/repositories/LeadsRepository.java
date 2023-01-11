package com.xandxr.mongodemo.repositories;

import com.xandxr.mongodemo.domain.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadsRepository extends MongoRepository<Lead, String> {
    Lead insert(Lead lead);

    Lead findByEmail(String email);

    @Query("{ 'firstName': ?0, 'lastName': ?1 }")
    Optional<Lead> findByFirstNameAndLastName(String firstName, String lastName);
}
