package com.example.EventZone_Backend.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
