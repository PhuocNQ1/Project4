package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.Models.Role;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Repository.UserRepository;
import com.example.estateagency1.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(new Date());
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
      return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByID(int id) {
        return  userRepository.findUserById(id);
    }

    @Override
    public List<User> findUserByRole(Role role) {
        return userRepository.findByRoles(role);
    }

    @Override
    public Page<User> getAllUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
