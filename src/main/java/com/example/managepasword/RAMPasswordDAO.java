package com.example.managepasword;

import java.util.ArrayList;
import java.util.List;

public class RAMPasswordDAO implements PasswordDAO {
    private List<PasswordEntry> passwordEntries;

    public RAMPasswordDAO() {
        passwordEntries = new ArrayList<>();
    }

    public List<PasswordEntry> getAllPasswords() {
        return passwordEntries;
    }

    public PasswordEntry getPasswordById(int id) {
        return passwordEntries.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addPassword(PasswordEntry entry) {
        passwordEntries.add(entry);
    }

    public void updatePassword(PasswordEntry entry) {
        for (int i = 0; i < passwordEntries.size(); i++) {
            if (passwordEntries.get(i).getId() == entry.getId()) {
                passwordEntries.set(i, entry);
                return;
            }
        }
    }

    public void deletePassword(int id) {
        passwordEntries.removeIf(entry -> entry.getId() == id);
    }
}
