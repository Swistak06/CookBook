package com.swistak.CookBook.config;

import com.swistak.CookBook.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    @Autowired
    RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void createStartupValues(){
        roleService.addStartupRoles();
    }
}
