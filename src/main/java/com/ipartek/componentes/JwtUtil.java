package com.ipartek.componentes;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	 private final String SECRET_KEY = "Del_barco_de_Chanquete,No_nos_moveran...";
		
	 public String generateToken(String username, String role) {
	        return Jwts.builder()
	                .setSubject(username)
	                .claim("role", role)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) 
	                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
	                .compact();
	 }
	 
	 public Claims extractClaims(String token) {
	        return Jwts.parserBuilder()
	                   .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
	                   .build()
	                   .parseClaimsJws(token)
	                   .getBody();
	 }

	 

}
