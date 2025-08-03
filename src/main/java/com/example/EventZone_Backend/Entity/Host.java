package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Host")
@Data
public class Host {
    @Id
    private ObjectId id;

    private String email;
    private String password;
    private String role = "ROLE_HOST";
}
