package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private int salary;
    private String emale;
    private String placeOfWork;

    public User(){}

    public User(String name, String surname, String patronymic, int age, int salary, String emale, String placeOfWork) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.salary = salary;
        this.emale = emale;
        this.placeOfWork = placeOfWork;
    }
}
