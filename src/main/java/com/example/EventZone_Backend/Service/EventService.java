package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Repository.EventRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;


}
