package ru.sidomik.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 3, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ArraySortBenchmark {

    private int[] a;

    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
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
    public int[] standardSort() {
        int[] copy = a.clone();
        Arrays.sort(copy);
        return copy;
    }

    @Benchmark
    public int[] mergeSort() {
        int[] copy = a.clone();
        Sort.mergeSort(copy);
        return copy;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ArraySortBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
