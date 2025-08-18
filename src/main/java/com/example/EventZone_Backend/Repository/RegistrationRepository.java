package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Registration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RegistrationRepository extends MongoRepository<Registration, ObjectId> {
    List<Registration> findByEventId(ObjectId eventId);
    Optional<Registration> findByEventIdAndAttendeeId(ObjectId eventId,ObjectId attendeeId);
}
