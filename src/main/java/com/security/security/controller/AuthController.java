package com.security.security.controller;

import com.security.security.entity.MasterUsers;
import com.security.security.security.JwtUtil;
import com.security.security.repository.UserRepository;
import com.security.security.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        String name = userData.get("name");
        String email = userData.get("email");
        String phone = userData.get("phone");
        String rawPassword = userData.get("password");

        // Check if email already exists
        if (userRepository.findByUserEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email already exists"));
        }

        // Create and save new user
        MasterUsers user = new MasterUsers();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setPhoneNumber(phone);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setIsActive(true);
        user.setFailedAttempts(0);
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(String.valueOf(LocalDateTime.now()));

        MasterUsers savedUser = userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(savedUser.getUserEmail());

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signup successful");
        response.put("token", token);
        response.put("user", Map.of(
                "id", savedUser.getId(),
                "name", savedUser.getUserName(),
                "email", savedUser.getUserEmail(),
                "phone", savedUser.getPhoneNumber(),
                "role", "user"  // Hardcoded role; replace if role logic exists
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // Login (Generate JWT)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
