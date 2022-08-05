package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


@Controller
public class PostController {
    private PostRepository pr;

    public PostController (PostRepository postRepository) {
        this.pr = postRepository;
    }

    //THIS HANDLES THE VIEWING OF ALL POST
    @GetMapping("/posts")
    public String viewAllPost(Model model){
        List<Post> posts = pr.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts",posts);
        return "/posts/index";
    }

    //THIS HANDLES THE DELETION OF A POST / PASSES ID TO EDIT METHOD / GRABS INDIVIDUAL POST TO DISPLAY ON SHOW PAGE
    @PostMapping("/posts")
    public String postMethods(Long delete, Long edit,HttpSession session){
        if (edit != null) {
            session.setAttribute("edit", edit);
            return "/posts/edit";
        }

        if (delete != null) {
            Post post = pr.getById(delete);
            pr.delete(post);
        }
        return "redirect:/posts";
    }

    //THIS HANDLES THE SINGLE AD PAGE
    @GetMapping("/posts/show")
    public String singlePostPage(){
        return "/posts/show";
    }

    //THESE HANDLE THE CREATION OF POSTS
    @GetMapping("/post/create")
    public String createPostPage(){

        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost( String title, String body){
        if (title != null && body != null) {
            pr.save(new Post(title, body));
        }
        return "redirect:/posts";
    }

    //THESE HANDLE THE EDITING OF POST BY ID
    @GetMapping("/post/edit")
    public String editPostPage(){
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost( String title, String body, HttpSession session){
        Long id = (Long) session.getAttribute("edit");
        if (title != null && body != null) {
            pr.editPost(title, body, id);
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable Long id, Model model){
    Post p = pr.getById(id);
    model.addAttribute("post", p);
        return "/posts/show";
    }
}
