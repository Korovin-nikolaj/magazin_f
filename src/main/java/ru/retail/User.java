package ru.retail;

public class User {

    private final String name;
    private final int id;
    private final String phone;
    private final int hashPassword;

    public User(String name, int id, String phone, int hashPassword) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.hashPassword = hashPassword;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public int getHashPassword() {
        return hashPassword;
    }
}