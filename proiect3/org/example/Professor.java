package org.example;

public class Professor extends Person {
    private int experience;
    private String school;

    protected Professor(String surname, String name, String role, int age, String email, int experience, String school) {
        super(surname, name, role, age, email);
        this.experience = experience;
        this.school = school;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return super.getRole();
    }

    public String toString() {
        return super.toString() + ", school=" + school + ", experience=" + experience;
    }
}
