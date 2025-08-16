package com.example.EventZone_Backend.Mapper;

import com.example.EventZone_Backend.DTO.Auth.AttendeeSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Attendee;

public class AttendeeMapper {
    public static Attendee toEntity(AttendeeSignUpRequestDTO dto){
        if(dto==null){
            return null;
        }
        Attendee attendee=new Attendee();
        attendee.setEmail(dto.getEmail());
        attendee.setFullName(dto.getFullName());
        return attendee;
    }
}
