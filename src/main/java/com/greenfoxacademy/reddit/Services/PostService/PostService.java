package com.greenfoxacademy.reddit.Services.PostService;

import com.greenfoxacademy.reddit.Models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> listingTopTenByPopularity();

    List<Post> listingAllByPopularity();

    void submitPost(Post post, String username);

    void upVote(Long id);

    void downVote(Long id);

    void upVoteInAllPosts(Long id);

    void downVoteInAllPosts(Long id);
}