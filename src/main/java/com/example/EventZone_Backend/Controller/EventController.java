package com.example.EventZone_Backend.Controller;


import com.example.EventZone_Backend.DTO.Event.EventCreateRequestDTO;
import com.example.EventZone_Backend.DTO.Event.EventResponseDTO;
import com.example.EventZone_Backend.DTO.Event.EventUpdateRequestDTO;
import com.example.EventZone_Backend.Service.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EventResponseDTO createEvent(
            @RequestPart("data") EventCreateRequestDTO requestDTO,
            @RequestPart(value = "image",required = false)MultipartFile imageFile
    ) throws IOException {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return eventService.createEvent(email, requestDTO,imageFile);
    }

    @GetMapping
    public List<EventResponseDTO> list() {
        return eventService.getEvents();
    }
    @PutMapping("/{id}")
    public EventResponseDTO update(@PathVariable ObjectId id, @RequestBody EventUpdateRequestDTO req) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return eventService.updateEvent(email, id, req);
    }
}
