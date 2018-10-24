package org.psc.misc.snippets;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FairDistributor {

    private static int equal(int[] arr) {
        // Complete this function
        List<Integer> distinctValues = Arrays.stream(arr).boxed().distinct().collect(Collectors.toList());

        return distinctValues.stream().map(FairDistributor::calcMinEqualizationSteps).mapToInt(Integer::valueOf).max()
                .orElse(0);

    }

    @org.jetbrains.annotations.Contract(pure = true)
    private static int calcMinEqualizationSteps(int difference) {
        int steps = difference / 5;
        int remainder = difference - (steps * 5);

        int step2 = remainder / 2;
        steps += step2;
        remainder -= step2 * 2;

        steps += remainder;

        return steps;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int arr_i = 0; arr_i < n; arr_i++) {
                arr[arr_i] = in.nextInt();
            }
            int result = equal(arr);
            System.out.println(result);
        }
        in.close();
    }
}
