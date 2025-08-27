package com.example.book_store_management;

import com.example.book_store_management.entity.Role;
import com.example.book_store_management.entity.User;
import com.example.book_store_management.repository.RoleRepo;
import com.example.book_store_management.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreManagementApplication {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    @Profile("dev")
    public CommandLineRunner commandLineRunner() {
        return args -> {

//            User admin = new User(1, "john doe", "john", passwordEncoder.encode("john"), "john@gmail.com");
//            User user1 = new User(2, "mary jane", "mary", passwordEncoder.encode("mary"), "mary@gmail.com");
//
//            Role adminRole = new Role();
//            adminRole.setName("ROLE_ADMIN");
//            Role userRole = new Role();
//            userRole.setName("ROLE_USER");
//
//            admin.addRole(adminRole);
//            user1.addRole(userRole);
//
//            userRepo.save(admin);
//            userRepo.save(user1);

            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepo.save(adminRole);
            }
            if (roleRepo.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepo.save(userRole);
            }

            if (userRepo.findByUserName("john").isEmpty()) {
                User admin = new User("john doe", "john", passwordEncoder.encode("john"), "john@gmail.com");
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(roleRepo.findByName("ROLE_ADMIN").get());
                admin.setRoles(adminRoles);
                userRepo.save(admin);
            }
            if (userRepo.findByUserName("mary").isEmpty()) {
                User user1 = new User("mary jane", "mary", passwordEncoder.encode("mary"), "mary@gmail.com");
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(roleRepo.findByName("ROLE_USER").get());
                user1.setRoles(userRoles);
                userRepo.save(user1);
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(BookStoreManagementApplication.class, args);
    }

}
