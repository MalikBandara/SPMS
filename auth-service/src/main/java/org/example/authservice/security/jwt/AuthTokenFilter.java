package org.example.authservice.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.authservice.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    private String parseJwtFromHeader(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        if (StringUtils.hasText(authorization)&& authorization.startsWith("Bearer ")){
            return authorization.substring(7);
        }else{
            return null;
        }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
        String jwtToken = parseJwtFromHeader(request);

        if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)){
            String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
    }catch (Exception e ){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"Invalid authorization header or check the Postman method.\"}");
        response.getWriter().flush();  // Ensures response is sent
        return;
    }

    filterChain.doFilter(request , response);

    }
}
