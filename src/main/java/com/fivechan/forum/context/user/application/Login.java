package com.fivechan.forum.context.user.application;

public class Login {
    // attributes :)
    private String username;
    private String password;

    // constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // methods
    public void execute() {}

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
}
