package me.icro.java.springboot.scaffold.security.service;

import me.icro.java.springboot.scaffold.security.vo.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义UserDetails查询
 *
 * @author lin
 * @version v 0.1 2020/3/11
 **/
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // mock
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(1L);
        userPrincipal.setUsername("admin");
        userPrincipal.setPassword("$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq");
        return userPrincipal;
    }
}
