package ru.sidomik.util;

import org.assertj.core.api.Assertions;

public final class ArrayAsserts extends Assertions {

    private ArrayAsserts() {

    }

    public static ArrayAssert assertThat(int[] actual) {
        return new ArrayAssert(actual);
    }
}
