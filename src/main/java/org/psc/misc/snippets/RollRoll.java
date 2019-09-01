package org.psc.misc.snippets;

import lombok.AllArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RollRoll {

    private static SecureRandom random;
    private static final int THREAD_COUNT = 8;
    private static final int TARGET_HITS = 100;

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        random = SecureRandom.getInstanceStrong();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<RollRunner> rollRunners = new ArrayList<>();

        int remainingTargetHits = TARGET_HITS;
        for (int i = 0; i < THREAD_COUNT; i++) {
            int desiredHits = TARGET_HITS / THREAD_COUNT;
            remainingTargetHits -= desiredHits;
            if (i == THREAD_COUNT - 1) {
                desiredHits = remainingTargetHits;
            }
            rollRunners.add(new RollRunner(desiredHits));
        }

        List<Integer> requiredTries = executorService.invokeAll(rollRunners).stream().map(e -> {
            List<Integer> result = null;
            try {
                result = e.get();
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
            return result;
        }).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println("--------------------");
        requiredTries.forEach(System.out::println);
        System.out.println("average: " + requiredTries.stream().collect(Collectors.averagingDouble(Double::valueOf)));
    }

    private static List<Integer> roll() {
        return random.ints(4, 1, 100).boxed().collect(Collectors.toList());
    }


    @AllArgsConstructor
    private static class RollRunner implements Callable<List<Integer>> {

        private final int desiredHits;

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> requiredTries = new ArrayList<>();
            for (int i = 0; i < desiredHits; i++) {
                boolean hitDouble = false;
                int tries = 0;
                while (!hitDouble) {
                    List<Integer> rolls = RollRoll.roll();
                    hitDouble = rolls.get(0).equals(rolls.get(1)) && rolls.get(2).equals(rolls.get(3));
                    tries++;
                    if (hitDouble) {
                        requiredTries.add(tries);
                        System.out.println("hit after " + tries + " rolls. " +
                                rolls.stream().map(String::valueOf).collect(Collectors.joining(", ", "{", "}.")));
                    } else if (tries % 1000 == 0) {
                        System.out.println("NO hit after " + tries + " rolls");
                    }
                }
            }
            return requiredTries;
        }
    }
}
