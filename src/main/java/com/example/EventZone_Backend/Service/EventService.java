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
import java.util.stream.Collectors;

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
            event.setEventImageUrl((String) uploadResult.get("secure_url"));
            event.setEventImagePublicId((String) uploadResult.get("public_id"));
        }
        eventRepository.save(event);
        return EventResponseDTO.fromEntityToThis(event);
    }

    //to get all events for a host
    public List<EventResponseDTO> getEvents() throws Exception {
        String email=getCurrentUserEmail();
        Host host=hostRepository.findByEmail(email);
        ObjectId hostId=host.getId();
        List<Event> events;
        try{
            events=eventRepository.findByHostIdOrderByDateAsc(hostId);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to evets for the hostid ",e);
        }
        if(events==null || events.isEmpty()){
            throw new Exception("No events ");
        }
        return events.stream()
                .map(EventMapper::toEventResponseDTO)
                .collect(Collectors.toList());
    }
    // to update an event
    public EventResponseDTO updateEvent(String publicId,EventUpdateRequestDTO req,MultipartFile imageFile) throws IOException {
        Event event=eventRepository.findByPublicId(publicId)
                .orElseThrow(()->new RuntimeException("Event Not Found with this id"));
        if(imageFile!=null && event.getEventImageUrl()!=null){
            deleteImageFromCloudinary(event.getEventImagePublicId());
        }
        if(imageFile!=null && !imageFile.isEmpty()){
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            event.setEventImageUrl((String) uploadResult.get("secure_url"));
            event.setEventImagePublicId((String) uploadResult.get("public_id"));
        }
        EventMapper.applyUpdates(req,event);
        eventRepository.save(event);
        return EventMapper.toEventResponseDTO(event);
    }

    //get all events for the attendees
    public List<EventResponseDTO> getAll() throws Exception {
        List<Event> events;
        try{
            events=eventRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve events",e);
        }
        if(events==null || events.isEmpty()){
            throw new Exception("No events ");
        }
        return events.stream()
                .map(EventMapper::toEventResponseDTO)
                .collect(Collectors.toList());
    }
    //helper methods
    public void deleteImageFromCloudinary(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
    }
    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
