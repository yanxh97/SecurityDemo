package com.securitydemo.service;

import com.securitydemo.doclass.SignInRequestBody;
import com.securitydemo.doclass.SignInResponseBody;
import com.securitydemo.jwt.JwtCache;
import com.securitydemo.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SignInServiceImpl implements SignInService{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtCache jwtCache;

    @Override
    public ResponseEntity<SignInResponseBody> signIn(SignInRequestBody requestBody) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestBody.getUserName(), requestBody.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("Wrong username or password");
            //return new ResponseEntity<SignInResponseBody>(new SignInResponseBody(401, "Wrong username or password", null), HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authenticate.getPrincipal();
        String jwt = jwtUtils.generateTokenFromUsername(user);
        jwtCache.records.put(user.getUsername(), user);
        System.out.println("User details of " + user.getUsername() + " added to records.");
        return new ResponseEntity<SignInResponseBody>(new SignInResponseBody(200, "Success", jwt), HttpStatus.OK);
    }
}
