package com.greenfoxacademy.reddit.Controllers;

import com.greenfoxacademy.reddit.Models.Post;
import com.greenfoxacademy.reddit.Services.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.listingTopTenByPopularity();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/allPosts")
    public String all(Model model) {
       List<Post> posts = postService.listingAllByPopularity();
        model.addAttribute("posts", posts);
        return ("allPosts");
    }

    @GetMapping("/submit")
    public String submitGet(Post post) {
        return "submit";
    }

    @PostMapping("/submit")
    public String submitPost(Post post, @RequestParam("username") String username) {
        postService.submitPost(post, username);
        return "redirect:/";
    }
}
