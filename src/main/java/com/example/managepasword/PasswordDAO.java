package com.example.managepasword;

import java.util.List;

public interface PasswordDAO {
    List<PasswordEntry> getAllPasswords();
    PasswordEntry getPasswordById(int id);
    void addPassword(PasswordEntry entry);
    void updatePassword(PasswordEntry entry);
    void deletePassword(int id);
}
