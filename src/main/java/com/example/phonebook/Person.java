// Person.java - NO SETTERS NEEDED
package com.example.phonebook;
import java.io.Serializable;



public class Person implements Serializable {
    private String name;
    private String phone;
    private String address;

    public Person(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return name + " | " + phone;
    }
}