package ru.sidomik.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 3, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class EliminateDuplicatesBenchmark {
    private int[] a;

    @Param({"10", "100", "1000", "10000", "100000", "1000000", "10000000"})
    private int arraySize;

    @Setup
    public void prepare() {
        Random r = new Random();

        a = new int[arraySize];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(arraySize * 100);
        }

        System.out.println(a.length - IntStream.of(a).distinct().count() + " duplications");
    }

    @Benchmark
    public int[] naive() {
        return EliminateDuplicates.naive(a);
    }

    @Benchmark
    public int[] viaJavaStream() {
        return EliminateDuplicates.javaStream(a);
    }

    @Benchmark
    public int[] hashSet() {
        return EliminateDuplicates.hashSet(a);
    }

    @Benchmark
    public int[] sort() {
        return EliminateDuplicates.sort(a);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(EliminateDuplicatesBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
