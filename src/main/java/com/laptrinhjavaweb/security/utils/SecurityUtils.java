package com.laptrinhjavaweb.security.utils;

import com.laptrinhjavaweb.dto.MyUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) (SecurityContextHolder
                .getContext()).getAuthentication().getPrincipal();
    }

    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }

    /**
     * Check role of current user logged
     * @param role
     * @return
     */
    public static boolean isRole(String role, MyUserDetail userDetails) {
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            if (grantedAuthority.getAuthority().contains(role)) {
                return true;
            }
        }
        return false;
    }

    public static MyUserDetail getMyUserDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyUserDetail) authentication.getPrincipal();
    }
}
