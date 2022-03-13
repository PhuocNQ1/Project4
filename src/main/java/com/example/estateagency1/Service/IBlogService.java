package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> getAllBlog();

    Page<Blog> getAllBlogPage(Pageable pageable);

    List<Blog> getAllBlogByUser(User user);

    Blog getBlogById(int id);

    void saveBlog(Blog blog);


}
