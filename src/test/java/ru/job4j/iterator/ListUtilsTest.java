package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeZeroIndex() {
        ListUtils.addBefore(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(2, 1, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfter2() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenAddAfter3() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf1() {
        ListUtils.removeIf(input, s -> s > 1);
        assertThat(input).hasSize(1).containsExactly(1);
    }

    @Test
    void whenRemoveIf2() {
        ListUtils.removeIf(input, s -> s < 4);
        assertThat(input).hasSize(0).isEmpty();
    }

    @Test
    void whenRemoveIf3() {
        assertThatNoException().isThrownBy(() -> ListUtils.removeIf(null, Objects::nonNull));
    }

    @Test
    void whenReplaceIf1() {
        ListUtils.replaceIf(input, s -> s > 1, 0);
        assertThat(input).hasSize(2).containsExactly(1, 0);
    }

    @Test
    void whenReplaceIf2() {
        ListUtils.replaceIf(input, s -> s < 4, 0);
        assertThat(input).hasSize(2).containsExactly(0, 0);
    }

    @Test
    void whenReplaceIf3() {
        assertThatNoException().isThrownBy(() -> ListUtils.replaceIf(null, Objects::nonNull, null));
    }

    @Test
    void whenRemoveAll1() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> test = List.of(1, 3, 5);
        ListUtils.removeAll(input, test);
        assertThat(input).hasSize(2).containsExactly(2, 4);
    }

    @Test
    void whenRemoveAll2() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> test = List.of(1, 2, 3, 4, 5, 6);
        ListUtils.removeAll(input, test);
        assertThat(input).hasSize(0).isEmpty();
    }

    @Test
    void whenRemoveAll3() {
        assertThatNoException().isThrownBy(() -> ListUtils.removeAll(null, null));
    }

    @Test
    void whenRemoveAll4() {
        ListUtils.removeAll(input, null);
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }

    @Test
    void whenRemoveAll5() {
        List<Integer> test = new ArrayList<>();
        ListUtils.removeAll(input, test);
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }
}