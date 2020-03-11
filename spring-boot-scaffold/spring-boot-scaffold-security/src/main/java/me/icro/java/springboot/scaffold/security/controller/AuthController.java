package me.icro.java.springboot.scaffold.security.controller;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.security.common.ApiResponse;
import me.icro.java.springboot.scaffold.security.payload.LoginRequest;
import me.icro.java.springboot.scaffold.security.util.JwtUtil;
import me.icro.java.springboot.scaffold.security.vo.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 认证 Controller，包括用户注册，用户登录请求
 *
 * @author lin
 * @version v 0.1 2020/3/11
 **/
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @param loginRequest 登录请求参数
     * @return 响应
     */
    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJwt(authentication,loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }
}
