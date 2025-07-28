package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
public class User {
    @Id
    private ObjectId userUserId;

    private String userEmail;
    private String userPassword;
}
