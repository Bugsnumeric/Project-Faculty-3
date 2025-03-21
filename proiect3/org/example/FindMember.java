package org.example;

import java.io.PrintWriter;

public class FindMember extends Main {

    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");

        try {
            String museumCode = parts[9];
            String timetable = parts[10];
            String surname = parts[1];
            String name = parts[2];

            Group group = findGroup(museumCode, timetable);

            if (group == null) {
                throw new MemberNotFoundException(formatMemberNotFoundException(parts, "vizitator"));
            }

            Person member = findMemberInGroup(group, surname, name);

            if (member == null || member.role.equals("ghid")) {
                throw new MemberNotFoundException(formatMemberNotFoundException(parts, member != null ? member.role : "vizitator"));
            }

            pw.println(group + "member found: " + member);

        } catch (MemberNotFoundException e) {
            pw.println(e.getMessage());
        } catch (Exception e) {
            pw.println("General error: " + e.getMessage());
        }
    }

    private Group findGroup(String museumCode, String timetable) {
        for (Group group : Database.groups) {
            if (group.getMuseumCode().equals(Integer.parseInt(museumCode)) && group.getTimetable().equals(timetable)) {
                return group;
            }
        }
        return null;
    }

    private Person findMemberInGroup(Group group, String surname, String name) {
        for (Person member : group.members) {
            if (member.surname.equals(surname) && member.name.equals(name)) {
                return member;
            }
        }
        return null;
    }

    private String formatMemberNotFoundException(String[] parts, String role) {
        String email = parts[5].isEmpty() ? "null" : parts[5];
        return parts[9] + " ## " + parts[10] + " ## member not exists: " +
                "surname=" + parts[1] + ", name=" + parts[2] + ", role=" + role + ", age=" + parts[4] +
                ", email=" + email + ", school=" + parts[6] + ", " +
                (parts[3].equals("profesor") ? "experience=" + parts[7] : "studyYear=" + parts[7]);
    }
}

class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
