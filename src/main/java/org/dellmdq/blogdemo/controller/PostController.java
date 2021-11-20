package org.dellmdq.blogdemo.controller;

import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll(@QueryParam("title") String title,
                             @QueryParam("category") String categoryName){
        return postService.getAll();
    }

    @GetMapping("/{postId}")
    public Post getById(@PathVariable int postId){
        return postService.getById(postId);
    }

    @PostMapping
    public Post add(@RequestBody Post post){
        return postService.add(post);
    }

}
