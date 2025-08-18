package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Event;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
    Page<Event> findByHostId(ObjectId hostId, Pageable pageable);
    Optional<Event> findByPublicId(String publicId);
}
