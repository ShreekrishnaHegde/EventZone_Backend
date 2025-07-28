package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attendee")
@Data
public class Attendee {
    @Id
    private ObjectId AttendeeUserId;

    private String AttendeeEmail;
    private String AttendeePassword;
    private String role = "ROLE_ATTENDEE";
}
