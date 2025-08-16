package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Registration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository extends MongoRepository<Registration, ObjectId> {

}
