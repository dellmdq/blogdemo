package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
       return userRepository.save(user);
    }

    public List<User> saveList(List<User> userList){
        return userRepository.saveAll(userList);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public String delete(int id){
        userRepository.deleteById(id);
        return "User Id: " + id + " deleted.";
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow();
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setEnabled(user.isEnabled());

        return userRepository.save(existingUser);
    }

}
