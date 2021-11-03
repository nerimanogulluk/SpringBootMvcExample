package com.example.springbootmvcexample.Services;

import com.example.springbootmvcexample.User;
import com.example.springbootmvcexample.Repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
//Spring boot transaction yönetimi sağlamak için“@Transactional” keyword’unu
// kullanır. Bu keyword sayesinde veritabanı operasyonlarının yönetimi
// parametreler aracılığıyla spring’e bırakmış oluyoruz. “Bu yönetimi
// class seviyesinde yapılabileceği gibi fonksiyon seviyesine de yapılabilir
@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return null;
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(grantedAuthority);
        }
        return authorities;

    }
// loadUserByUsername Sadece kullanıcı sorgulayıcı metodunda
// userRepository.findByUsername ile kullanıcıyı bulunuyor.
// getAuthorities metodu ile de kullanıcıya ait rollerin veritabanından
// alınmasını sağlayarak Spring’e verilmesi sağlanmış oluyor.
}
