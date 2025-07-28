package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.DTO.HostSignUpRequest;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        host.setHostEmail(request.getEmail());
        host.setHostPassword(request.getPassword());
        return hostRepository.save(host);
    }
}
