package ru.sidomik.bench;

import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class EliminateDuplicatesTest {

    @Test
    public void naive() {
        testRemoveDuplicates(new int[] {0, 0, 0, 1, 1, 1}, EliminateDuplicates::naive);
        testRemoveDuplicates(new int[] {9, 7}, EliminateDuplicates::naive);
        testRemoveDuplicates(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10}, EliminateDuplicates::naive);
        testRemoveDuplicates(new int[] {}, EliminateDuplicates::naive);
    }

    @Test
    public void javaStream() {
        testRemoveDuplicates(new int[] {0, 0, 0, 1, 1, 1}, EliminateDuplicates::javaStream);
        testRemoveDuplicates(new int[] {9, 7}, EliminateDuplicates::javaStream);
        testRemoveDuplicates(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10}, EliminateDuplicates::javaStream);
        testRemoveDuplicates(new int[] {}, EliminateDuplicates::javaStream);
    }

    @Test
    public void hashSet() {
        testRemoveDuplicates(new int[] {0, 0, 0, 1, 1, 1}, EliminateDuplicates::hashSet);
        testRemoveDuplicates(new int[] {9, 7}, EliminateDuplicates::hashSet);
        testRemoveDuplicates(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10}, EliminateDuplicates::hashSet);
        testRemoveDuplicates(new int[] {}, EliminateDuplicates::hashSet);
    }

    @Test
    public void sort() {
        testRemoveDuplicates(new int[] {0, 0, 0, 1, 1, 1}, EliminateDuplicates::sort);
        testRemoveDuplicates(new int[] {9, 7}, EliminateDuplicates::sort);
        testRemoveDuplicates(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10}, EliminateDuplicates::sort);
        testRemoveDuplicates(new int[] {}, EliminateDuplicates::sort);
        testRemoveDuplicates(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10}, EliminateDuplicates::sort);
    }

    private static void testRemoveDuplicates(int[] data, Function<int[], int[]> removeDuplicates) {
        assertThat(removeDuplicates.apply(data)).doesNotHaveDuplicates();
    }
}
