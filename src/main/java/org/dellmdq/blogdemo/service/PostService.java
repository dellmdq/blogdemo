package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(int postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post with " + postId + " id not found"));
    }

    @Transactional
    public Post add(Post post) {
        return postRepository.save(post);
    }

}
