package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.Role;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IUserService {
    void addUser(User user);

    List<User> getAllUser();

    void updateUser(User user);

    User findUserByUserName(String username);

    User findUserByID(int id);

    List<User> findUserByRole(Role role);

    Page<User> getAllUserPage(Pageable pageable);


}
