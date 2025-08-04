package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.Auth.AttendeeSignUpRequestDTO;
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
    public Attendee register(AttendeeSignUpRequestDTO request){
        Attendee attendee=new Attendee();
        attendee.setEmail(request.getEmail());
        attendee.setPassword(request.getPassword());
        return attendeeRepository.save(attendee);
    }
}
