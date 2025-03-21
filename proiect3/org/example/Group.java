package org.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    protected List<Person> members;
    protected Professor guide;
    private Integer museumCode;
    private String timetable;

    public Group() {
        members = new ArrayList<>();
    }

    public Group(Integer museumCode, String timetable) {
        this();
        this.museumCode = museumCode;
        this.timetable = timetable;
    }

    public void addGuide(Professor guide){
        this.guide = guide;
        members.add(guide);
    }

    public void addMember(Person member){
        members.add(member);
    }

    public void removeMember(Person member){
        members.remove(member);
    }

    public void resetGuide() {
        if (guide != null) {
            members.remove(guide);
        }
        guide = null;
    }

    public void setMuseumCode(Integer museumCode) {
        this.museumCode = museumCode;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public Integer getMuseumCode() {
        return museumCode;
    }

    public String getTimetable() {
        return timetable;
    }

    public String toString() {
        return museumCode + " ## " + timetable + " ## ";
    }
}
