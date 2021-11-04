package com.itranswarp.learnjava;

/**
 * for练习
 */
public class Main {
	public static void main(String[] args) {
		double pi = 0;
		for (double i = 1; i < 100000; i += 4) {
			// TODO:
			pi += 4/i;
			pi -= 4/(i+2);
		}
		System.out.println(pi);
	}
}