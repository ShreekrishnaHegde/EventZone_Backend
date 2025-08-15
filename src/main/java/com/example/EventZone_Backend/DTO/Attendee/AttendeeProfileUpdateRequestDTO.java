package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileUpdateRequestDTO {
    private String email;


    public void applyUpdatesTo(Attendee attendee) {
        if (this.email != null) {
            attendee.setEmail(this.email);
        }
    }
}
