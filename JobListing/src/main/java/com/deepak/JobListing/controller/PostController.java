package com.deepak.JobListing.controller;

import com.deepak.JobListing.model.Post;
import com.deepak.JobListing.repository.PostRepository;
import com.deepak.JobListing.repository.SearchPostRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchPostRepository searchRepo;
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response){

    }
    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return repo.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return repo.findAll();
    }
    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return searchRepo.findByText(text);
    }

}
