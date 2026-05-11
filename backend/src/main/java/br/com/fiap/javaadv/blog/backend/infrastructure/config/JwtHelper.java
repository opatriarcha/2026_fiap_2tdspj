package br.com.fiap.javaadv.blog.backend.infrastructure.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {

    private final String SECRET = "CHAVE_FODA_SUPER_SECRRETA_FODA_PACARAI_MEMO";
    private final int EXPIRATION_MS = 86400000; //24hours
    private final int EXPIRATION_REFRESH_TOKEN_MS = 86400000 * 7; //7 days


    public String generateToken( UserDetails userDetails ){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt( new Date())
                .setExpiration( new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername( final String token ){
         return Jwts.parserBuilder()
                 .setSigningKey(this.getSignKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
    }

    public boolean isTokeValid( final String token, final UserDetails userDetails){
        final String username = this.extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before( new Date());

    }

    public String generateRefreshToken( final UserDetails userDetails ){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt( new Date())
                .setExpiration( new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


}
