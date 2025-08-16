package com.example.EventZone_Backend.DTO.Auth;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeSignUpRequestDTO {
    private String email;
    private String password;
    private String fullName;


}
