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

    private String clubName;
    private String clubDescription;
    private String clubLogo;
    private String logoPublicId;

    private double rating;
    private String paymentAccountId;
    private String phoneNumber;
    
    private String website;
    private String instagram;
    private String linkedin;
    private String twitter;

}
