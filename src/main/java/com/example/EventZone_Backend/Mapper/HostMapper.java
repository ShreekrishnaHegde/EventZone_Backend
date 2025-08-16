package com.example.EventZone_Backend.Mapper;

import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Host;

public class HostMapper {
    public static Host toEntity(HostSignUpRequestDTO dto){
        if(dto==null){
            return null;
        }
        Host host=new Host();
        host.setEmail(dto.getEmail());
        host.setName(dto.getName());
        return host;
    }
}
