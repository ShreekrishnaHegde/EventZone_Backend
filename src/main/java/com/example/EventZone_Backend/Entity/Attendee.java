package com.example.EventZone_Backend.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "Attendee")
@Data
public class Attendee {
    @Id
    private ObjectId id;

    @Indexed
    @NotBlank
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String fullName;
    private String collegeName;
    private String usn;
    private String branchName;

    private String imageUrl;
    private String imagePublicId;
    private String role = "ROLE_ATTENDEE";
}
