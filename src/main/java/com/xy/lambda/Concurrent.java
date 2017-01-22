package com.xy.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by XiuYang on 2016/10/27.
 */
public class Concurrent {
    private static final int N = 2000000000;
    private final double fraction;
    private final Map<Integer, Double> results;
    private final int numberOfThreads;
    private final ExecutorService executor;
    private final int workPerThread;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Concurrent roles = new Concurrent();
        roles.simulateDiceRoles();
        System.out.println(System.currentTimeMillis()-start);
    }
    public Concurrent() {
        fraction = 1.0 / N;
        results = new ConcurrentHashMap<>();
        numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(Runtime.getRuntime().availableProcessors());
        executor = Executors.newFixedThreadPool(numberOfThreads);
        workPerThread = N / numberOfThreads;
    }
    public void simulateDiceRoles() {
        List<Future<?>> futures = submitJobs();
        awaitCompletion(futures);
        printResults();
    }
    private void printResults() {
        results.entrySet()
                .forEach(System.out::println);
    }
    private List<Future<?>> submitJobs() {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(executor.submit(makeJob()));
        }
        return futures;
    }
    private Runnable makeJob() {
        return () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows(random);
                accumulateResult(entry);
            }
        };
    }
    private void accumulateResult(int entry) {
        results.compute(entry, (key, previous) ->
                previous == null ? fraction
                        : previous + fraction
        );
    }
    private int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }
    private void awaitCompletion(List<Future<?>> futures) {
        futures.forEach((future) -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
}
