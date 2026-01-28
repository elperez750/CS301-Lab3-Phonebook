package com.example.phonebook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PhoneBookGUI extends Application {

    //private PhoneBookManager manager = new PhoneBookManager();

    // Text field for name
    private TextField nameField = new TextField();

    // Text field for phone
    private TextField phoneField = new TextField();

    //Text field for address
    private TextArea addressArea = new TextArea();

    // This will include all people in the phonebook
    private ListView<Person> contactList = new ListView<>();




    @Override
    public void start(Stage stage) {
        // Input fields
        nameField.setPromptText("Name");
        phoneField.setPromptText("Phone");
        addressArea.setPromptText("Address");
        addressArea.setPrefRowCount(2);


        // Buttons
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addContact());

        Button delBtn = new Button("Delete");
        delBtn.setOnAction(e -> deleteContact());

        Button saveBtn = new Button("Save");
        //saveBtn.setOnAction(e -> manager.saveToFile());


        // Layout
        VBox inputs = new VBox(5, nameField, phoneField, addressArea);
        HBox buttons = new HBox(10, addBtn, delBtn, saveBtn);
        VBox layout = new VBox(10, inputs, buttons, contactList);
        layout.setStyle("-fx-padding: 10;");

        refreshList();

        Scene scene = new Scene(layout, 400, 500);
        stage.setTitle("Phone Book");
        stage.setScene(scene);
        //stage.setOnCloseRequest(e -> manager.saveToFile());
        stage.show();
    }



    private void addContact() {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
           // Person p = new Person(name, addressArea.getText(), phoneField.getText());
            //manager.addContact(p);
            refreshList();
            nameField.clear();
            phoneField.clear();
            addressArea.clear();
        }
    }

    private void deleteContact() {
        Person selected = contactList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // manager.deleteContact(selected.getName());
            refreshList();
        }
    }

    private void refreshList() {
        //contactList.getItems().setAll(manager.getAllContacts());
    }

    public static void main(String[] args) {
        launch(args);
    }
}