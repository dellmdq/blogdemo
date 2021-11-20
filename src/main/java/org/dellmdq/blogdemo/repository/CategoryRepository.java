package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByTitle(String title);
}
