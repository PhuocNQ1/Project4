package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Repository.BlogRepository;
import com.example.estateagency1.Service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> getAllBlogPage(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> getAllBlogByUser(User user) {
        return blogRepository.findAllByUser(user);
    }

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.getById(id);
    }

    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateDate(new Date());
        blogRepository.save(blog);
    }
}
