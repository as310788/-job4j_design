package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    void whenRemoveIf() {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, f -> f > 2);
        assertThat(input).hasSize(3).containsSequence(0, 1, 2);
    }

        @Test
        void whenReplaceIf() {
            List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
            ListUtils.replaceIf(input, el -> el % 2 == 0, 0);
            assertThat(input).hasSize(5).containsSequence(0, 1, 0, 3, 0);

    }

    @Test
    void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 4));
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }
}