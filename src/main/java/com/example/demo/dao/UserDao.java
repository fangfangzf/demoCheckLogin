package com.example.demo.dao;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
