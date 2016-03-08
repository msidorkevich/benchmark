package ru.sidomik.bench;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static ru.sidomik.util.ArrayAsserts.assertThat;

@RunWith(JUnitQuickcheck.class)
public class SortPropertyTest {

    @Property
    public void isSorted(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        Sort.mergeSort(a);

        assertThat(a).isSorted();
    }

    @Property
    public void compareWithEthalon(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        int[] actual = a.clone();
        Sort.mergeSort(actual);
        Arrays.sort(a);

        assertThat(actual).isEqualTo(a);
    }
}
