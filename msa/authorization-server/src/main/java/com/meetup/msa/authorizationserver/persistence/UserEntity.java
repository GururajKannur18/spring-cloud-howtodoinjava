package com.meetup.msa.authorizationserver.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    public UserEntity firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntity lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserEntity email(final String email) {
        this.email = email;
        return this;
    }
}
