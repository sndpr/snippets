package org.psc.misc.snippets;

import java.util.Scanner;

public class ScoreComparator {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        int[] result = solve(a0, a1, a2, b0, b1, b2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");

    }

    static int[] solve(int a0, int a1, int a2, int b0, int b1, int b2) {
        // Complete this function
        int[] scores = new int[2];
        scores[0] = comparePartialScore(a0, b0) + comparePartialScore(a1, b1) + comparePartialScore(a2, b2);
        scores[1] = comparePartialScore(b0, a0) + comparePartialScore(b1, a1) + comparePartialScore(b2, a2);

        return scores;

    }

    private static int comparePartialScore(int a, int b) {
        return Math.max(0, Integer.valueOf(a).compareTo(b));
    }

}
