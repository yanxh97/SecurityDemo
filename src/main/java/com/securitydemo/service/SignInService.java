package com.securitydemo.service;


import com.securitydemo.doclass.SignInRequestBody;
import com.securitydemo.doclass.SignInResponseBody;
import org.springframework.http.ResponseEntity;

public interface SignInService {
    public ResponseEntity<SignInResponseBody> signIn(SignInRequestBody requestBody);
}
