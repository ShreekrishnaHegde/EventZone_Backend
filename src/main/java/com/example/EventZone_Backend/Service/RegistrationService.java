package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Entity.Registration;
import com.example.EventZone_Backend.Repository.AttendeeRepository;
import com.example.EventZone_Backend.Repository.EventRepository;
import com.example.EventZone_Backend.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private EventRepository eventRepository;
    public void register(String publicId) throws Exception {
        String email=getCurrentUserEmail();
        Attendee attendee=attendeeRepository.findByEmail(email);
        if(attendee==null){
            throw  new Exception("Attendee not found");
        }
        Event event=eventRepository.findByPublicId(publicId)
                .orElseThrow(()->new RuntimeException("Event Not Found with this id"));
        Optional<Registration> existing=registrationRepository.findByEventId(event.getId());
        if(existing.isPresent()){
            throw new Exception("Attendee Already exists with the email");
        }
        Registration registration=new Registration();
        registration.setHostId(event.getHostId());
        registration.setEventId(event.getId());
        registration.setAttendeeId(attendee.getId());
        registrationRepository.save(registration);
    }
    public void getAllRegistrations(String publicId){
        Event event=eventRepository.findByPublicId(publicId)
                .orElseThrow(()->new RuntimeException("Event Not Found with this id"));

    }
    //helper methods
    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
