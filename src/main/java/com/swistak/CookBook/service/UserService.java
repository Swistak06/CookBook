package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.UserDto;
import com.swistak.CookBook.model.User;

public interface UserService {
    User findByUsername(String username);
    User registerUser(UserDto userDto);
}
