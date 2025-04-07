package com.sabbir.service;

import com.sabbir.model.User;
import com.sabbir.repository.UserRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Cacheable(value = "users", key = "#email", unless = "#result == null")
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Cacheable(value = "users", key = "#Id", unless = "#result == null")
    public User getUserById(Long Id) {
        System.out.println("hi");
        return userRepo.findById(Id).orElse(null);
    }

    @CachePut(value = "users", key="#user.id")
    public User save(User user) {
        return userRepo.save(user);
    }

    @CacheEvict(value = "users", key = "#user.id")
    public void delete(User user) {
        userRepo.delete(user);
    }
}
