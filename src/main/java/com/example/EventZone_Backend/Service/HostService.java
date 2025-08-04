package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponse;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequest;
import com.example.EventZone_Backend.DTO.HostSignUpRequest;
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
    public Host register(HostSignUpRequest request){
        Host host=new Host();
        host.setEmail(request.getEmail());
        host.setPassword(request.getPassword());
        return hostRepository.save(host);
    }
    //to fetch the profile
    public HostProfileResponse getProfile() {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        return mapToResponse(host);
    }
    //to update the profile
    public HostProfileResponse updateProfile(HostProfileUpdateRequest request) {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        host.setFullname(request.getFullname());
        hostRepository.save(host);
        return mapToResponse(host);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private HostProfileResponse mapToResponse(Host host) {
        HostProfileResponse res = new HostProfileResponse();
        res.setEmail(host.getEmail());
        res.setFullname(host.getFullname());
        return res;
    }
}
