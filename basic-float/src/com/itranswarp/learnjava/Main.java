package com.itranswarp.learnjava;

/**
 * 求解 一元二次方程ax^2+bx+c=0
 */
public class Main {

	public static void main(String[] args) {
		// x*x + 3*x - 4 = 0
		double a = 1.0;
		double b = 3.0;
		double c = -4.0;
		// 求平方根可用 Math.sqrt():
		// double x = Math.sqrt(2)); // ==> 1.414
		// TODO:
		double r1 = ((-1*b) + Math.sqrt(b*b - 4*a*c)) / (2*a);
		double r2 = ((-1*b) - Math.sqrt(b*b - 4*a*c)) / (2*a);;
		System.out.println(r1 + ", " + r2);
		System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");
	}

}
