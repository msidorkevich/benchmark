package ru.sidomik.bench;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    @Test
    public void compareWithEthalon() {
        compareWithEthalon(new int[] {0, 0, 0, 1, 1, 1});
        compareWithEthalon(new int[] {9, 7});
        compareWithEthalon(new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10});
        compareWithEthalon(new int[] {});
    }

    private static void compareWithEthalon(int[] a) {
        int[] merge = a.clone();
        int[] standard = a.clone();
        Sort.mergeSort(merge);
        Arrays.sort(standard);

        assertArrayEquals(standard, merge);
    }
}
