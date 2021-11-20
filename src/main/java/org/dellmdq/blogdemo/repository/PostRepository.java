package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
