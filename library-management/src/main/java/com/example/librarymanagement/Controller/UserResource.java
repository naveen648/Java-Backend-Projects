package com.example.librarymanagement.Controller;


import com.example.librarymanagement.DataAcessLayer.User;
import com.example.librarymanagement.DataAcessLayer.UserRepository;
import com.example.librarymanagement.Exception.UserNotFoundException;
import com.example.librarymanagement.Util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@RestController
public class UserResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User newuser) throws Exception {
        if(UserValidator.isValid(newuser))
            return repository.save(newuser);
        else throw new Exception();
    }

    @GetMapping("/users/{id}")
    User findUser(@PathVariable int id){
        LOGGER.info("/users/{id} called with id "+ id);
        return repository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

}
