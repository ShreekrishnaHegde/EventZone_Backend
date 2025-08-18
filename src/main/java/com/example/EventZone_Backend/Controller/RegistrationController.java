package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.Entity.Registration;
import com.example.EventZone_Backend.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> register(
            @PathVariable String publicId
    ) throws Exception {
        registrationService.register(publicId);
        return ResponseEntity.ok("Registration Successfully");
    }
    @GetMapping
    public ResponseEntity<List<Registration>> getRegistrationsByEvent(
            @PathVariable String publicId
    ){
        List<Registration> registrations=registrationService.getAllRegistrations(publicId);
        return ResponseEntity.ok(registrations);
    }
}
