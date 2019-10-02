package org.psc.misc.snippets;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ConcurrentTasksRunner {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> firstTask = executorService.submit(() -> {
            for (int i = 0; i < 10000; i++) {
                SecureRandom secureRandom = null;
                try {
                    secureRandom = SecureRandom.getInstanceStrong();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                assert secureRandom != null;
                List<Integer> collect = secureRandom.ints(10, 1, 100).boxed().collect(Collectors.toList());
            }
            System.out.println("task one done");
        });

        Future<?> secondTask = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task two done");
        });

        Future<?> thirdTask = executorService.submit(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task three done");
        });

        executorService.shutdown();

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future");
        });

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  "near future";
        }).thenAcceptAsync(System.out::println);

        System.out.println("present");
    }

}
