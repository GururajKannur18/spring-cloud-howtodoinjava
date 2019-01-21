package com.meetup.msa.authorizationserver.api;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationApi {

    @RequestMapping("/user")
    public Principal me(@AuthenticationPrincipal final Principal principal) {
        return principal;
    }
}
