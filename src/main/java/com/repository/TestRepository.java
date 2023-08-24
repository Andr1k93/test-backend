package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.document.Test;

public interface TestRepository extends MongoRepository<Test, String> {

}
