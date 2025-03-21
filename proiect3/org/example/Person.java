package org.example;
//Design pattern Factory Method
public class Person {
    protected String surname;
    protected String name;
    protected String role;
    protected int age;
    protected String email;

    protected int experience;
    protected String school;
    protected int studyYear;

    public Person() {}

    public Person(String surname, String name, String role, int age, String email) {
        this.surname = surname;
        this.name = name;
        this.role = role;
        this.age = age;

        if (email != null) {
            this.email = email;
        } else {
            this.email = "";
        }
    }

    public static Person createPerson(String surname, String name, String role, int age, String email, String school, int year) {
        switch (role) {
            case "profesor":
                return new Professor(surname, name, role, age, email, year, school);
            case "student":
                return new Student(surname, name, role, age, email, school, year);
            default:
                return null;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "surname=" + surname + ", name=" + name + ", role=" + role + ", age=" + age + ", email=" + email;
    }
}
