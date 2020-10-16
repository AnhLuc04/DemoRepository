package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Customer implements Cloneable {

    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Cú pháp sai")
    @Size(min = 2, max = 30,message = "Cú pháp sai")
    private String firstName;
//    @NotEmpty(message = "Cú pháp sai")
//    @Size(min = 2, max = 30,message = "Cú pháp sai")
    @Column(unique = true)
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(Long id, @NotEmpty(message = "Cú pháp sai") @Size(min = 2, max = 30, message = "Cú pháp sai") String firstName, @NotEmpty(message = "Cú pháp sai") @Size(min = 2, max = 30, message = "Cú pháp sai") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}