package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.UserDto;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User(userDto, Calendar.getInstance());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        roleService.addUserRole(user);
        return userRepository.save(user);
    }

}
