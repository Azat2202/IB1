package ru.itmo.ib1.controller;

import ru.itmo.ib1.controller.domain.AuthRequestDto;
import ru.itmo.ib1.controller.domain.AuthResponseDto;
import ru.itmo.ib1.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto req) {
        return new AuthResponseDto(authService.getJwt(req));
    }
}
