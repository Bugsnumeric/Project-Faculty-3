package org.example;

import java.util.LinkedHashSet;
import java.util.Set;

//Design pattern SINGLETON
public class Database {
    private static Database database;
    protected static Set<Museum> museums;
    protected static Set<Group> groups;

    private Database() {}
    public static Database Instance() {
        if (database == null) {
            database = new Database();
            museums = new LinkedHashSet<>();
            groups = new LinkedHashSet<>();
        }
        return database;
    }

    protected void addMuseum(Museum museum) {
        museums.add(museum);
    }

    protected void addMuseums(Set<Museum> museums) {
        Database.museums.addAll(museums);
    }

    protected void addGroup(Group group) {
        groups.add(group);
    }

    protected void addGroups(Set<Group> groups) {
        Database.groups.addAll(groups);
    }
}
