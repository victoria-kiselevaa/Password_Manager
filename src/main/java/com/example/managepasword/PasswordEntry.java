package com.example.managepasword;

public class PasswordEntry {
    private int id;
    private String login;  // логин
    private String password; // Пароль

    public PasswordEntry(int id, String title, String password) {
        this.id = id;
        this.login = title;
        this.password = password;
    }

    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
