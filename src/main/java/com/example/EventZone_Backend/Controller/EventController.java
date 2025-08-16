package com.example.EventZone_Backend.Controller;


import com.example.EventZone_Backend.DTO.Event.EventCreateRequestDTO;
import com.example.EventZone_Backend.DTO.Event.EventResponseDTO;
import com.example.EventZone_Backend.DTO.Event.EventUpdateRequestDTO;
import com.example.EventZone_Backend.Service.EventService;
import org.apache.coyote.Response;
import org.apache.http.protocol.HTTP;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvent(
            @RequestPart("data") EventCreateRequestDTO requestDTO,
            @RequestPart(value = "image",required = false)MultipartFile imageFile
    ) throws IOException {
        try{
            eventService.createEvent(requestDTO,imageFile);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to create the event");
        }
    }
//    @PutMapping(value = "/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateEvent(
//            @PathVariable
//            @RequestPart("data") EventUpdateRequestDTO requestDTO,
//            @RequestPart(value = "image",required = false) MultipartFile imageFile
//    ){
//        EventResponseDTO responseDTO=eventService.updateEvent(requestDTO,imageFile);
//    }

    @GetMapping
    public List<EventResponseDTO> list() {
        return eventService.getEvents();
    }

}
