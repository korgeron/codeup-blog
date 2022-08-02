package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsPage(){
//        return "<h1>These are all of the posts on this page</h1>";
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postByIdPage(@PathVariable int id){
//        return String.format("This is the post for %d", id);
        return "/posts/id";
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostPage(){
//        return "<h1>Create post here!</h1>";
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createdPost(){
//        return "created post";
        return "/posts/index";
    }
}
