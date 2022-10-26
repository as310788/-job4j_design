package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        Map<Integer, String> previousMap = new HashMap<>();
        for (User userMap : previous) {
            previousMap.put(userMap.getId(), userMap.getName());
        }
        for (User user : current) {
            int userID = user.getId();
            if (!previousMap.containsKey(userID)) {
                added++;
            } else {
                if (!user.getName().equals(previousMap.remove(userID))) {
                    changed++;
                }
            }
        }
        deleted = previousMap.size();
        return new Info(added, changed, deleted);
    }

    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, u3);
        System.out.println(Analize.diff(previous, current));
    }
}
