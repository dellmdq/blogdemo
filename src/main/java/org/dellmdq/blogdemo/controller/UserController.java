package org.dellmdq.blogdemo.controller;

import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User add(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping()
    public List<User> findAll(){
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/{userName}")
    public User findByName(@PathVariable String userName){
        return userService.getUserByName(userName);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable int userId){
        return userService.delete(userId);
    }






}
