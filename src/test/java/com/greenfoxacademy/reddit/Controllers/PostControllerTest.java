package com.greenfoxacademy.reddit.Controllers;

import com.greenfoxacademy.reddit.Models.Post;
import com.greenfoxacademy.reddit.Services.PostService.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void index() {
        List<Post> posts = new ArrayList<>();
        when(postService.listingTopTenByPopularity()).thenReturn(posts);

        Model model = mock(Model.class);
        String viewName = postController.index(model);

        assertEquals("index", viewName);
        verify(model).addAttribute("posts", posts);
        verify(postService).listingTopTenByPopularity();
    }

    @Test
    void all() {
        List<Post> posts = new ArrayList<>();
        when(postService.listingAllByPopularity()).thenReturn(posts);

        Model model = mock(Model.class);
        String viewName = postController.all(model);

        assertEquals("allPosts", viewName);
        verify(model).addAttribute("posts", posts);
        verify(postService).listingAllByPopularity();
    }

    @Test
    void submitGet() {
        String viewName = postController.submitGet(null);

        assertEquals("submit", viewName);
    }

    @Test
    void submitPost() {
        Post post = new Post();
        String username = "testUser";

        String redirectUrl = postController.submitPost(post, username);

        assertEquals("redirect:/", redirectUrl);
        verify(postService).submitPost(post, username);
    }
}
