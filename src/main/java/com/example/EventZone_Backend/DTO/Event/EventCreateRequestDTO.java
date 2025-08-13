package com.example.EventZone_Backend.DTO.Event;

import com.example.EventZone_Backend.Entity.Event;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventCreateRequestDTO {
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private String time;

    public Event toEntity() {
        Event event = new Event();
        event.setTitle(this.title);
        event.setDescription(this.description);
        event.setDate(this.date);
        event.setLocation(this.location);
        event.setTime(this.time);
        return event;
    }
}
