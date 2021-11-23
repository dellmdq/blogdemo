package org.dellmdq.blogdemo.repository;

import org.dellmdq.blogdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameAndDeleteUserAtIsNull(String name);

    List<User> findByDeleteUserAtIsNull();
}
