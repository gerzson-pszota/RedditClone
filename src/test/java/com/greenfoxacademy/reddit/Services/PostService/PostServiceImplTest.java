package com.greenfoxacademy.reddit.Services.PostService;

import com.greenfoxacademy.reddit.Models.Post;
import com.greenfoxacademy.reddit.Models.User;
import com.greenfoxacademy.reddit.Repositories.PostRepository;
import com.greenfoxacademy.reddit.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listingTopTenByPopularity() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findTop10ByOrderByVoteDesc()).thenReturn(posts);

        List<Post> result = postService.listingTopTenByPopularity();

        assertEquals(posts, result);
        verify(postRepository).findTop10ByOrderByVoteDesc();
    }

    @Test
    void listingAllByPopularity() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findAllByOrderByVoteDesc()).thenReturn(posts);

        List<Post> result = postService.listingAllByPopularity();

        assertEquals(posts, result);
        verify(postRepository).findAllByOrderByVoteDesc();
    }

    @Test
    void submitPost() {
        Post post = new Post();
        String username = "testUser";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);

        postService.submitPost(post, username);

        assertEquals(user, post.getAssignedTo());
        verify(postRepository).save(post);
    }

    @Test
    void upVote() {
        Long id = 1L;
        Post post = new Post();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        postService.upVote(id);

        assertEquals(1, post.getVote());
        verify(postRepository).save(post);
    }

    void downVoteWhenVoteCountIsZero() {
        Long id = 1L;
        Post post = new Post();
        post.setVote(0);
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        postService.downVote(id);

        assertEquals(0, post.getVote());
        verify(postRepository, never()).save(post);
    }

    @Test
    void upVoteInAllPosts() {
        Long id = 1L;
        Post post = new Post();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        postService.upVoteInAllPosts(id);

        assertEquals(1, post.getVote());
        verify(postRepository).save(post);
    }
}
