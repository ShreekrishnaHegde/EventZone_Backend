package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.AttendeeSignUpRequest;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //signup
    public Attendee register(AttendeeSignUpRequest request){
        Attendee attendee=new Attendee();
        attendee.setAttendeeEmail(request.getEmail());
        attendee.setAttendeePassword(request.getPassword());
        return attendeeRepository.save(attendee);
    }
}
