package com.example.EventZone_Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Host")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    private ObjectId id;

    private String email;
    private String password;
    private String role = "ROLE_HOST";
    private String fullname;
    
}
