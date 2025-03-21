package org.example;

import java.io.PrintWriter;

public class AddEvent extends Main {
    public void execute(String text, String[] args, PrintWriter pw) {
        String[] parts = text.split("\\|");
        int museumCode = Integer.parseInt(parts[1]);
        String message = parts[2];
        try {
            String name_museum = null;
            for (Museum museum: Database.museums) {
                if (museum.getCode() == museumCode) {
                    name_museum = museum.getName();
                }
            }
            boolean museumFound = false;
            for (Group group : Database.groups) {
                if (group.getMuseumCode().equals(museumCode)) {
                    museumFound = true;

                    if (group.guide != null && name_museum != null) {
                        pw.println("To: " + group.guide.getEmail() + " ## Message: " + name_museum + " ("
                                + group.getMuseumCode() + ") " + message);
                    }
                }
            }

            if (!museumFound) {
                throw new MuseumNotFoundException("Museum not found: " + museumCode);
            }
        } catch (NumberFormatException e) {
            pw.println("Error, code must be a number");
        } catch (MuseumNotFoundException e) {
            pw.println(e.getMessage());
        }
    }
}

class MuseumNotFoundException extends Exception {
    public MuseumNotFoundException(String message) {
        super(message);
    }
}
