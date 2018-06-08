package org.psc.misc.snippets;

import java.util.Collections;
import java.util.Scanner;

public class StaircasePrinter {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		staircase(n);
		in.close();
	}

	static void staircase(int n) {
		for (int i = 0; i < n; i++) {
			String whitespaces = Collections.nCopies(n - i - 1, " ").stream().reduce((e, res) -> res += e).orElse("");
			String pounds = Collections.nCopies(i + 1, "#").stream().reduce((e, res) -> res += e).orElse("");
			System.out.println(whitespaces + pounds);
		}
	}

}
