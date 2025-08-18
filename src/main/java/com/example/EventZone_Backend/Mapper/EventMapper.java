package com.example.EventZone_Backend.Mapper;

import com.example.EventZone_Backend.DTO.Event.EventCreateRequestDTO;
import com.example.EventZone_Backend.DTO.Event.EventResponseDTO;
import com.example.EventZone_Backend.DTO.Event.EventUpdateRequestDTO;
import com.example.EventZone_Backend.Entity.Event;
import com.example.EventZone_Backend.Entity.Host;

public class EventMapper {
    public static Event toEntity(EventCreateRequestDTO requestDTO, Host host){
        Event event=new Event();
        event.setHostId(host.getId());
        event.setTitle(requestDTO.getTitle());
        event.setDescription(requestDTO.getDescription());
        event.setDate(requestDTO.getDate());
        event.setTime(requestDTO.getTime());
        event.setLocation(event.getLocation());
        if(requestDTO.getCapacity()!=null){
            event.setCapacity(requestDTO.getCapacity());
        }
        event.setHostId(host.getId());
        event.setHost(host.getName());
        return event;
    }
    public static EventResponseDTO toEventResponseDTO(Event event){
        EventResponseDTO responseDTO=new EventResponseDTO();
        responseDTO.setHost(event.getHost());
        responseDTO.setDescription(event.getDescription());
        responseDTO.setLocation(event.getLocation());
        responseDTO.setCapacity(event.getCapacity());
        responseDTO.setDate(event.getDate());
        responseDTO.setTime(event.getTime());
        responseDTO.setRegistrationCount(event.getRegistrationCount());
        responseDTO.setTitle(event.getTitle());
        responseDTO.setCreatedAt(event.getCreatedAt());
        responseDTO.setUpdatedAt(event.getUpdatedAt());
        responseDTO.setEventImageUrl(event.getEventImageUrl());
        return responseDTO;
    }
    public static void applyUpdates(EventUpdateRequestDTO requestDTO,Event event){
        if(requestDTO.getTitle()!=null){
            event.setTitle(requestDTO.getTitle());
        }
        if(requestDTO.getDescription()!=null){
            event.setDescription(requestDTO.getDescription());
        }
        if(requestDTO.getDate()!=null){
            event.setDate(requestDTO.getDate());
        }
        if(requestDTO.getTime()!=null){
            event.setTime(requestDTO.getTime());
        }
        if(requestDTO.getLocation()!=null){
            event.setLocation(requestDTO.getLocation());
        }
        event.setCapacity(requestDTO.getCapacity());
    }
}
