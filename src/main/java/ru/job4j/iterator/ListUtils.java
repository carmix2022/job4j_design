package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        if (index == list.size() - 1) {
            list.add(value);
            return;
        }
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        if (list != null) {
            ListIterator<T> iterator = list.listIterator();
            while (iterator.hasNext()) {
                if (filter.test(iterator.next())) {
                    iterator.remove();
                }
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        if (list != null) {
            ListIterator<T> iterator = list.listIterator();
            while (iterator.hasNext()) {
                if (filter.test(iterator.next())) {
                    iterator.set(value);
                }
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        if (list != null && elements != null) {
            for (T check : elements) {
                ListIterator<T> itList = list.listIterator();
                while (itList.hasNext()) {
                    if (Objects.equals(check, itList.next())) {
                        itList.remove();
                    }
                }
            }
        }
    }
}