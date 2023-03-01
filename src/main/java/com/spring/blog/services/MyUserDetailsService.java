package com.spring.blog.services;

import java.util.List;
import java.util.UUID;
/* 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 */
import com.spring.blog.models.User;

public interface MyUserDetailsService {

    // UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException;

    List<User> findAll();

    User findById(UUID id);

    User save(User obj);

    public void deleteById(UUID id);

    public User update(User user);
}
