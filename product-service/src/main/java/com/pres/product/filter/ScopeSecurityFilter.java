package com.pres.product.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
public class ScopeSecurityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        DecodedJWT decodedJWT = JWT.decode(bearerToken.substring(7));
        var scopeClaim = decodedJWT.getClaims().get("scope").toString();
        if (scopeClaim == null || !scopeClaim.contains("product-service")) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.getWriter().write("Scope missing");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
