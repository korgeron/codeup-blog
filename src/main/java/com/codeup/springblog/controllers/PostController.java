package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
    public PostRepository pr;

    public PostController (PostRepository postRepository) {
        this.pr = postRepository;
    }

    //THIS HANDLES THE VIEWING OF ALL POST
    @GetMapping("/posts")
    public String viewAllPost(Model model){
        List<Post> posts = pr.findAll();
        model.addAttribute("posts",posts);
        return "/posts/index";
    }

    //THIS HANDLES THE DELETION OF A POST / PASSES ID TO EDIT METHOD / GRABS INDIVIDUAL POST TO DISPLAY ON SHOW PAGE
    @PostMapping("/posts")
    public String postMethods(Long delete, Long edit,Long show, HttpSession session, Model model){

        if (show != null) {
            Post post = pr.getById(show);
            model.addAttribute("post", post);
            return "/posts/show";
        }

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
    public String singleAdPage(){
        return "/posts/show";
    }
//    @GetMapping("/posts/{id}")
//    public String postByIdPage(@PathVariable long id, Model model){
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post(1L, "Post 1", "This is my post 1"));
//        posts.add(new Post(2L, "Post 2", "This is my post 2"));
//        posts.add(new Post(3L, "Post 3", "This is my post 3"));
//
//        model.addAttribute("post", posts.get((int)id - 1));
//
//        return "/posts/show";
//    }

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
}
