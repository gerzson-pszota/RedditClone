package com.greenfoxacademy.reddit.Services.PostService;

import com.greenfoxacademy.reddit.Models.Post;
import com.greenfoxacademy.reddit.Models.User;
import com.greenfoxacademy.reddit.Repositories.PostRepository;
import com.greenfoxacademy.reddit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Post> listingTopTenByPopularity() {
        List<Post> posts = postRepository.findTop10ByOrderByVoteDesc();
        return posts;
    }

    @Override
    public List<Post> listingAllByPopularity() {
        List<Post> posts = postRepository.findAllByOrderByVoteDesc();
        return posts;
    }

    @Override
    public void submitPost(Post post, String username) {
        User user = userRepository.findByUsername(username);
        post.setAssignedTo(user);
        postRepository.save(post);
    }

    @Override
    public void upVote(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        post.upVote();
        postRepository.save(post);
    }

    @Override
    public void downVote(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        if (post.getVote() > 0) {
            post.downVote();
            postRepository.save(post);
        }
    }

    @Override
    public void upVoteInAllPosts(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        post.upVote();
        postRepository.save(post);
    }


    @Override
    public void downVoteInAllPosts(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        if (post.getVote() > 0) {
            post.downVote();
            postRepository.save(post);
        }
    }
}