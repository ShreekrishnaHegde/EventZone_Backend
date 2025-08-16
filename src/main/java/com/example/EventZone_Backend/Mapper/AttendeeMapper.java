package com.example.EventZone_Backend.Mapper;

import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileUpdateRequestDTO;
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
    public static AttendeeProfileResponseDTO toAttendeeProfileResponseDTO(Attendee attendee){
        AttendeeProfileResponseDTO dto=new AttendeeProfileResponseDTO();
        dto.setEmail(attendee.getEmail());
        dto.setImageUrl(attendee.getImageUrl());
        dto.setFullName(attendee.getFullName());
        dto.setBranchName(attendee.getBranchName());
        dto.setUsn(attendee.getUsn());
        return dto;
    }
    public static void applyUpdates(AttendeeProfileUpdateRequestDTO requestDTO,Attendee attendee){
        if(requestDTO.getFullName()!=null){
            attendee.setFullName(requestDTO.getFullName());
        }
        if(requestDTO.getCollegeName()!=null){
            attendee.setCollegeName(requestDTO.getCollegeName());
        }
        if (requestDTO.getBranchName()!=null){
            attendee.setBranchName(requestDTO.getBranchName());
        }
        if(requestDTO.getUsn()!=null){
            attendee.setUsn(requestDTO.getUsn());
        }
    }
}
