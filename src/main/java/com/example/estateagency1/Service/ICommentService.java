package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.Comment;

import java.util.List;

public interface ICommentService {
    void saveComment(Comment comment);
    List<Comment> getAllCommentByBlog(Blog blog);
}
