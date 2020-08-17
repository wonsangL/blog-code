package com.example.validation;

import com.example.validation.validator.NoSpecialCharacter;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {
    @NotBlank
    @NoSpecialCharacter
    private String name;

    private int age;
}
