package com.example.phonebook;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class PhoneBookGUI extends Application {
    private Label statusLabel = new Label();

    // Text fields for input
    private TextField nameField = new TextField();
    private TextField addressField = new TextField();
    private TextField phoneField = new TextField();

    // Display panel for contacts
    private TextArea displayPanel = new TextArea();


    PhoneBookManager manager = new PhoneBookManager();
    @Override
    public void start(Stage stage) {
        // Title
        Label titleLabel = new Label("PhoneBook");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleLabel.setTextFill(Color.RED);
        titleLabel.setPadding(new Insets(15, 0, 10, 0));

        // Input grid
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(15);
        inputGrid.setVgap(12);
        inputGrid.setPadding(new Insets(15, 20, 10, 20));

        Label nameLabel = new Label("Name");
        Label addressLabel = new Label("Address");
        Label phoneLabel = new Label("Phone Number");

        // Style labels
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        addressLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        phoneLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(addressLabel, 0, 1);
        inputGrid.add(addressField, 1, 1);
        inputGrid.add(phoneLabel, 0, 2);
        inputGrid.add(phoneField, 1, 2);




        // Configure text fields
        nameField.setPrefWidth(350);
        addressField.setPrefWidth(350);
        phoneField.setPrefWidth(350);

        nameField.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");
        addressField.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");
        phoneField.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");


        // Display panel
        displayPanel.setEditable(false);
        displayPanel.setPrefHeight(350);
        displayPanel.setPrefWidth(300);
        displayPanel.setWrapText(true);
        displayPanel.setStyle(
                "-fx-control-inner-background: #f5f5f5; " +
                        "-fx-border-color: #999999; " +
                        "-fx-border-width: 1; " +
                        "-fx-font-family: 'Courier New'; " +
                        "-fx-font-size: 12px;"
        );

        // Buttons with consistent sizing
        Button addContactBtn = createStyledButton("Add Contact");
        Button resetEntriesBtn = createStyledButton("Reset Entries");
        Button removeContactBtn = createStyledButton("Remove Contact");
        Button resetDisplayBtn = createStyledButton("Reset Display Panel");
        Button modifyContactBtn = createStyledButton("Modify Contact");
        Button contactInfoBtn = createStyledButton("Contact Information");
        Button printPhoneBookBtn = createStyledButton("Print Entire PhoneBook");
        Button saveToFileBtn = createStyledButton("Save PhoneBook To File");
        Button openOldPhoneBookBtn = createStyledButton("Open Old PhoneBook");

        // Set button widths to match layout
        addContactBtn.setPrefWidth(155);
        resetEntriesBtn.setPrefWidth(155);
        removeContactBtn.setPrefWidth(155);
        resetDisplayBtn.setPrefWidth(155);
        modifyContactBtn.setPrefWidth(320);
        contactInfoBtn.setPrefWidth(320);
        printPhoneBookBtn.setPrefWidth(320);
        saveToFileBtn.setPrefWidth(200);
        openOldPhoneBookBtn.setPrefWidth(200);

        // Set button actions (placeholder - you'll implement these)
        addContactBtn.setOnAction(e -> addContact());
        resetEntriesBtn.setOnAction(e -> resetEntries());
        removeContactBtn.setOnAction(e -> removeContact());
        resetDisplayBtn.setOnAction(e -> resetDisplayPanel());
        modifyContactBtn.setOnAction(e -> modifyContact());
        contactInfoBtn.setOnAction(e -> contactInformation());
        printPhoneBookBtn.setOnAction(e -> printEntirePhoneBook());
        saveToFileBtn.setOnAction(e -> savePhoneBookToFile());
        openOldPhoneBookBtn.setOnAction(e -> openOldPhoneBook());

        // Button layout
        HBox row1 = new HBox(10, addContactBtn, resetEntriesBtn);
        HBox row2 = new HBox(10, removeContactBtn, resetDisplayBtn);

        VBox buttonGroup = new VBox(10);
        buttonGroup.setPadding(new Insets(10, 20, 10, 20));
        buttonGroup.getChildren().addAll(
                row1,
                row2,
                modifyContactBtn,
                contactInfoBtn,
                printPhoneBookBtn
        );






        // Bottom buttons
        HBox bottomButtons = new HBox(20, saveToFileBtn, openOldPhoneBookBtn);
        bottomButtons.setPadding(new Insets(10, 20, 15, 20));
        bottomButtons.setAlignment(Pos.CENTER);




        // In your start() method, after creating inputGrid and before buttonGroup

// Style the status label
        statusLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setPadding(new Insets(5, 20, 5, 20));
        statusLabel.setVisible(false); // Hidden by default

    // Add it to your leftSide VBox
        VBox leftSide = new VBox(5);
        leftSide.setStyle("-fx-background-color: #e8e8e8;");
        leftSide.getChildren().addAll(inputGrid, statusLabel, buttonGroup, bottomButtons);



        // Right side with display panel
        VBox rightSide = new VBox(10);
        rightSide.setPadding(new Insets(15, 15, 15, 10));
        rightSide.setStyle("-fx-background-color: #e8e8e8;");
        rightSide.getChildren().add(displayPanel);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #e8e8e8;");

        VBox topSection = new VBox();
        topSection.setAlignment(Pos.CENTER);
        topSection.setStyle("-fx-background-color: #e8e8e8;");
        topSection.getChildren().add(titleLabel);

        mainLayout.setTop(topSection);
        mainLayout.setLeft(leftSide);
        mainLayout.setCenter(rightSide);

        Scene scene = new Scene(mainLayout, 960, 630);
        stage.setTitle("PhoneBook");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    private void showStatus(String message, boolean success) {
        statusLabel.setText(message);
        statusLabel.setTextFill(success ? Color.GREEN : Color.RED);
        statusLabel.setVisible(true);

        // Optional: Auto-hide after 3 seconds
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> statusLabel.setVisible(false));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #d3d3d3; " +
                        "-fx-border-color: #999999; " +
                        "-fx-border-width: 1; " +
                        "-fx-font-size: 11px; " +
                        "-fx-padding: 6 12 6 12;"
        );

        // Hover effect
        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #c0c0c0; " +
                                "-fx-border-color: #999999; " +
                                "-fx-border-width: 1; " +
                                "-fx-font-size: 11px; " +
                                "-fx-padding: 6 12 6 12;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: #d3d3d3; " +
                                "-fx-border-color: #999999; " +
                                "-fx-border-width: 1; " +
                                "-fx-font-size: 11px; " +
                                "-fx-padding: 6 12 6 12;"
                )
        );

        return button;
    }

    // Placeholder methods - you'll implement these
    private void addContact() {
        // Your implementation here
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();


        if (name.isEmpty()) {
            showStatus("Please enter a name to add contact", false);
            return;
        }

        if (address.isEmpty()) {
            showStatus("Please enter a address to add contact", false);
            return;
        }

        if (phone.isEmpty()) {
            showStatus("Please enter a phone number to add contact", false);
            return;
        }


        Person person = new Person(name, address, phone);
        boolean isAdded = manager.addContact(person);


        if (isAdded) {
            showStatus(name + " has been added successfully", true);

        }
        else {
            showStatus(name + " was not added successfully", false);
        }

        resetEntries();
    }

    private void resetEntries() {
        nameField.clear();
        addressField.clear();
        phoneField.clear();
    }

    private void removeContact() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            showStatus("Please enter a name to delete", false);
            return;
        }

        boolean isDeleted = manager.deleteContact(name);

        if (isDeleted) {
            showStatus(name + " has been deleted successfully", true);
        } else {
            showStatus("Contact not found: " + name, false);
        }

        resetEntries();
    }

    private void resetDisplayPanel() {
        displayPanel.clear();
    }

    private void modifyContact() {
        // Your implementation here

        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            showStatus("Please enter a name to edit", false);
            return;
        }


        Person oldContact = manager.getContact(name);

        String address = addressField.getText().trim();

        if (address.isEmpty()) {
            showStatus("Please enter a address to edit", false);
            return;
        }


        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            showStatus("Please enter a phone number to edit", false);
            return;
        }

        if (oldContact != null) {

            Person newContact = new Person(name, address, phone);
            boolean hasBeenModified = manager.modifyContact(oldContact, newContact);

            if (hasBeenModified) {
                showStatus(name + " has been modified successfully", true);
                resetEntries();

            }
            else {
                showStatus(name + " was not modified successfully", false);
            }


        }



    }

    private void contactInformation() {
        // Your implementation here
        String name = nameField.getText().trim();
        Person person = manager.getContact(name);

        if (person != null) {
            resetEntries();
            resetDisplayPanel();
            displayPanel.appendText(person.getName() + "\n");
            displayPanel.appendText(person.getAddress() + "\n");
            displayPanel.appendText(person.getPhone() + "\n");
            showStatus(name + " was found succesfully", true);

        }

        else {
            showStatus(name + " was not found succesfully", false);
        }




    }

    private void printEntirePhoneBook() {
        // Your implementation here
        List<Person> allContacts = manager.getAllContacts();

        displayPanel.clear(); // Clear existing content

        for (Person person : allContacts) {
            displayPanel.appendText(person.getName() + "\n");
            displayPanel.appendText(person.getAddress() + "\n");
            displayPanel.appendText(person.getPhone() + "\n");
            displayPanel.appendText("\n"); // Blank line between contacts
        }

    }

    private void savePhoneBookToFile() {
        manager.saveToFile();
    }

    private void openOldPhoneBook() {
        manager.loadFromFile();
        printEntirePhoneBook();

    }

    public static void main(String[] args) {
        launch(args);
    }
}