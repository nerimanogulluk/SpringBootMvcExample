package com.example.springbootmvcexample.Services;

import com.example.springbootmvcexample.Repositories.RoleRepository;
import com.example.springbootmvcexample.User;
import com.example.springbootmvcexample.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    // Servis katmanında yapılacak işlemlerin amacı veritabanındaki
    // kullanıcı bilgileri ile kullanıcı formu tarafından gelecek olan
    // bilgilerin karşılaştırılması, kullanıcı kayıt sayfasından gelecek bilgilerin ise kayıt edilmesi sağlamaktadır
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

}
