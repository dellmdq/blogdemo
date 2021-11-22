package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByOrderByCreationDateDesc();

    Post findByTitle(String title);

    Post findByCategory_Title(String category);

    Post findByTitleAndCategory_Title(String title, String category);

}
