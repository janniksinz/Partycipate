package com.partycipate.Partycipate.security.jwt;

import com.partycipate.Partycipate.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * <authors>
 *     <author> Jannik Sinz - jannik.sinz@ibm.com </author>
 * </authors>
 * */
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${partycipate.app.jwtSecret}")
    private String jwtSecret;

    @Value("${partycipate.app.jwtExpiration}")
    private int jwtExpiration;

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpiration(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String generateJwtToken(Authentication authentication){

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrinciple.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateJwtTokenFromUser(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            logger.error("Invalid JWT Signature -> Message: {}", e);
        } catch (MalformedJwtException e){
            logger.error("Invalid JWT Token -> Message: {}", e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT Token -> Message: {}", e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT Token -> Message: {}", e);
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Boolean validateToken(String token){
        final String username = getUserNameFromToken(token);
        return username != null && !isTokenExpired(token);
    }

}
