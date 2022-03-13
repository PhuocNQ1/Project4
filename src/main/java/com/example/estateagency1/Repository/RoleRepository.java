package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role getRoleById(int id);
    Role findRoleByName(String name);
}
