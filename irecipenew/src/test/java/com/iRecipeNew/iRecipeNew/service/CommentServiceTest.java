package com.iRecipeNew.iRecipeNew.service;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Comment;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.CommentRepository;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;



    private List<Comment> existingComments;
    private Comment comment;
    private String results;
    private String result;
    private Gson gson;
    @BeforeEach
    public void setMockOutput() {
        gson = new Gson();

        existingComments = new ArrayList<>();
        comment = new Comment();
        comment.setAuthorName("Ruzanna");
        comment.setId(5L);
        comment.setText("highly recommended");

        existingComments.add(comment);
        results = gson.toJson(existingComments);
        result = gson.toJson(comment);

        when(commentRepository.findAll()).thenReturn(existingComments);
        when(commentRepository.findById(5L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);


    }

    @Test
    public void getAllCommentsTest(){

        assertEquals(1, commentService.getAllComments().size());

    }

    @Test
    public void getCommentByIdTest(){
        assertEquals(Optional.of(comment), commentService.getCommentById(5L));


        Optional<Comment> test = commentService.getCommentById(5L);
        assert(test.isPresent());

    }



    @Test
    public void deleteCommentByIdTest(){
        given(commentService.deleteCommentId(5L)).willReturn(true);

        commentService.deleteCommentId(5L);
        verify(commentRepository, times(1)).deleteById(5L);

    }

    @Test
    public void createCommentTest(){

        doAnswer((arguments) -> {
            assertEquals(comment, arguments.getArgument(0));
            return null;
        }).when(commentRepository).save(any(Comment.class));
        commentService.createComment(comment);

        verify(commentRepository, times(1)).save(comment);

    }

}
