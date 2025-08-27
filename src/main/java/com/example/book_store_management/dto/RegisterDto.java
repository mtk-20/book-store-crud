package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDto {

    private String name;
    private String userName;
    private String password;
    private String email;
}
