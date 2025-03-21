package org.example;

import java.io.*;

//Design pattern Command
public abstract class Main {
    public abstract void execute(String command, String[] args, PrintWriter pw) throws IOException;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            Commander commander = new Commander();
            FileWriter fw = new FileWriter(args[1] + ".out");
            PrintWriter pw = new PrintWriter(fw);
            FileReader fr = new FileReader(args[1] + ".in");

            try (BufferedReader bufferedReader = new BufferedReader(fr)) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    commander.addCommand(parts[0]);

                    switch (parts[0]) {
                        case "ADD MUSEUM":
                            AddMuseum addMuseum = new AddMuseum();
                            addMuseum.execute(line, args, pw);
                            break;
                        case "ADD MEMBER":
                            AddMember addMemeber = new AddMember();
                            addMemeber.execute(line, args, pw);
                            break;
                        case "ADD GUIDE":
                            AddGuide addGuide = new AddGuide();
                            addGuide.execute(line, args, pw);
                            break;
                        case "REMOVE MEMBER":
                            RemoveMember removeMember = new RemoveMember();
                            removeMember.execute(line, args, pw);
                            break;
                        case "REMOVE GUIDE":
                            RemoveGuide removeGuide = new RemoveGuide();
                            removeGuide.execute(line, args, pw);
                            break;
                        case "FIND MEMBER":
                            FindMember findMember = new FindMember();
                            findMember.execute(line, args, pw);
                            break;
                        case "FIND GUIDE":
                            FindGuide findGuide = new FindGuide();
                            findGuide.execute(line, args, pw);
                            break;
                    }
                }
                commander.showCommands(Commander.commands);
                commander.clearCommands();
                Database.groups.clear();
                Database.museums.clear();
            } catch (IOException e) {
                System.out.println("Error: " + args[1]);
            }
            pw.close();
        } else {
            FileWriter write_to_museum = new FileWriter(args[1] + ".out");
            PrintWriter print_to_museum = new PrintWriter(write_to_museum);

            FileWriter write_to_group = new FileWriter(args[2] + ".out");
            PrintWriter print_to_group = new PrintWriter(write_to_group);

            FileWriter write_to_event = new FileWriter(args[3] + ".out");
            PrintWriter print_to_event = new PrintWriter(write_to_event);

            for (int i = 1; i < 4; i++) {
                FileReader fileReader = new FileReader(args[i] + ".in");

                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split("\\|");

                        switch (parts[0]) {
                            case "ADD MUSEUM":
                                AddMuseum addMuseum = new AddMuseum();
                                addMuseum.execute(line, args, print_to_museum);
                                break;
                            case "ADD MEMBER":
                                AddMember addMemeber = new AddMember();
                                addMemeber.execute(line, args, print_to_group);
                                break;
                            case "ADD GUIDE":
                                AddGuide addGuide = new AddGuide();
                                addGuide.execute(line, args, print_to_group);
                                break;
                            case "REMOVE MEMBER":
                                RemoveMember removeMember = new RemoveMember();
                                removeMember.execute(line, args, print_to_group);
                                break;
                            case "REMOVE GUIDE":
                                RemoveGuide removeGuide = new RemoveGuide();
                                removeGuide.execute(line, args, print_to_group);
                                break;
                            case "FIND MEMBER":
                                FindMember findMember = new FindMember();
                                findMember.execute(line, args, print_to_group);
                                break;
                            case "FIND GUIDE":
                                FindGuide findGuide = new FindGuide();
                                findGuide.execute(line, args, print_to_group);
                                break;
                            case "ADD EVENT":
                                AddEvent addEvent = new AddEvent();
                                addEvent.execute(line, args, print_to_event);
                                break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + args[1]);
                }
            }
            Database.groups.clear();
            Database.museums.clear();
            print_to_museum.close();
            print_to_group.close();
            print_to_event.close();
        }
    }
}
