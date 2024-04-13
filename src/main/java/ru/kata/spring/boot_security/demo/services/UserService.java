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
public class UserService implements UserDetailsService {

    private UserRepositories userRepositories;

    private RoleRepositories roleRepositories;

    private RoleService roleService;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepositories userRepositories, RoleRepositories roleRepositories, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;
        this.roleService = roleService;


        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) {
        return userRepositories.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s'  not found", username));
        }
        return user;
    }

    @Transactional
    public void save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setRoles(user.getRoles());
        userRepositories.save(user);

    }

    @Transactional
    public void update(User updateUser, Long id) {
        User user = userRepositories.findById(id).get();
        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        user.setRoles(updateUser.getRoles());
        user.setId(id);
        userRepositories.save(user);
    }


    public List<User> showListUser() {
        return userRepositories.findAll();
    }


    @Transactional
    public void removeUser(Long id) {
        userRepositories.deleteById(id);
    }


    public User getUserById(Long id) {
        return userRepositories.findById(id).get();
    }

}
