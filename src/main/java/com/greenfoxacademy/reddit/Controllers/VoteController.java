package com.greenfoxacademy.reddit.Controllers;

import com.greenfoxacademy.reddit.Services.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class VoteController {

    private final PostService postService;

    @Autowired
    public VoteController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/upVote")
    public RedirectView upVote(@RequestParam Long id, @RequestParam String username) {
        postService.upVote(id);
        return new RedirectView("/?username=" + username);
    }

    @GetMapping("/downVote")
    public RedirectView downVote(@RequestParam Long id, @RequestParam String username) {
        postService.downVote(id);
        return new RedirectView("/?username=" + username);
    }

    @GetMapping("/allPosts/upVote")
    public RedirectView upVoteInAllPosts(@RequestParam Long id, @RequestParam String username) {
        postService.upVoteInAllPosts(id);
        return new RedirectView("/allPosts/?username=" + username);
    }

    @GetMapping("/allPosts/downVote")
    public RedirectView downVoteInAllPosts(@RequestParam Long id, @RequestParam String username) {
        postService.downVoteInAllPosts(id);
        return new RedirectView("/allPosts/?username=" + username);
    }
}