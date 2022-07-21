package com.sloths.movie_review_project.auth.filters;

import com.sloths.movie_review_project.auth.entities.MyUserDetails;
import com.sloths.movie_review_project.auth.services.MyUserDetailsService;
import com.sloths.movie_review_project.auth.services.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = null;
        String token = null;

        if(Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith(BEARER)) {
            token = authorizationHeader.substring(7);
            username = jwtUtil.getUsername(token);
        }

        if(Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            MyUserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
