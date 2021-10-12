package com.demo.javademo.concurrency.forkjoin.recursivetask;

import com.demo.javademo.concurrency.forkjoin.util.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class LongSumBenchmark {

    @Param({"20000000"})
    public int N;

    @Param({"4", "10", "100"})
    public int M;

    @Param({"1000"})
    public int NUM;

    public int[] arrayToSum;
    public int[] arrayToSumSingleThread;
    public ForkJoinPool pool;

    @Setup(Level.Iteration)
    public void init() {
        arrayToSum = Utils.buildRandomIntArray(N);
        arrayToSumSingleThread = Arrays.copyOf(arrayToSum, arrayToSum.length);

        pool = new ForkJoinPool(M);
    }

    @TearDown(Level.Iteration)
    public void destroy() {
        pool.shutdown();
    }

    @Benchmark
    public long multiThreadTask() throws Exception {
        LongSumForkJoinTask ls = new LongSumForkJoinTask(arrayToSum, 0, arrayToSum.length);
        long result = pool.submit(ls).get();
        return result;
    }

    @Benchmark
    public long singleThreadTask() {
        return LongSumMain.seqSum(arrayToSumSingleThread);
    }
}