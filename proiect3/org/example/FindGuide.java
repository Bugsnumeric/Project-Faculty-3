package org.example;

import java.io.PrintWriter;

public class FindGuide extends Main {
    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");
        String guideSurname = parts[1];
        String guideName = parts[2];
        Integer museumCode = Integer.parseInt(parts[9]);
        String timetable = parts[10];

        try {
            boolean groupFound = false;
            boolean guideFound = false;

            for (Group group : Database.groups) {
                if (group.getMuseumCode().equals(museumCode) && group.getTimetable().equals(timetable)) {
                    groupFound = true;
                    if (group.guide != null && group.guide.surname.equals(guideSurname) && group.guide.name.equals(guideName)) {
                        pw.println(group + "guide found: " + group.guide);
                        guideFound = true;
                        break;
                    }
                }
            }

            if (!groupFound) {
                handleGroupNotFound(parts);
            }

            if (!guideFound) {
                handleGuideNotFound(parts, guideSurname, guideName);
            }

        } catch (NumberFormatException e) {
            pw.println("Error, museum code must be a number.");
        } catch (GuideNotFoundException | GroupNotFoundException e) {
            pw.println(e.getMessage());
        }
    }

    private void handleGroupNotFound(String[] parts) throws GroupNotFoundException {
        String email = parts[5].isEmpty() ? "null" : parts[5];
        String role = parts[3];

        throw new GroupNotFoundException(String.format("%s ## %s ## GroupNotExistsException: Group does not exist. ## (removed member: surname=%s, name=%s, role=vizitator, age=%s, email=%s, school=%s, %s)",
                parts[9], parts[10], parts[1], parts[2], parts[4], email, parts[6], role.equals("profesor") ? "experience=" + parts[7] : "studyYear=" + parts[7]));
    }

    private void handleGuideNotFound(String[] parts, String guideSurname, String guideName) throws GuideNotFoundException, GroupNotFoundException {
        for (Group group : Database.groups) {
            if (group.guide != null && group.guide.surname.equals(guideSurname) && group.guide.name.equals(guideName)) {
                throw new GuideNotFoundException(String.format("%s ## %s ## guide not exists: %s", parts[9], parts[10], group.guide));
            }
        }

        String email = parts[5].isEmpty() ? "null" : parts[5];
        String role = parts[3];

        throw new GroupNotFoundException(String.format("%s ## %s ## guide not exists: surname=%s, name=%s, role=vizitator, age=%s, email=%s, school=%s, %s",
                parts[9], parts[10], parts[1], parts[2], parts[4], email, parts[6], role.equals("profesor") ? "experience=" + parts[7] : "studyYear=" + parts[7]));
    }
}

class GuideNotFoundException extends Exception {
    public GuideNotFoundException(String message) {
        super(message);
    }
}

class GroupNotFoundException extends Exception {
    public GroupNotFoundException(String message) {
        super(message);
    }
}
