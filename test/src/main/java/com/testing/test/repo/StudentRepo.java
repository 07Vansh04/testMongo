package com.testing.test.repo;

import com.testing.test.model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo  extends MongoRepository<Students, String> {

}
