package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "EventRegistration")
@Data
public class Registration {
    @Id
    private ObjectId id;
    @Indexed
    private ObjectId hostId;
    @Indexed
    private ObjectId eventId;
    @Indexed
    private ObjectId attendeeId;
    @CreatedDate
    private Instant createdAt;
}
