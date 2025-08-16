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
    private Integer capacity;
}
