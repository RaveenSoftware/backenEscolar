package co.edu.udes.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "JWT1234567890JWT1234567890JWT123"; // mínimo 32 bytes para HS256
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 día en milisegundos

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generar token JWT con nombre de usuario y tiempo de expiración
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraer nombre de usuario desde el token
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validar token: correcto + no expirado + pertenece al usuario
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Exponer método público para validar expiración si necesitas usarlo fuera
    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // Método auxiliar para extraer todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
