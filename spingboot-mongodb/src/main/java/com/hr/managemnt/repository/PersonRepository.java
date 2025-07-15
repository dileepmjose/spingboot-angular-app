package com.hr.managemnt.repository;


import com.hr.managemnt.model.Persons;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Persons, String> {


}