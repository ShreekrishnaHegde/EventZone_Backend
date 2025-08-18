package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Host;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends MongoRepository<Host, ObjectId> {
    Host findByEmail(String email);

}
