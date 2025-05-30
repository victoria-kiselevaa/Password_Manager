package com.example.managepasword;

public class PasswordFactory {
    public static final String FILE = "file";
    public static final String RAM = "ram";

    public static PasswordDAO createPasswordDAO(String type, String filePath) {
        if (type.equalsIgnoreCase(FILE)) {
            return new FilePasswordDAO(filePath);
        } else if (type.equalsIgnoreCase(RAM)) {
            return new RAMPasswordDAO();
        } else {
            throw new IllegalArgumentException("Invalid datasource type!");
        }
    }
}
