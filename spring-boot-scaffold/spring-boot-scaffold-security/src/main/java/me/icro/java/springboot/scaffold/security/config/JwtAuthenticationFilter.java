package me.icro.java.springboot.scaffold.security.config;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.security.common.Status;
import me.icro.java.springboot.scaffold.security.service.CustomUserDetailsServiceImpl;
import me.icro.java.springboot.scaffold.security.util.JwtUtil;
import me.icro.java.springboot.scaffold.security.util.ResponseUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 认证过滤器
 *
 * @author lin
 * @version v 0.1 2020/3/11
 **/
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private CustomUserDetailsServiceImpl customUserDetailsService;

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (checkIgnores(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String jwt = jwtUtil.getJwtFromRequest(httpServletRequest);
        if (null != jwt) {
            String username = jwtUtil.getUsernameFromJwt(jwt);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            ResponseUtil.renderJson(httpServletResponse, Status.UNAUTHORIZED, null);
        }
    }

    private boolean checkIgnores(HttpServletRequest request) {
        return false;
    }
}
