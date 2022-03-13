package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> getAllByBlog(Blog blog);
}
