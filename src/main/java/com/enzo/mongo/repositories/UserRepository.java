package com.enzo.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enzo.mongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
