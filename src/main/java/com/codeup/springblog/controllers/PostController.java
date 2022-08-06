package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Tag;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.TagsRepository;
import com.codeup.springblog.repos.UserRepository;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class PostController {
    private PostRepository pr;
    private UserRepository ur;
    private TagsRepository tr;

    public PostController (PostRepository postRepository, UserRepository userRepository, TagsRepository tagsRepository) {
        this.pr = postRepository;
        this.ur = userRepository;
        this.tr = tagsRepository;
    }

    //THIS HANDLES THE VIEWING OF ALL POST
    @GetMapping("/posts")
    public String viewAllPost(Model model){
        List<Post> posts = pr.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts",posts);
        return "/posts/index";
    }

    //THIS HANDLES THE DELETION OF A POST / PASSES ID TO EDIT METHOD
    @PostMapping("/posts")
    public String postMethods(Long delete, Long edit,HttpSession session, String newTag){
        if (edit != null) {
            session.setAttribute("edit", edit);
            return "/posts/edit";
        }

        if (newTag != null) {
            tr.save(new Tag(newTag));
            return "redirect:/post/create";
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
    public String createPostPage(Model model){
        model.addAttribute("tags", tr.findAll());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(String title, String body,@RequestParam List<Long> tag){
        try {
            if (title != null && body != null) {
                User user = ur.getById(1L);
                List<Tag> t = new ArrayList<>();
                for (Long id : tag) {
                    Tag allTags = tr.getById(id);
                    t.add(allTags);
                }

                System.out.println(tag);
                Post p = new Post(title, body, user, t);
                pr.save(p);
            }
        } catch (RuntimeException re){
            throw new RuntimeException("whats going on here?", re);
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
