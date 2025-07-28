package com.example.EventZone_Backend.Controller.AuthController;

import com.example.EventZone_Backend.DTO.AttendeeSignUpRequest;
import com.example.EventZone_Backend.DTO.HostSignUpRequest;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Service.AttendeeService;
import com.example.EventZone_Backend.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private HostService hostService;

    @PostMapping("/signup/attendee")
    public ResponseEntity<?> registerAttendee(@RequestBody AttendeeSignUpRequest attendeeSignUpRequest){
        Attendee attendee=attendeeService.register(attendeeSignUpRequest);
        return ResponseEntity.ok("Attendee Registered Successfully with email"+attendee.getAttendeeEmail());
    }

    @PostMapping("/signup/host")
    public ResponseEntity<?> registerHost(@RequestBody HostSignUpRequest hostSignUpRequest){
        Host host=hostService.register(hostSignUpRequest);
        return ResponseEntity.ok("Host Registered successfully with email"+host.getHostEmail());
    }

}
