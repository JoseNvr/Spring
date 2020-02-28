package com.sanmina.basictemplate.security;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.sanmina.basictemplate.pojo.ApplicationData;
import com.sanmina.basictemplate.pojo.Profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:Y1JaU8VOhF9YKL2FuMOVc8Oi5gOfNKFA}")
    private String secretKey = "Y1JaU8VOhF9YKL2FuMOVc8Oi5gOfNKFA";
    @Value("${security.jwt.token.expire-length:28800000}")
    private long validityInMilliseconds = 28800000; // 8h 28800000
    private String campusAPI = "http://gdl1amwebl01.sanmina.com/WebService_APPM";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, String application, String plant) {
        Claims claims = Jwts.claims().setSubject(username).setAudience(application + "_PLANT_" + plant);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        String[] applicationAndPlant = getApplicationAndPlant(token);
        RestTemplate restTemplate = new RestTemplate();
        String url = campusAPI + "/ProfileApp/ProfileApp/getSiteProfileMenu/" + username + "/" + applicationAndPlant[0]
                + "/" + applicationAndPlant[1];
        ApplicationData applicationData = restTemplate.getForObject(url, ApplicationData.class);
        UserDetails userDetails = new User(username, username, getAuthorities(applicationData.getData().getProfiles()));
        return new UsernamePasswordAuthenticationToken(userDetails, "Test", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String[] getApplicationAndPlant(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getAudience().split("_PLANT_");
    }

    public Collection<? extends GrantedAuthority> getAuthorities(List<Profile> profiles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Profile profile : profiles) {
            authorities.add(new SimpleGrantedAuthority(profile.getName()));
        }
        return authorities;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // e.printStackTrace();
            return false;
        }
    }
}