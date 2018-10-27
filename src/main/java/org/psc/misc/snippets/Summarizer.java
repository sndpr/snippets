package org.psc.misc.snippets;

import java.util.Arrays;
import java.util.Scanner;

public class Summarizer {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int ar_i = 0; ar_i < n; ar_i++) {
            ar[ar_i] = in.nextInt();
        }
        int result = simpleArraySum(n, ar);
        System.out.println(result);
    }

    static int simpleArraySum(int n, int[] ar) {
        // Complete this function
        return Arrays.stream(ar).sum();
    }

}
