package com.example.EventZone_Backend.DTO.Event;

import com.example.EventZone_Backend.Entity.Event;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public class EventResponseDTO {
    private ObjectId id;
    private String title;
    private String description;
    private LocalDate date;


    public static EventResponseDTO fromEntityToThis(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        return dto;
    }
}
