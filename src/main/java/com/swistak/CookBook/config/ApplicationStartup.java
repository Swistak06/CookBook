package com.swistak.CookBook.config;

import com.swistak.CookBook.service.RoleService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void createStartupValues(){
        roleService.addStartupRoles();
        userService.createStartupUsers();
    }
}
