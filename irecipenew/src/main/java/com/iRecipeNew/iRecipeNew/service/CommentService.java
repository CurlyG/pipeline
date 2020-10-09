package com.iRecipeNew.iRecipeNew.service;

import com.iRecipeNew.iRecipeNew.domain.Comment;
import com.iRecipeNew.iRecipeNew.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getAllComments();

    Optional<Comment> getCommentById(Long id);

    boolean deleteCommentId(Long id);


    void createComment(Comment comment);


}
