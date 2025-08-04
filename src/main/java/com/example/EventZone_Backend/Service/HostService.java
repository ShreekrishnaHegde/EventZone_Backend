package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HostService {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //signup
    public Host register(HostSignUpRequestDTO request){
        Host host=new Host();
        host.setEmail(request.getEmail());
        host.setPassword(request.getPassword());
        return hostRepository.save(host);
    }
    //to fetch the profile
    public HostProfileResponseDTO getProfile() {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }
    //to update the profile
    public HostProfileResponseDTO updateProfile(HostProfileUpdateRequestDTO request) {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        host.setFullname(request.getFullname());
        hostRepository.save(host);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
