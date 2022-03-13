package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Role;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

    User findUserById(int id);

    List<User> findByRoles(Role role);

    Page<User> findAll(Pageable pageable);

}
