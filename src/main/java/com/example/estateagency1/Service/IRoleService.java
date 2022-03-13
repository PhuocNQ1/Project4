package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRole();

    Role getRoleById(int id);

    Role getRoleByName(String name);
}
