package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.DTO.Auth.AttendeeSignUpRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.AuthRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.AuthResponseDTO;
import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Service.AttendeeService;
import com.example.EventZone_Backend.Service.HostService;
import com.example.EventZone_Backend.Service.UserDetailsServiceImpl;
import com.example.EventZone_Backend.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private HostService hostService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup/attendee")
    public ResponseEntity<?> registerAttendee(@RequestBody AttendeeSignUpRequestDTO attendeeSignUpRequest){
        Attendee attendee=attendeeService.register(attendeeSignUpRequest);
        UserDetails userDetails=userDetailsService.loadUserByUsername(attendee.getEmail());
        String token=jwtUtil.generateToken(userDetails.getUsername(),"ATTENDEE");
        return ResponseEntity.ok(new AuthResponseDTO(token,attendee.getRole(),attendee.getEmail()));
    }

    @PostMapping("/signup/host")
    public ResponseEntity<?> registerHost(@RequestBody HostSignUpRequestDTO hostSignUpRequest){
        Host host=hostService.register(hostSignUpRequest);
        UserDetails userDetails=userDetailsService.loadUserByUsername(host.getEmail());
        String token=jwtUtil.generateToken(userDetails.getUsername(),"HOST");
        return ResponseEntity.ok(new AuthResponseDTO(token,host.getRole(),host.getEmail()));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest){
        try {
            var authToken=new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword());
            authenticationManager.authenticate(authToken);
            UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());
            String role = user.getAuthorities().iterator().next().getAuthority();
            String jwt = jwtUtil.generateToken(user.getUsername(), role);
            return ResponseEntity.ok(new AuthResponseDTO(jwt,role,user.getUsername()));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }


}
