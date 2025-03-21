package org.example;

import java.io.PrintWriter;

public class RemoveGuide extends Main {
    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");

        for (Group group : Database.groups) {
            if (group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10])) {
                if (group.guide != null) {
                    pw.println(group + "removed guide: " + group.guide);
                    Database.groups.remove(group);
                    group.resetGuide();
                } else {
                    pw.println("No guide assigned to group: " + group);
                }
                break;
            }
        }
    }
}
