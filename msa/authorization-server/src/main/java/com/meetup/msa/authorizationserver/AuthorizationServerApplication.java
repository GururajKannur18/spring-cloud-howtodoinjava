package com.meetup.msa.authorizationserver;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meetup.msa.authorizationserver.persistence.UserEntity;
import com.meetup.msa.authorizationserver.repository.UserRepository;

@SpringBootApplication
public class AuthorizationServerApplication implements CommandLineRunner {
    private UserRepository userRepository;

    @Autowired
    public AuthorizationServerApplication(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        // @formatter:off
        final UserEntity user1 = new UserEntity()
                .firstName("Chandra")
                .lastName("V")
                .email("cv@yopmail.com");
        final UserEntity user2 = new UserEntity()
                .firstName("Larry")
                .lastName("Page")
                .email("lp@yopmail.com");
        final UserEntity user3 = new UserEntity()
                .firstName("Sergey")
                .lastName("Brin")
                .email("sb@yopmail.com");
        // @formatter:on

       userRepository.save(Arrays.asList(user1, user2, user3));
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
