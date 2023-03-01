package com.spring.blog.services.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.blog.models.User;
import com.spring.blog.repositories.UserRepository;
import com.spring.blog.services.MyUserDetailsService;

@Service
public class UserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setMiddleName(user.getMiddleName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    //
    /*
     * @Override
     * public UserDetails loadUserByUsername(String username) throws
     * UsernameNotFoundException {
     * 
     * User user = userRepository.findByUserName(username);
     * if (user == null) {
     * throw new UsernameNotFoundException("Usuário não encontrado");
     * }
     * 
     * // Cria uma lista de permissões
     * 
     * List<String> roles = user.getRoles();
     * String[] rolesArray = roles.toArray(new String[roles.size()]);
     * List<GrantedAuthority> authorities =
     * AuthorityUtils.createAuthorityList(rolesArray);
     * 
     * return new org.springframework.security.core.userdetails.User(
     * user.getUserName(),
     * user.getPassword(),
     * true,
     * true,
     * true,
     * true,
     * authorities);
     * }
     */
}
