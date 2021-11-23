package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAll(String deletedAt) {
        return postRepository.findAllByDeleteAtOrderByCreationDateDesc(deletedAt);
    }

    public Post getById(int postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post with " + postId + " id not found"));
    }

    @Transactional
    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Post getByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> getByCategoryName(String category) {
        return postRepository.findByCategory_Title(category);
    }

    public Post getByTitleAndCategoryName(String title, String category) {
        return postRepository.findByTitleAndCategory_Title(title, category);
    }

    public Post softDelete(int id) {
        Post softDeletedPost = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post with " + id + " id not found"));
        softDeletedPost.setDeleteAt(LocalDateTime.now().toString());
        return postRepository.save(softDeletedPost);
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
