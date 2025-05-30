package com.example.managepasword;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public RadioButton ramStorage;
    public RadioButton fileStorage;
    public TextField filePathField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField passwordField;
    @FXML
    private TableView<PasswordEntry> passwordTable;
    @FXML
    private TableColumn<PasswordEntry, Integer> idColumn;
    @FXML
    private TableColumn<PasswordEntry, String> titleColumn;
    @FXML
    private TableColumn<PasswordEntry, String> passwordColumn;
    private PasswordDAO passwordDAO;
    private ObservableList<PasswordEntry> passwordEntries;

    public HelloController() {
        // Изначально используем память
        this.passwordDAO = new RAMPasswordDAO();
        this.passwordEntries = FXCollections.observableArrayList();
    }

    @FXML
    public void setStorageMethod() {
        if (ramStorage.isSelected()) {
            passwordDAO = new RAMPasswordDAO();
        } else if (fileStorage.isSelected()) {
            String filePath = filePathField.getText();
            if (!filePath.isEmpty()) {
                passwordDAO = new FilePasswordDAO(filePath);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid file path.");
                alert.showAndWait();
                return;
            }
        }
        loadPasswords(); // Загрузите пароли в текущий DAO
    }

    @FXML
    private void addPassword() {
        String login = titleField.getText();
        String password = passwordField.getText();

        if (!login.isEmpty() && !password.isEmpty()) {
            PasswordEntry entry = new PasswordEntry(passwordDAO.getAllPasswords().size() + 1, login, password);
            passwordDAO.addPassword(entry);
            passwordEntries.add(entry);
            titleField.clear();
            passwordField.clear();
        }
    }

    @FXML
    private void loadPasswords() {
        List<PasswordEntry> entries = passwordDAO.getAllPasswords();
        passwordEntries.setAll(entries);
        passwordTable.setItems(passwordEntries);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Инициализация таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadPasswords();
    }
}