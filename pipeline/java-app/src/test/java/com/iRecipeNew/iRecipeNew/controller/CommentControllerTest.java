package com.iRecipeNew.iRecipeNew.controller;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Comment;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.CommentRepository;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import com.iRecipeNew.iRecipeNew.service.CommentServiceImpl;
import com.iRecipeNew.iRecipeNew.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommentRepository commentRepository;

    @MockBean
    private CommentServiceImpl commentService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(commentRepository).isNotNull();
        assertThat(commentService).isNotNull();
    }


    private List<Comment> existingComments;
    private Comment comment;
    private String results;
    private String result;
    private Gson gson;

    @BeforeEach
    public void setUpBeforeClass() {

        gson = new Gson();

        existingComments = new ArrayList<>();
        comment = new Comment();
        comment.setAuthorName("Ruzanna");
        comment.setId(5L);
        comment.setText("highly recommended");

        existingComments.add(comment);
        results = gson.toJson(existingComments);
        result = gson.toJson(comment);


    }

    @Test
    public void getCommentsTest() throws Exception {
        given(commentService.getAllComments()).willReturn(existingComments);

        mockMvc.perform(
                get("http://localhost:8080/api/v1/comments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(results));

    }


    @Test
    public void getCommentByIdTest() throws Exception {
        given(commentService.getCommentById(5L)).willReturn(Optional.of(comment));

        mockMvc.perform(get("http://localhost:8080/api/v1/comments/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));

    }

    @Test
    public void getCommentByIncorrectIdTest() throws Exception {
        given(commentService.getCommentById(5L)).willReturn(Optional.of(comment));

        mockMvc.perform(get("http://localhost:8080/api/v1/comments/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void createCommentTest() throws Exception{

        mockMvc.perform(post("http://localhost:8080/api/v1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isCreated());

    }


    @Test
    public void deleteCommentByIdTest() throws Exception {
        given(commentService.deleteCommentId(5L)).willReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/v1/comments/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteCommentByIncorrectIdTest() throws Exception {
        given(commentService.deleteCommentId(55L)).willReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/v1/comments/55")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}
