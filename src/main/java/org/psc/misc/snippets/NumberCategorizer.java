package org.psc.misc.snippets;

import java.util.Arrays;
import java.util.Scanner;

public class NumberCategorizer {

    static void plusMinus(int[] arr) {
        // Complete this function
        System.out.println((double) Arrays.stream(arr).filter(e -> e > 0).count() / arr.length);
        System.out.println((double) Arrays.stream(arr).filter(e -> e == 0).count() / arr.length);
        System.out.println((double) Arrays.stream(arr).filter(e -> e < 0).count() / arr.length);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        plusMinus(arr);
        in.close();
    }
}
