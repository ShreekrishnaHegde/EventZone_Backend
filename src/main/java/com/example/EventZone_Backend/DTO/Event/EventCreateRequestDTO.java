package com.example.EventZone_Backend.DTO.Event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventCreateRequestDTO {
    private String title;
    private String description;
    private LocalDate date;
}
