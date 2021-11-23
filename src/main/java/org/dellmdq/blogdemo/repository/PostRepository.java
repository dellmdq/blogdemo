package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByOrderByCreationDateDesc();

    List<Post> findAllByDeleteAtOrderByCreationDateDesc(String deletedAt);

    Post findByTitle(String title);

    List<Post> findByCategory_Title(String category);

    Post findByTitleAndCategory_Title(String title, String category);

    @Query(value = "SELECT p.id, p.title, p.image, c.title, p.creation_date FROM post p \n" +
            "INNER JOIN category c ON c.id = p.category_id", nativeQuery = true)
    List<Post> findAllBasic();

}
