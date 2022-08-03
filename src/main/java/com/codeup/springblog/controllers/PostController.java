package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    @GetMapping("/posts")
    public String postsPage(Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "This is my post 1"));
        posts.add(new Post(2L, "Post 2", "This is my post 2"));
        posts.add(new Post(3L, "Post 3", "This is my post 3"));
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postByIdPage(@PathVariable long id, Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "This is my post 1"));
        posts.add(new Post(2L, "Post 2", "This is my post 2"));
        posts.add(new Post(3L, "Post 3", "This is my post 3"));

        model.addAttribute("post", posts.get((int)id - 1));

        return "/posts/show";
    }



//    @GetMapping("/posts/create")
//    public String createPostPage(){
//        return "/posts/create";
//    }
//
//    @PostMapping("/posts/create")
//    public String createdPost(){
//        return null;
//    }
}
