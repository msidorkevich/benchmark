package ru.sidomik.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 3, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SampleBenchmark {

    private int[] a;
    private Blackhole bh = new Blackhole();

    @Setup
    public void setup() {
        a = new int[] {4, 2, 1, 5, 1, 6, 8, 6, 10, 9, 10};
    }

    @Benchmark
    public int[] baseline() {
        return a;
    }

    @Benchmark
    public int[] copy() {
        return a.clone();
    }

    @Benchmark
    public void forLoop() {
        for (int i : a) {
            bh.consume(i);
        }
    }

    @Benchmark
    public void copyAndForLoop() {
        int[] copy = a.clone();
        for (int i : copy) {
            bh.consume(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SampleBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
