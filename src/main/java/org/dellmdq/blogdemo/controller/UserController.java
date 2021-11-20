package org.dellmdq.blogdemo.controller;

import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> findAll(){
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/username/{userName}")
    public User findByName(@PathVariable String userName){
        return userService.getUserByName(userName);
    }

    @PostMapping
    public User add(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping()
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable int userId){
        return userService.delete(userId);
    }

    @DeleteMapping("/deleteSoft/{userId}")
    public String deleteSoft(@PathVariable int userId){
        return userService.deleteSoft(userId);
    }

    //taken from https://stackoverflow.com/questions/29988841/spring-rest-and-patch-method
    @PatchMapping("/{id}")
    ResponseEntity<User> patch(@PathVariable int id, @RequestBody Map<String, Object> fields){

        // Sanitize and validate the data
        if (id <= 0 || fields == null || fields.isEmpty() || !fields.get("id").equals(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
        }

        User user = userService.getUserById(id);

        // Does the object exist?
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("id");

        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(User.class, k); // find field in the object class
            field.setAccessible(true);
            ReflectionUtils.setField(field, user, v); // set given field for defined object to value V
        });

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


}
