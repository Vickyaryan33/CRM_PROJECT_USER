package com.example.Payload;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String mobile;
}
