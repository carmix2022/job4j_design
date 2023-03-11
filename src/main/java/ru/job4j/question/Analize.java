package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed;
        int deleted;
        Set<User> temp = new HashSet<>(current);
        temp.removeAll(previous);
        Set<Integer> prevKey = previous.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        Set<Integer> tempKey = temp.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        tempKey.retainAll(prevKey);
        changed = tempKey.size();
        added = temp.size() - changed;
        deleted = previous.size() - (current.size() - added);
        return new Info(added, changed, deleted);
    }
}