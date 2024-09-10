package com.securitydemo.controller;

import com.securitydemo.doclass.SignInRequestBody;
import com.securitydemo.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignInController {
    @Autowired
    SignInService signInService;

    @GetMapping("/singin")
    public ResponseEntity<?> getSingin(){
        return new ResponseEntity<String>("You are using the wrong http method!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> postUserNamePassword(@RequestBody SignInRequestBody signInRequestBody){
        return signInService.signIn(signInRequestBody);
    }
}
