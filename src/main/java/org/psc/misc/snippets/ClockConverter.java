package org.psc.misc.snippets;

import java.util.Scanner;

public class ClockConverter {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String result = timeConversion(s);
		System.out.println(result);
	}

	static String timeConversion(String s) {
		int hours = Integer.parseInt(s.substring(0, 2));
		int add = s.toLowerCase().contains("pm") && hours != 12 ? 12
				: s.toLowerCase().contains("am") && hours == 12 ? -12 : 0;
		hours = (Integer.parseInt(s.substring(0, 2)) + add) % 24;
		return String.format("%1$02d", hours) + s.substring(2, 8);
	}

}
