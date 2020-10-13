package com.sportshoes.ecom.services;


import com.sportshoes.ecom.security.ApplicationUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CurrentUserService {
    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public Long getIdFromSecurityContext() {
        Object principal = authentication.getPrincipal();
        if(principal instanceof ApplicationUserDetails )
            return ((ApplicationUserDetails) principal).getUserID();
        return 0L;
    }


}
