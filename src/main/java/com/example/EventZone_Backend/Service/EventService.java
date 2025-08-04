package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.Event.EventCreateRequestDTO;
import com.example.EventZone_Backend.DTO.Event.EventResponseDTO;
import com.example.EventZone_Backend.DTO.Event.EventUpdateRequestDTO;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.EventRepository;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private HostRepository hostRepository;

    //to create an event
    public EventResponseDTO createEvent(String email, EventCreateRequestDTO request){
        Host host=hostRepository.findByEmail(email);
        Event event=new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        return EventResponseDTO.fromEntityToThis(event);
    }

    //to get all events for a host
    public List<EventResponseDTO> getEvents(){
        return eventRepository.findAll().stream().map(
                EventResponseDTO::fromEntityToThis
        ).toList();
    }
    // to update an event
    public EventResponseDTO updateEvent(String email, ObjectId eventId, EventUpdateRequestDTO req) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setDate(req.getDate());
        return EventResponseDTO.fromEntityToThis(eventRepository.save(event));
    }

}
