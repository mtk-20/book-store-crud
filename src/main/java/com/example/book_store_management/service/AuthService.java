package com.example.book_store_management.service;

import com.example.book_store_management.dto.LoginDto;
import com.example.book_store_management.dto.RegisterDto;
import com.example.book_store_management.entity.Role;
import com.example.book_store_management.entity.User;
import com.example.book_store_management.exception.EmailAlreadyExistException;
import com.example.book_store_management.exception.UserNameAlreadyExistException;
import com.example.book_store_management.repository.RoleRepo;
import com.example.book_store_management.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        Authentication authed = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authed);
        return "Login Success.";
    }

    @Transactional
    public void register(RegisterDto registerDto, String roleName) {
        if (userRepo.existsByUserName(registerDto.getUserName())) {
            throw new UserNameAlreadyExistException("Username already exists!");
        }
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyExistException("Email already exists!");
        }
        Role userRole = roleRepo.findByName("ROLE_" + roleName).orElseThrow(() -> new IllegalStateException("Role not found!"));
        User newUser = new User(
                registerDto.getName(),
                registerDto.getUserName(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getEmail());
        newUser.addRole(userRole);
        userRepo.save(newUser);
    }
}
