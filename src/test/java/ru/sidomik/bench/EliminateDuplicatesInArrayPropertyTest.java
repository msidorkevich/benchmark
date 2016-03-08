package ru.sidomik.bench;

import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Ints;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static ru.sidomik.util.ArrayAsserts.assertThat;

@RunWith(JUnitQuickcheck.class)
public class EliminateDuplicatesInArrayPropertyTest {
    @Property
    public void noDuplicatesExistsNaive(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        a = EliminateDuplicates.naive(a);

        assertThat(a).doesNotHaveDuplicates();
    }

    @Property
    public void noDuplicatesExistsJavaStream(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        a = EliminateDuplicates.javaStream(a);

        assertThat(a).doesNotHaveDuplicates();
    }

    @Property
    public void noDuplicatesExistsHashSet(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        a = EliminateDuplicates.hashSet(a);

        assertThat(a).doesNotHaveDuplicates();
    }

    @Property
    public void noDuplicatesExistsSort(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        a = EliminateDuplicates.sort(a);

        assertThat(a).doesNotHaveDuplicates();
    }

    @Property
    public void compareWithEthalon(@InRange(min = "0", max = "10") int @Size(max = 100) [] a) {
        int[] naive = EliminateDuplicates.naive(a);
        int[] javaStream = EliminateDuplicates.javaStream(a);
        int[] hashSet = EliminateDuplicates.hashSet(a);
        int[] sort = EliminateDuplicates.sort(a);

        int[] expected = Ints.toArray(ImmutableSet.copyOf(Ints.asList(a)));

        assertThat(naive).isEqualIgnoreOrder(expected);
        assertThat(javaStream).isEqualIgnoreOrder(expected);
        assertThat(hashSet).isEqualIgnoreOrder(expected);
        assertThat(sort).isEqualIgnoreOrder(expected);
    }
}