package com.fivechan.forum.context.user.application;

import com.fivechan.forum.context.user.domain.User;
import com.fivechan.forum.context.user.domain.UserRepository;

import java.util.UUID;

public class CreateUser {
    UserRepository userRepository;
    private String username;
    private String password;
    private String email;

    // constructor
    public CreateUser(UserRepository userRepository, String username, String password, String email) {
        this.userRepository = userRepository;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // methods
    public void run(UUID id, String name, String description, String avatar) {
        User user = new User(id, name, description, avatar);
        this.userRepository.save(user);
    }

    // method for the creation of user
    public void execute() {
        // generate new UUID
        UUID id = UUID.randomUUID();
        // new description for the user :)
        String description = "Default description";
        String avatar = "Default avatar";
        // create and save user
        this.run(id, this.username, description, avatar);
    }

    // getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
