package com.example.springoauth2.repositories;

import com.example.springoauth2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);

}
