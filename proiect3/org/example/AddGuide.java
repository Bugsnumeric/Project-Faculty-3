package org.example;

import java.io.PrintWriter;
import java.util.Set;

public class AddGuide extends Main{
    public void execute(String text, String[] args, PrintWriter pw) {
        try {
            GuideTypeException.verifyGuideType(text);

            String[] parts = text.split("\\|");
            Group group = new Group(Integer.parseInt(parts[9]), parts[10]);
            Professor professor = new Professor(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[7]), parts[6]);
            if (Database.groups != null) {
                GuideExistsException.verifyGuideExists(Database.groups, group);
            }
            Database.Instance().addGroup(group);
            group.addGuide(professor);
            professor.setRole("ghid");
            pw.println(group + "new guide: " + professor);
        } catch (GuideTypeException | GuideExistsException e) {
            pw.println(e.getMessage());
        }
    }
}

class GuideTypeException extends Exception {
    public GuideTypeException(String message) {
        super(message);
    }

    public static void verifyGuideType(String text) throws GuideTypeException {
        String[] parts = text.split("\\|");
        Student student = new Student(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]));
        student.setRole("vizitator");
        if (parts[3].equals("student")) {
            throw new GuideTypeException(parts[9] + " ## " + parts[10] + " ## GuideTypeException: Guide must be a professor. ## (new guide: " + student + ")");
        }
    }
}

class GuideExistsException extends Exception {
    public GuideExistsException(String message) {
        super(message);
    }

    public static void verifyGuideExists(Set<Group> groups, Group group) throws GuideExistsException {
        for (Group g : groups) {
            if (g.getTimetable().equals(group.getTimetable()) && g.getMuseumCode().equals(group.getMuseumCode())) {
                throw new GuideExistsException(g.getMuseumCode() + " ## " + g.getTimetable() + " ## GuideExistsException: Guide already exists. ## (new guide: " + g.guide + ")");
            }
        }
    }
}