package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Iterator;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }
    @Test
    public void whenAddTwoElements() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(1));
        assertThat(set.add(1));
        assertThat(set.contains(2));
        assertThat(set.add(2));
        assertThat(set.contains(3));
        assertThat(set.add(3));
    }

    @Test
    void whenIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(100);
        set.add(500);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(100);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(500);
        assertThat(iterator.hasNext()).isFalse();
    }
}