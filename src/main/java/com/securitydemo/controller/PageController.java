package com.securitydemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping("/hello")
    ResponseEntity<String> getHelloPage(){
        return new ResponseEntity<String>("Hello Security Demo",HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> getAdminPage(){
        return new ResponseEntity<String>("Hello Admin",HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<String> getUserPage(){
        return new ResponseEntity<String>("Hello User",HttpStatus.OK);
    }
}
