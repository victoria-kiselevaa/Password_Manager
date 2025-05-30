package com.example.managepasword;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePasswordDAO implements PasswordDAO {
    private String filePath;
    private List<PasswordEntry> passwordEntries;

    public FilePasswordDAO(String filePath) {
        this.filePath = filePath;
        this.passwordEntries = new ArrayList<>();
        loadPasswordsFromFile();
    }

    private void loadPasswordsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    PasswordEntry entry = new PasswordEntry(Integer.parseInt(parts[0]), parts[1], parts[2]);
                    passwordEntries.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePasswordsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (PasswordEntry entry : passwordEntries) {
                writer.write(entry.getId() + "," + entry.getLogin() + "," + entry.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PasswordEntry> getAllPasswords() {
        return passwordEntries;
    }

    @Override
    public PasswordEntry getPasswordById(int id) {
        return passwordEntries.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPassword(PasswordEntry entry) {
        passwordEntries.add(entry);
        savePasswordsToFile();
    }

    @Override
    public void updatePassword(PasswordEntry entry) {
        for (int i = 0; i < passwordEntries.size(); i++) {
            if (passwordEntries.get(i).getId() == entry.getId()) {
                passwordEntries.set(i, entry);
                savePasswordsToFile();
                return;
            }
        }
    }

    @Override
    public void deletePassword(int id) {
        passwordEntries.removeIf(entry -> entry.getId() == id);
        savePasswordsToFile();
    }
}
