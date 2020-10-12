package com.sportshoes.ecom.security;

import com.sportshoes.ecom.entity.Customers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
@Component
public class JWTUtil {
    private final String mySecretKey="my_secret_key";
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(mySecretKey).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //generating new token
    public String generateToken(UserDetails customer) {
        HashMap<String, Object> map = new HashMap<>();
        return createToken(map, customer.getUsername());
    }

    private String createToken(HashMap<String, Object> map, String emailId) {
        return Jwts.builder().setClaims(map)
                .setSubject(emailId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*60*1000*60*10))
                .signWith(SignatureAlgorithm.HS256, mySecretKey).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String extractedUsername = extractUserName(token);
        String actualUsername = userDetails.getUsername();
        String token1 = token;
        boolean expired = isTokenExpired(token);
        return actualUsername.equals(extractedUsername) && !isTokenExpired(token);
    }
}
