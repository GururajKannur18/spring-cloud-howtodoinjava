package com.meetup.msa.authorizationserver.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User {
	private static final long serialVersionUID = 1L;

	public UserPrincipal(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static UserPrincipal instance(final String userName, final String password) {
        return new UserPrincipal(userName, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
