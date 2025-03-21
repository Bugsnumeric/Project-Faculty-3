package org.example;

import java.io.PrintWriter;

public class AddMember extends Main {
    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");
        try {
            boolean groupFound = false;

            for (Group group : Database.groups) {
                if (group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10])) {
                    groupFound = true;

                    if (group.members.size() > 10) {
                        String email = parts[5];
                        if (parts[5].isEmpty()) {
                            email = "null";
                        }

                        if (parts[3].equals("profesor")) {
                        throw new GroupThresholdException(group + "GroupThresholdException: Group cannot have more than 10 members. ## (new member: " +
                                "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator" + ", age=" + parts[4] + ", email="
                                + email + ", school=" + parts[6] + ", experience=" + parts[7] + ")");
                        } else if (parts[3].equals("student")) {
                            throw new GroupThresholdException(group + "GroupThresholdException: Group cannot have more than 10 members. ## (new member: " +
                                    "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator" + ", age=" + parts[4] + ", email="
                                    + email + ", school=" + parts[6] + ", studyYear=" + parts[7] + ")");
                        }
                    }

                    Person person = Person.createPerson(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]));
                    assert person != null;
                    if (person.getEmail().isEmpty()) {
                        person.setEmail("null");
                    }
                    person.setRole("vizitator");
                    group.addMember(person);
                    pw.println(group + "new member: " + person);
                }
            }

            if (!groupFound) {
                String email = parts[5];
                if (parts[5].isEmpty()) {
                    email = "null";
                }

                if (parts[3].equals("profesor")) {
                throw new GroupNotFoundException(parts[9] + " ## " + parts[10] + " ## " + "GroupNotExistsException: Group does not exist. ## (new member: " +
                        "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator" + ", age=" + parts[4] + ", email="
                        + email + ", school=" + parts[6] + ", experience=" + parts[7] + ")");
                } else if (parts[3].equals("student")) {
                    throw new GroupNotFoundException(parts[9] + " ## " + parts[10] + " ## " + "GroupNotExistsException: Group does not exist. ## (new member: " +
                            "surname=" + parts[1] + ", name=" + parts[2] + ", role=vizitator" + ", age=" + parts[4] + ", email="
                            + email + ", school=" + parts[6] + ", studyYear=" + parts[7] + ")");
                }
            }
        } catch (GroupThresholdException | GroupNotFoundException e) {
            pw.println(e.getMessage());
        } catch (Exception e) {
            pw.println("General error: " + e.getMessage());
        }
    }
}

class GroupThresholdException extends Exception {
    public GroupThresholdException(String message) {
        super(message);
    }
}
