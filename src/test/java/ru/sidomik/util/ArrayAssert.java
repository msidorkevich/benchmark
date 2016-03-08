package ru.sidomik.util;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.IntArrayAssert;

public class ArrayAssert extends IntArrayAssert {

    protected ArrayAssert(int[] actual) {
        super(actual);
    }

    public static ArrayAssert assertThat(int[] actual) {
        return new ArrayAssert(actual);
    }

    public ArrayAssert isEqualIgnoreOrder(int[] values) {
        isNotNull();

        Assertions.assertThat(actual).hasSameSizeAs(values);
        Assertions.assertThat(actual).contains(values);
        Assertions.assertThat(values).contains(actual);

        return this;
    }
}
