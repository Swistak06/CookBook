package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
