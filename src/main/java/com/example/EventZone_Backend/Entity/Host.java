package com.example.EventZone_Backend.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Document(collection = "Host")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    private ObjectId id;
    @Email
    @NotBlank
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role = "ROLE_HOST";

    private String name;
    private String description;
    private String logoUrl;
    private String logoPublicId;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal rating;
    private String paymentAccountId;
    private String phoneNumber;

    @URL(protocol = "https")
    private String website;
    @URL(protocol = "https")
    private String instagram;
    @URL(protocol = "https")
    private String linkedin;
    @URL(protocol = "https")
    private String twitter;
}
