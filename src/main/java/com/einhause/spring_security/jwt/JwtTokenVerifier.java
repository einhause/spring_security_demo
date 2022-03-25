package com.einhause.spring_security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        // remove 'Bearer ' from auth header
        String token = authorizationHeader.replace("Bearer ", "");

        // if no token or it doesnt start with Bearer
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // secret key
            String key = "supersecretkeysupersecretkeysupersecretkeysupersecretkeysupersecretkeysupersecretkey";

            // build the Jws claim
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            // get body of the jwt
            Claims body = claimsJws.getBody();

            // get subject (username) of jwt
            String username = body.getSubject();

            // get authorities claim (List<Map<String, String>>)
            var authorities = (List<Map<String, String>>) body.get("authorities");

            // mapping each authority to a SimpleGrantedAuthority
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            // creating a new auth token with username, password, and simpleGrantedAuthorities
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            // config the authentication method
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException jwtException) {
            throw new IllegalStateException(String.format("Token: %s is invalid!", token));
        }

    }
}
