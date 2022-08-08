package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Tag;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.TagsRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
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
    private EmailService emailService;

    public PostController(PostRepository postRepository, UserRepository userRepository, TagsRepository tagsRepository, EmailService emailService) {
        this.pr = postRepository;
        this.ur = userRepository;
        this.tr = tagsRepository;
        this.emailService = emailService;
    }

    //THIS HANDLES THE VIEWING OF ALL POST
    @GetMapping("/posts")
    public String viewAllPost(Model model) {
        List<Post> posts = pr.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    //THIS HANDLES THE DELETION OF A POST / PASSES ID TO EDIT METHOD
    @PostMapping("/posts")
    public String postMethods(Long delete, String newTag, Long edit) {

        if (edit != null) {
            System.out.println(edit);
            return "redirect:/post/edit";
        }

        if (newTag != null) {
            tr.save(new Tag(newTag));
            return "redirect:/post/create";
        }

        if (delete != null) {
            pr.deletePost(delete);
        }
        return "redirect:/posts";
    }

    //THESE HANDLE THE EDITING OF A POST
    @GetMapping("/post/{id}/edit")
    public String editPostPage(@PathVariable Long id ,Model model){
        model.addAttribute("post", pr.getById(id));
        model.addAttribute("tags", tr.findAll());
        return "/posts/create";
    }

    @PostMapping("/post/{id}/edit")
    public String editPost (@RequestParam List<Long> tag, @ModelAttribute Post post){
        User user = ur.getById(1L);
        List<Tag> t = new ArrayList<>();
        for (Long id : tag) {
            Tag allTags = tr.getById(id);
            t.add(allTags);
        }
        post.setUser(user);
        post.setTags(t);
        pr.save(post);
        return "redirect:/posts";
    }

    //THIS HANDLES THE SINGLE AD PAGE
    @GetMapping("/posts/show")
    public String singlePostPage() {
        return "/posts/show";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable Long id, Model model) {
        Post p = pr.getById(id);
        model.addAttribute("post", p);
        return "/posts/show";
    }

    //THESE HANDLE THE CREATION OF POSTS
    @GetMapping("/post/create")
    public String createPostPage(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("tags", tr.findAll());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam List<Long> tag, @ModelAttribute Post post) {

        User user = ur.getById(1L);
        List<Tag> t = new ArrayList<>();
        for (Long id : tag) {
            Tag allTags = tr.getById(id);
            t.add(allTags);
        }
        post.setTags(t);
        post.setUser(user);
        pr.save(post);
        emailService.prepareAndSend(user, post.getTitle(), post.getBody());

        return "redirect:/posts";
    }
}
