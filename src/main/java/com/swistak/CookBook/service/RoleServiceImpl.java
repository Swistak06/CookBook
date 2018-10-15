package com.swistak.CookBook.service;

import com.swistak.CookBook.model.Security.Role;
import com.swistak.CookBook.model.Security.UserRole;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void addStartupRoles(){
        if(roleRepository.findRoleByIdAndAndRoleName(1,"ROLE_ADMIN") == null ||
                roleRepository.findRoleByIdAndAndRoleName(2,"ROLE_USER") == null)
        {
            roleRepository.deleteAll();
            roleRepository.save(new Role(1,"ROLE_ADMIN"));
            roleRepository.save(new Role(2,"ROLE_USER"));
        }
    }

    @Override
    public void addUserRole(User user) {
        user.setUserRoles(new HashSet<UserRole>() {{
            add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
        }});
    }

}
