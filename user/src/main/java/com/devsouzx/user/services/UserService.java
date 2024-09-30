package com.devsouzx.user.services;

import com.devsouzx.user.model.User;
import com.devsouzx.user.producers.UserProducer;
import com.devsouzx.user.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProducer userProducer;

    @Transactional
    public User save(User user){
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
