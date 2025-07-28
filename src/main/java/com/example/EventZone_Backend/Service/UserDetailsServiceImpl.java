package com.example.EventZone_Backend.Service;

import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.AttendeeRepository;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private HostRepository hostRepository;

    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
        Attendee attendee=attendeeRepository.findByEmail(email);
        if (attendee != null) {
            return new User(
                    attendee.getEmail(),
                    attendee.getPassword(),
                    List.of(new SimpleGrantedAuthority(attendee.getRole()))
            );
        }
        Host host = hostRepository.findByEmail(email);
        if (host != null) {
            return new User(
                    host.getEmail(),
                    host.getPassword(),
                    List.of(new SimpleGrantedAuthority(host.getRole()))
            );
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
