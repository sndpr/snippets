package org.psc.misc.snippets;

import java.util.Arrays;
import java.util.Scanner;

public class MiniMaxSummarizer {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for (int arr_i = 0; arr_i < 5; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        miniMaxSum(arr);
        in.close();
    }

    static void miniMaxSum(int[] arr) {
        // Complete this function
        Arrays.sort(arr);

        long sum = Arrays.stream(arr).mapToLong(Long::valueOf).sum();
        System.out.println((sum - arr[arr.length - 1]) + " " + (sum - arr[0]));

        Arrays.sort(arr);
    }

}
