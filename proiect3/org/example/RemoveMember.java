package org.example;

import java.io.PrintWriter;

public class RemoveMember extends Main {
    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");
        String surname = parts[1];
        String name = parts[2];
        Integer museumCode = Integer.parseInt(parts[9]);
        String timetable = parts[10];

        try {
            boolean groupFound = false;
            Person removePerson = null;
            Group targetGroup = null;

            for (Group group : Database.groups) {
                if (group.getMuseumCode().equals(museumCode) && group.getTimetable().equals(timetable)) {
                    groupFound = true;
                    targetGroup = group;

                    for (Person member : group.members) {
                        if (member.surname.equals(surname) && member.name.equals(name)) {
                            removePerson = member;
                            break;
                        }
                    }
                    break;
                }
            }

            if (!groupFound) {
                throw new GroupNotFoundException(formatGroupNotFoundMessage(parts));
            }

            if (removePerson == null) {
                throw new PersonNotExistsException(formatPersonNotExistsMessage(parts, targetGroup));
            }

            targetGroup.removeMember(removePerson);
            pw.println(targetGroup + "removed member: " + removePerson);

        } catch (NumberFormatException e) {
            pw.println("Error: Museum code must be a number.");
        } catch (PersonNotExistsException | GroupNotFoundException e) {
            pw.println(e.getMessage());
        } catch (Exception e) {
            pw.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private String formatGroupNotFoundMessage(String[] parts) {
        String email = parts[5].isEmpty() ? "null" : parts[5];
        String details = "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator, age=" + parts[4] + ", email=" + email +
                ", school=" + parts[6] + (parts[3].equals("profesor") ? ", experience=" + parts[7] : ", studyYear=" + parts[7]);

        return parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: Group does not exist. ## (removed member: " + details + ")";
    }

    private String formatPersonNotExistsMessage(String[] parts, Group group) {
        String email = parts[5].isEmpty() ? "null" : parts[5];
        String details = "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator, age=" + parts[4] + ", email=" + email +
                ", school=" + parts[6] + (parts[3].equals("profesor") ? ", experience=" + parts[7] : ", studyYear=" + parts[7]);

        return group + "PersonNotExistsException: Person was not found in the group. ## (" + details + ")";
    }
}

class PersonNotExistsException extends Exception {
    public PersonNotExistsException(String message) {
        super(message);
    }
}

