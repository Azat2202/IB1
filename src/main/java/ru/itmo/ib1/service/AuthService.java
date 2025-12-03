package ru.itmo.ib1.service;

import ru.itmo.ib1.controller.domain.AuthRequestDto;
import ru.itmo.ib1.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public String getJwt(AuthRequestDto req) {
        var token = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authManager.authenticate(token);
        return jwtUtils.generateToken(req.getUsername());
    }
}
