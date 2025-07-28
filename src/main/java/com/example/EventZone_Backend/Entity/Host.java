package com.example.EventZone_Backend.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Host")
@Data
public class Host {
    @Id
    private ObjectId hostId;

    private String hostEmail;
    private String hostPassword;
    private String role = "ROLE_HOST";
}
