package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private int id;
    private String name;
    private String userName;
    private String email;
}
