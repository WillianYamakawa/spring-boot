package com.tarefa.aula.jwt;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtKey {
    public static Key getKey() {
        return Keys.hmacShaKeyFor("chave-secreta-muito-grande-para-jwt-256!".getBytes());
    }
}

