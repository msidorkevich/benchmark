package ru.sidomik.bench;

public final class Sort {

    private Sort() {

    }

    public static void mergeSort(int[] a) {
        int[] from = a, to = new int[a.length];
        for (int blockSize = 1; blockSize < a.length; blockSize *= 2) {
            for (int start = 0; start < a.length; start += 2 * blockSize) {
                merge(from, to, start, start + blockSize, start + 2 * blockSize);
            }
            int[] temp = from;
            from = to;
            to = temp;
        }
        if (from != a) {
            System.arraycopy(from, 0, a, 0, from.length);
        }
    }

    private static void merge(int[] from, int[] to, int lo, int mid, int hi) {
        if (mid > from.length) {
            mid = from.length;
        }
        if (hi > from.length) {
            hi = from.length;
        }
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) {
                to[k] = from[j++];
            } else if (j == hi) {
                to[k] = from[i++];
            } else if (from[j] < from[i]) {
                to[k] = from[j++];
            } else {
                to[k] = from[i++];
            }
        }
    }
}
