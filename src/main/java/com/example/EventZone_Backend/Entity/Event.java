package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Document(collection = "Event")
@Data
public class Event {
    @Id
    private ObjectId id;

    @Indexed
    private ObjectId hostId;

    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private String time;

    private String host;

    private String eventImageUrl;
    private String eventImagePublicId;

    private Integer capacity;
    private int registrationCount;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
