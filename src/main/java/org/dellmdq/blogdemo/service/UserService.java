package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passEncoder;

    @Transactional
    public User save(User user){
        User userToRegister = new User();
        userToRegister.setUserName(user.getUserName());
        userToRegister.setPassword(passEncoder.encode(user.getPassword()));
        userToRegister.setFirstName(user.getFirstName());;
        userToRegister.setEmail(user.getEmail());
        userToRegister.setVerificationCode(user.getVerificationCode());
        return userRepository.save(user);
    }

    @Transactional
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
        return userRepository.findByUserName(name);
    }

    @Transactional
    public String delete(int id){
        userRepository.deleteById(id);
        return "User Id: " + id + " deleted.";
    }

    @Transactional
    public String deleteSoft(int userId) {
        User userToSoftDelete = userRepository.findById(userId).orElseThrow();
        userToSoftDelete.setDeleteUserAt(LocalDateTime.now().toString());
        userRepository.save(userToSoftDelete);
        return "User " + userId + " soft deleted. \n" +
                "Deleted at: " + userToSoftDelete.getDeleteUserAt() + ".";
    }

    @Transactional
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setVerificationCode(user.getVerificationCode());

        return userRepository.save(existingUser);
    }

}
