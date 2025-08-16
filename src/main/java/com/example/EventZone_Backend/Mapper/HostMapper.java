package com.example.EventZone_Backend.Mapper;

import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
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
    public static HostProfileResponseDTO toHostProfileResponseDTO(Host host){
        HostProfileResponseDTO dto=new HostProfileResponseDTO();
        dto.setEmail(host.getEmail());
        dto.setDescription(host.getDescription());
        dto.setName(host.getName());
        dto.setLinkedin(host.getLinkedin());
        dto.setInstagram(host.getInstagram());
        dto.setWebsite(host.getWebsite());
        dto.setTwitter(host.getTwitter());
        dto.setPhoneNumber(host.getPhoneNumber());
        return dto;
    }
    public static void applyUpdates(HostProfileUpdateRequestDTO requestDTO,Host host){
        if(requestDTO.getName()!=null){
            host.setName(requestDTO.getName());
        }
        if(requestDTO.getDescription()!=null){
            host.setDescription(requestDTO.getDescription());
        }
        if(requestDTO.getWebsite()!=null){
            host.setWebsite(requestDTO.getWebsite());
        }
        if(requestDTO.getInstagram()!=null){
            host.setInstagram(requestDTO.getInstagram());
        }
        if(requestDTO.getLinkedin()!=null){
            host.setInstagram(requestDTO.getInstagram());
        }
        if(requestDTO.getTwitter()!=null){
            host.setTwitter(requestDTO.getTwitter());
        }
        if(requestDTO.getPhoneNumber()!=null){
            host.setPhoneNumber(requestDTO.getPhoneNumber());
        }
    }
}
