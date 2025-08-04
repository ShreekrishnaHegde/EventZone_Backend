package com.example.EventZone_Backend.Repository;

import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Entity.Host;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
    List<Event> findByHost(Host host);
}
