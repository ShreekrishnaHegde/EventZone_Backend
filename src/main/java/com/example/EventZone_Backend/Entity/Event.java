package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Event")
@Data
public class Event {
    @Id
    private ObjectId id;

    private String hostName;
    private String eventName;
    private String eventPoster;
    private String eventDescription;
    private LocalDateTime eventDateTime;
    private String  eventPosterUrl;
    private LocalDateTime lastDateToRegister;
    private int numberOfSlots;
    private List<ObjectId> participants=new ArrayList<>();
}
