package com.example.phonebook;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.io.File;

public class PhoneBookManager {

    /*
    Structure will look like the following:


    Gerald: <Gerald, 39820 Rodger Lane, 5098938291>

    We will search with the name
     */


    private TreeMap<String, Person> contacts;

    // Where we will save the phonebook
    private static final String FILE_NAME = "phonebook.dat";



    public PhoneBookManager() {

        // Case-insensitive which is useful for sorting and deleting later on!
        contacts = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        // Load save phonebook if we have one
        loadFromFile();
    }


    public Person getContact(String name) {

        if (contacts.containsKey(name)) {
            return contacts.get(name);
        }

        return null;
    }


    public boolean addContact(Person person) {

        // Preventing duplicates
        if (contacts.containsKey(person.getName())) {
            return false;
        }


        contacts.put(person.getName(), person);
        return true;

    }



    public boolean deleteContact(String name) {
        return contacts.remove(name) != null;
    }


    public boolean modifyContact(Person oldPerson, Person newPerson) {
        // Easier to remove Person object with old info and insert Person object with new info.
        contacts.remove(oldPerson.getName());
        contacts.put(newPerson.getName(), newPerson);
        return true;
    }


    @SuppressWarnings("unchecked")
    public boolean loadFromFile() {
        File file = new File("phonebook.dat");
        if (!file.exists()) {
            return false;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("phonebook.dat"))) {
            contacts = (TreeMap<String, Person>)in.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }

    }

    public boolean saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("phonebook.dat"))) {

            out.writeObject(contacts);
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    public List<Person> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }




}
