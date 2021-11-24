package org.dellmdq.blogdemo;

import org.dellmdq.blogdemo.entity.User;

import java.util.Optional;

public class Data {

    public static Optional<User> createUser001(){
        return Optional.of(new User(1, "dellmdqtest", "fun123", "", "Erik", "Dell", "erikdell1@gmail.com", "", "" ));
    }
}
