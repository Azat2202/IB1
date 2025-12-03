package ru.itmo.ib1.controller.domain;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
