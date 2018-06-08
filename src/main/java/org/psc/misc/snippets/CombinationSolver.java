package org.psc.misc.snippets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CombinationSolver {

	private static Map<Long, Long> counts = new HashMap<>();
	
	static long getWays(long n, long[] c) {
		// Complete this function
		Arrays.sort(c);

/*
		long count = 0;

		for (int i = 0; i < c.length; i++) {
			double res = n / (double) c[i];
			long newBase = n - (long) res;
			long remainder = n % c[i];
			
			if (remainder == 0) {
				count++;
				if (newBase > 0) {
					count += getWays(newBase, Arrays.copyOfRange(c, 0, i));
				}
			} else if (res >= 1){
				count += getWays(remainder, c);
			}
		}
		
*/
		
		long count = 0;
		
		for (int i = 0; i < c.length; i++) {
			count +=  getWaysForN(n, 1, c);
		}
		
		return count;
	}
	
	private static long getWaysForN(long n, long sub, long[] c) {
		
		Long waysForN = counts.get(n);
		
		if (waysForN != null) {
			return waysForN; 
		}
		else if(n == c[0]) {
			counts.put(n, Long.valueOf(1L));
			return 1;
		}
		else if (n <= 0) {
			return 0;
		}
		else {
			return getWaysForN(n - sub, sub, c);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		long[] c = new long[m];
		for (int c_i = 0; c_i < m; c_i++) {
			c[c_i] = in.nextLong();
		}
		// Print the number of ways of making change for 'n' units using coins having
		// the values given by 'c'
		long ways = getWays(n, c);
		System.out.println(ways);
	}
}
