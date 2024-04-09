package ru.kata.spring.boot_security.demo.services;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositories;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;


import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepositories userRepositories;

    private RoleRepositories roleRepositories;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepositories userRepositories, RoleRepositories roleRepositories, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;


        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username){
        return userRepositories.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(String.format("User '%s'  not found",username));
        }
        return user;
    }

    @Transactional
    public boolean save(User user){

        //userRepositories.save(user);
        User user1=userRepositories.findByUsername(user.getUsername());
        if(user1==null) {
            user1.setUsername(user.getUsername());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setEmail(user.getEmail());
            user.setRole(user.getRole());
            userRepositories.save(user1);
            return true;
        }else return false;
    }

    @Transactional
    public User update(User updateUser){
        User user =userRepositories.findById(updateUser.getId()).get();
        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        user.setRole(updateUser.getRole());
        userRepositories.save(user);
        return user;
    }


    public List<User> showListUser() {
        return userRepositories.findAll();
    }


    public void removeUser(Long id) {
        userRepositories.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepositories.findById(id).get();
    }

}
