package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String name);
}
