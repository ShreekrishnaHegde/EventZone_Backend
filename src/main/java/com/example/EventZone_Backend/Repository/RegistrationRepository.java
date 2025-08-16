package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Registration;
import com.example.EventZone_Backend.Entity.RegistrationStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, ObjectId> {
    Optional<Registration> findByEventIdAndAttendeeId(ObjectId eventId,ObjectId attendeeId);
    long countByEventIdAndStatus(ObjectId eventId, RegistrationStatus status);
    Page<Registration> findByEventIdAndStatus(ObjectId eventId,RegistrationStatus status);
}
