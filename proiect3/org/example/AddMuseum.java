package org.example;

import java.io.IOException;
import java.io.PrintWriter;

public class AddMuseum extends Main {
    public void execute(String text, String[] args, PrintWriter pw) throws IOException {
        try {
            String[] parts = text.split("\\|");

            Location location = new Location.Builder(parts[3], Integer.parseInt(parts[16])).build();
            Museum museum = new Museum.Builder(parts[2], Long.parseLong(parts[1]), Long.parseLong(parts[14]), location).build();
            Database.Instance().addMuseum(museum);

            pw.println(museum.toString());
        } catch (Exception e) {
            pw.println("Exception: Data is broken. ## (" + text + ")");
        }
    }
}
