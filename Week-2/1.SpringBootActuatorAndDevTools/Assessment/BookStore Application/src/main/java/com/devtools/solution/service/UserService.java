package com.devtools.solution.service;

import com.devtools.solution.entity.User;
import com.devtools.solution.repo.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepoInterface userRepoInterface;

    public User getById(int id) {
        return userRepoInterface.findById(id).get();
    }

    public void save(User user) {
        userRepoInterface.save(user);
    }

    public void deleteById(int id) {
        userRepoInterface.deleteById(id);
    }
}
