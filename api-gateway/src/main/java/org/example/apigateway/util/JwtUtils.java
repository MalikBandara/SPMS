package org.example.apigateway.util;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import java.security.Key;
import java.util.Date;

@Configuration
public class JwtUtils {

    @Value("${app.secret}")
    private String secret;

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); // generate key with secret
    }


    // after authenticate generate user come to this method


    public boolean validateJwtToken(String token){
            try{
                Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
                return true;

            }catch (Exception e ){
                return false;
            }
    }



}
