package com.example.springbootmvcexample.Repositories;

import com.example.springbootmvcexample.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
