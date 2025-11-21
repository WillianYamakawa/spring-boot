package com.tarefa.aula.jwt;

import com.tarefa.aula.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    private static final long EXPIRACAO = 1000 * 60 * 60 * 24;

    public static String gerarToken(int id, String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .claim("id", id)
                .setIssuer("trabalho")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(JwtKey.getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(JwtKey.getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }
    }
}
