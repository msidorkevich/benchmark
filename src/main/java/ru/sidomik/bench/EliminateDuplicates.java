package ru.sidomik.bench;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * All implementations do not change the original array.
 * Order may be changed in result.
 */
public final class EliminateDuplicates {

    private EliminateDuplicates() {

    }

    public static int[] naive(int[] a) {
        int[] temp = new int[a.length];
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            boolean unique = true;
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                temp[k++] = a[i];
            }
        }
        return copyArray(temp, k);
    }

    public static int[] javaStream(int[] a) {
        return IntStream.of(a).distinct().toArray();
    }

    public static int[] hashSet(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int uniqueInt : set) {
            result[i++] = uniqueInt;
        }
        return result;
    }

    public static int[] sort(int[] a) {
        if (a.length == 0) {
            return a;
        }

        int[] sorted = a.clone();
        Arrays.sort(sorted);
        int[] temp = new int[a.length];
        int j = 0;
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i] != sorted[i + 1]) {
                temp[j++] = sorted[i];
            }
        }
        temp[j] = sorted[sorted.length - 1];

        return copyArray(temp, j + 1);
    }

    private static int[] copyArray(int[] a, int length) {
        int[] result = new int[length];
        System.arraycopy(a, 0, result, 0, length);
        return result;
    }
}
