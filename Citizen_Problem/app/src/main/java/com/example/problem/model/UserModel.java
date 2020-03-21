package com.example.problem.model;

public class UserModel {
    String name;
    String surname;
    String password;
    String img;

    public UserModel(String name, String surname, String password, String img) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getImg() {
        return img;
    }
}
