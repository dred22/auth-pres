package com.pres.order.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
public class ScopeSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken == null) {
            filterChain.doFilter(request, response);
        }

        DecodedJWT decodedJWT = JWT.decode(bearerToken.substring(7));
        var scopeClaim = decodedJWT.getClaims().get("scope").toString();
        if (scopeClaim == null || !scopeClaim.contains("order-service")) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.getWriter().write("Scope missing");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
