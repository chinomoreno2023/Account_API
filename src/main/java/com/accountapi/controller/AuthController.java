package com.accountapi.controller;

import com.accountapi.dto.auth.AuthRequestDto;
import com.accountapi.dto.auth.AuthResponseDto;
import com.accountapi.model.user.User;
import com.accountapi.repository.user.UserRepository;
import com.accountapi.security.JwtTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody AuthRequestDto authDto) {
        User user = userRepository.findByEmailsEmail(authDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenService.generateToken(user.getId());
        return new AuthResponseDto(token);
    }
}
