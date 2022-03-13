package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.Comment;
import com.example.estateagency1.Repository.CommentRepository;
import com.example.estateagency1.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateDate(new Date());
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentByBlog(Blog blog) {
        return commentRepository.getAllByBlog(blog);
    }


}
