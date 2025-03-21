package org.example;

public class Student extends Person {
    private String school;
    private int studyYear;

    protected Student(String surname, String name, String role, int age, String email, String school, int studyYear) {
        super(surname, name, role, age, email);
        this.school = school;
        this.studyYear = studyYear;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public String toString() {
        return super.toString() + ", school=" + school + ", studyYear=" + studyYear;
    }
}
