package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findAll(Pageable pageable);

    List<Blog> findAllByUser(User user);
}
