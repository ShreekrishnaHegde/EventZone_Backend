package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registraionService;

    @PostMapping
    public ResponseEntity<?> register(
            @PathVariable String publicId
    ) throws Exception {
        registraionService.register(publicId);
        return ResponseEntity.ok("Registration Successfully");
    }
}
