package com.securitydemo.service;

import com.securitydemo.config.MySecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Map<String, UserDetails> users;


    public UserDetailsServiceImpl(){
        PasswordEncoder pe = MySecurityConfig.myPasswordEncoder();
        users = new HashMap<>();
        users.put("admin", User.withUsername("admin")
                .password(pe.encode("123456"))
                .authorities("USER", "ADMIN").build());
        users.put("user1", User.withUsername("user1")
                .password(pe.encode("123"))
                .authorities("USER").build());
        users.put("user2", User.withUsername("user2")
                .password(pe.encode("456"))
                .authorities("USER").build());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)){
            UserDetails ud = users.get(username);
            return new User(ud.getUsername(), ud.getPassword(), ud.getAuthorities());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
