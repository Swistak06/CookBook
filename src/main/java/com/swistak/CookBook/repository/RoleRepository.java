package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByRoleName(String roleName);
    Role findRoleByIdAndAndRoleName(long id, String roleName);

}
