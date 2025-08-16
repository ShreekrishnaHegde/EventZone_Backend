package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attendee")
@Data
public class Attendee {
    @Id
    private ObjectId id;

    private String email;
    private String password;

    private String fullname;
    private String collegeName;
    private String USN;
    private String branchName;

    private String imageUrl;
    private String imagePublicId;
    private String role = "ROLE_ATTENDEE";
}
