package com.example.EventZone_Backend.DTO.Event;

import com.example.EventZone_Backend.Entity.Event;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class EventResponseDTO {
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private String time;
    private Integer capacity;
    private String host;
    private String eventImageUrl;
    private int registrationCount;
    private Instant createdAt;
    private Instant updatedAt;

    public static EventResponseDTO fromEntityToThis(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        return dto;
    }
}
