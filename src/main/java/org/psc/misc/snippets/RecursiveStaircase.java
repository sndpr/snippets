package org.psc.misc.snippets;

import java.util.*;

public class RecursiveStaircase {

    private Map<StepConfiguration, Integer> storedSteps = new HashMap<>();
    private int allowedStepsCount;

    public static void main(String[] args) {
        RecursiveStaircase recursiveStaircase = new RecursiveStaircase();
        System.out.println(recursiveStaircase.findWays(4, Arrays.asList(1, 2)));

        recursiveStaircase = new RecursiveStaircase();
        System.out.println(recursiveStaircase.findWays(5, Arrays.asList(1, 2, 5)));
    }

    public int findWays(int totalSteps, List<Integer> allowedSteps) {
        int ways = 0;

        allowedStepsCount = allowedSteps.size();

        for (Integer allowedStep : allowedSteps) {
            ways += findWays(totalSteps, allowedStep);
        }

        return ways;
    }

    private int findWays(int totalSteps, int allowedSteps) {
        int ways = 0;
        StepConfiguration stepConfiguration = new StepConfiguration(totalSteps, allowedSteps);

        if (storedSteps.containsKey(stepConfiguration)) {
            ways = storedSteps.get(stepConfiguration);
        } else if (totalSteps >= 0 && totalSteps == allowedSteps) {
            ways = 1;
            storedSteps.put(new StepConfiguration(totalSteps, allowedSteps), ways);
        } else if (totalSteps > allowedSteps) {
            /*
            for (int i = 1; i <= allowedStepsCount; i++){
                ways += findWays(totalSteps - i, allowedSteps);
            }
            */
            ways = findWays(totalSteps - 1, allowedSteps) + findWays(totalSteps - 2,
                    allowedSteps);
            storedSteps.put(new StepConfiguration(totalSteps, allowedSteps), ways);
        }

        return ways;
    }

    private static class StepConfiguration {
        private int totalSteps;
        private int allowedSteps;

        StepConfiguration(int totalSteps, int allowedSteps) {
            this.totalSteps = totalSteps;
            this.allowedSteps = allowedSteps;
        }

        public int getTotalSteps() {
            return totalSteps;
        }

        public void setTotalSteps(int totalSteps) {
            this.totalSteps = totalSteps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StepConfiguration that = (StepConfiguration) o;
            return totalSteps == that.totalSteps &&
                    allowedSteps == that.allowedSteps;
        }

        @Override
        public int hashCode() {

            return Objects.hash(totalSteps, allowedSteps);
        }
    }
}
