package com.swistak.CookBook.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(min=3)
    private String username;

    @NotBlank
    @Size(min=12, max=30)
    private String password;

    @NotBlank
    private String email;

    public UserDto() {
    }

    public UserDto(@NotBlank @Size(min = 3) String username, @NotBlank @Size(min = 12, max = 30) String password, @NotBlank String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
