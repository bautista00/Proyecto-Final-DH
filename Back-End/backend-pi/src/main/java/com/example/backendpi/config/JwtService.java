package com.example.backendpi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="4D635166546A576E5A7234753778214125442A472D4B6150645267556B587032";

    public String extraerUsername(String token){
        return extraerClaim(token,Claims::getSubject);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails)
    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validacionToken(String token,UserDetails userDetails){
        final String username=extraerUsername(token);
        return (username.equals(userDetails.getUsername())) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        return extraerExpiracionToken(token).before(new Date());
    }

    private Date extraerExpiracionToken(String token) {
        return extraerClaim(token,Claims::getExpiration);
    }


    public <T> T extraerClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extraerLosClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    private Claims extraerLosClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();

    }

    private Key getSignIngKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
