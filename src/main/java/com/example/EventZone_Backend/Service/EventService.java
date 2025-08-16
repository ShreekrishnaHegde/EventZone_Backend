package com.example.EventZone_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EventZone_Backend.DTO.Event.EventCreateRequestDTO;
import com.example.EventZone_Backend.DTO.Event.EventResponseDTO;
import com.example.EventZone_Backend.DTO.Event.EventUpdateRequestDTO;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Mapper.EventMapper;
import com.example.EventZone_Backend.Repository.EventRepository;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private HostRepository hostRepository;
    @Autowired
    private Cloudinary cloudinary;

    //to create an event
    public EventResponseDTO createEvent(EventCreateRequestDTO request,MultipartFile imageFile) throws IOException {
        String email=getCurrentUserEmail();
        Host host=hostRepository.findByEmail(email);
        if (host==null){
            throw new RuntimeException("Host Not Found with email"+ email);
        }
        Event event= EventMapper.toEntity(request,host);
        if (imageFile != null && !imageFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            event.setEventImage((String) uploadResult.get("secure_url"));
            event.setEventImagePublicId((String) uploadResult.get("public_id"));
        }
        eventRepository.save(event);
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
    //image upload service
    public String uploadImage(MultipartFile file) throws Exception{
        Map uploadResult=cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
