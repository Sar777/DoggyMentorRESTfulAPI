package io.github.doggymentor.controller;

import io.github.doggymentor.security.facade.SecurityContextAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final SecurityContextAuthenticationFacade facade;

    @Autowired
    public UserController(final SecurityContextAuthenticationFacade facade) {
        this.facade = facade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(facade.getAuthentication(), HttpStatus.OK);
    }
}
