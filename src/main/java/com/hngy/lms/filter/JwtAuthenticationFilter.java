package com.hngy.lms.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hngy.lms.entity.User;
import com.hngy.lms.repository.UserRepository;
import com.hngy.lms.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get token
        String header=request.getHeader("Authorization");
        if (header == null ||!header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //verify token
        String realToken=header.substring("Bearer ".length()); // (Bearer null)

        /**
         * We start by creating an empty SecurityContext.
         * You should create a new SecurityContext instance instead of using SecurityContextHolder.getContext().setAuthentication(authentication) to avoid race conditions across multiple threads.
         */
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(Objects.isNull(securityContext.getAuthentication())){
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(realToken);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }
    /**
     * parse the token and get user information to packaging UsernamePasswordAuthenticationToken
     * @param realToken
     * @return
     * @throws JsonProcessingException
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String realToken) throws JsonProcessingException {
        HashMap<String,Object> tokenPayload= JWTUtil.parseToken(realToken);
//        if(ObjectUtils.isEmpty(tokenPayload)) {
//            throw new RuntimeException("token payload is empty");
//        }
        User user= (User) userDetailsService.loadUserByUsername((String) tokenPayload.get("username"));
        return new UsernamePasswordAuthenticationToken(user,null,
                Collections.singleton(new SimpleGrantedAuthority(tokenPayload.get("role").toString())));
    }
}
