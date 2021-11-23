package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        org.dellmdq.blogdemo.entity.User userEntity = userRepository.findByUserNameAndDeleteUserAtIsNull(userName);
        return new User(userEntity.getUserName(),userEntity.getPassword(),new ArrayList<>());
    }
}
