package com.example.book_store_management.service;

import com.example.book_store_management.dto.UserDto;
import com.example.book_store_management.dto.UserUpdateDto;
import com.example.book_store_management.entity.User;
import com.example.book_store_management.exception.EmailAlreadyExistException;
import com.example.book_store_management.exception.UserNameAlreadyExistException;
import com.example.book_store_management.mapper.CustomUtils;
import com.example.book_store_management.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomUtils customUtils;

    public List<UserDto> getAllUser() {
        return userRepo.findAll().stream().map(customUtils::toUserDto).collect(Collectors.toUnmodifiableList());
    }

    public UserDto getUserById(int id) {
        return customUtils.toUserDto(userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User id not found.")));
    }

    @Transactional
    public UserDto updateUser(int id, UserUpdateDto userUpdateDto) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User id not found."));

        if (userUpdateDto.getUserName() != null && !userUpdateDto.getUserName().isEmpty()) {
            user.setName(userUpdateDto.getName());
        }

        if (userUpdateDto.getUserName() != null && !userUpdateDto.getUserName().isEmpty() && !user.getUserName().equals(userUpdateDto.getUserName())) {
            if (userRepo.existsByUserNameAndIdNot(userUpdateDto.getUserName(), id)) {
                throw new UserNameAlreadyExistException("Username already taken.");
            }
            user.setUserName(userUpdateDto.getUserName());
        }

        if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().isEmpty() && !user.getEmail().equals(userUpdateDto.getEmail())) {
            if (userRepo.existsByEmailAndIdNot(userUpdateDto.getEmail(), id)) {
                throw new EmailAlreadyExistException("Email already taken.");
            }
            user.setEmail(userUpdateDto.getEmail());
        }

        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

        User updatedUser = userRepo.save(user);

        return new UserDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getUserName(),
                updatedUser.getEmail());
    }

    @Transactional
    public void deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalStateException("No such user id.");
        }
        userRepo.deleteById(id);
    }
}
