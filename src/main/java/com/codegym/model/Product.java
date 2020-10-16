package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;


@Entity

public class Product implements Validator {

    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String lastName;

    public Product() {
    }

    public Product(String firstName, String lastName) {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        String firstName = product.getFirstName();
        String lastName = product.getLastName();
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
        if (firstName.length() > 1 || firstName.length() < 30) {
            errors.rejectValue("firstName", "firstName.length");
        }
//        if (!firstName.startsWith("0")) {
//            errors.rejectValue("firstName", "firstName.starts With");
//        }
//        if (!firstName.matches(("(^$|[0-9]*$)"))) {
//            errors.rejectValue("firstName", "firstName.matches");
//        }
        if (lastName.length() > 1 || lastName.length() < 30) {
            errors.rejectValue("lastName", "lastName.length");
        }
//        if (!lastName.startsWith("0")) {
//            errors.rejectValue("lastName", "lastName.starts With");
//        }
//        if (!lastName.matches(("(^$|[0-9]*$)"))) {
//            errors.rejectValue("firstName", "flastName.matches");
//        }
    }
}
