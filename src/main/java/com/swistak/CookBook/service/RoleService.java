package com.swistak.CookBook.service;

import com.swistak.CookBook.model.User;

public interface RoleService {

    void addUserRole(User user);
    void addStartupRoles();
}
