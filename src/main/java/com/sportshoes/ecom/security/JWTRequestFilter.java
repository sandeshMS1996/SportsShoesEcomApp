package com.sportshoes.ecom.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService service;
    @Autowired
    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Autowired
    public void setApplicationUserDetails(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Entering JWT Filter");
        String header = httpServletRequest.getHeader("Authorization");
        String uname = null;
        String token = null;
        if(header != null && header.startsWith("Bearer ")) {
             token= header.substring(7);
            uname = jwtUtil.extractUserName(token);
        }
        if(uname != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.service.loadUserByUsername(uname);
            boolean validateToken = jwtUtil.validateToken(token, userDetails);

            logger.debug("Is valid token + " + validateToken);
            if(jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        logger.debug("End of JUTIl Filter for " + uname + " with token " + token );
        logger.debug("Exiting JWT filter");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
