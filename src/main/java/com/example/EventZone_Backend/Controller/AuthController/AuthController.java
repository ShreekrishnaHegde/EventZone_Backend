package com.example.EventZone_Backend.Controller.AuthController;

import com.example.EventZone_Backend.DTO.AttendeeSignUpRequest;
import com.example.EventZone_Backend.DTO.AuthRequest;
import com.example.EventZone_Backend.DTO.AuthResponse;
import com.example.EventZone_Backend.DTO.HostSignUpRequest;
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
    public ResponseEntity<?> registerAttendee(@RequestBody AttendeeSignUpRequest attendeeSignUpRequest){
        Attendee attendee=attendeeService.register(attendeeSignUpRequest);
        UserDetails userDetails=userDetailsService.loadUserByUsername(attendee.getAttendeeEmail());
        String token=jwtUtil.generateToken(userDetails.getUsername(),"ATTENDEE");
        return ResponseEntity.ok(new AuthResponse(token,attendee.getRole(),attendee.getAttendeeEmail()));
    }

    @PostMapping("/signup/host")
    public ResponseEntity<?> registerHost(@RequestBody HostSignUpRequest hostSignUpRequest){
        Host host=hostService.register(hostSignUpRequest);
        UserDetails userDetails=userDetailsService.loadUserByUsername(host.getHostEmail());
        String token=jwtUtil.generateToken(userDetails.getUsername(),"HOST");
        return ResponseEntity.ok(new AuthResponse(token,host.getRole(),host.getHostEmail()));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            var authToken=new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword());
            authenticationManager.authenticate(authToken);
            UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());
            String role = user.getAuthorities().iterator().next().getAuthority();
            String jwt = jwtUtil.generateToken(user.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(jwt,role,user.getUsername()));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }


}
