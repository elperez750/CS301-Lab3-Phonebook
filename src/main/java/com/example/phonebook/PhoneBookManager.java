package com.example.phonebook;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.io.File;

public class PhoneBookManager {

    /*
    Structure will look like the following:


    Elliott: <Elliott, 1111 Merea Lane, 2065029287

    We will search with the nam
     */
    private TreeMap<String, Person> contacts;
    private static final String FILE_NAME = "phonebook.dat";



    public PhoneBookManager() {
        contacts = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        loadFromFile();
    }

    public boolean addContact(Person person) {

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
        if (!contacts.containsKey(oldPerson.getName())) {
            return false;
        }
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
            contacts = (TreeMap<String, Person>) in.readObject();
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





    /*
    // Writing
try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("data.dat"))) {
    out.writeObject(myTreeMap);  // Saves entire TreeMap
}

// Reading
try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("data.dat"))) {
    TreeMap<String, Person> loaded = (TreeMap<String, Person>) in.readObject();
    //                                ↑ Type cast needed
}
     */


}
