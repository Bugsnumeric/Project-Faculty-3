package org.example;

import java.util.LinkedList;
import java.util.List;

public class Commander {
    public static List<String> commands = new LinkedList<>();

    Commander() {}

    public void addCommand(String command) {
        commands.add(command);
    }

    public void removeCommand(String command) {
        commands.remove(command);
    }

    public void showCommands(List<String> commands) {
        if (commands.isEmpty()) {
            return;
        }
        int i = 0;
        System.out.println("Showing commands:");
        for (String command : commands) {
            System.out.println(i + ": " + command);
            i++;
        }
    }

    public void clearCommands() {
        commands.clear();
    }

    public void undoCommand() {}

    public void redoCommand() {}
}
