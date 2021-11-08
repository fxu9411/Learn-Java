package com.itranswarp.learnjava;

/**
 * 二维数组
 */
public class Main {
	public static void main(String[] args) {
		// 用二维数组表示的学生成绩:
		int[][] scores = { //
				{ 82, 90, 91 }, //
				{ 68, 72, 64 }, //
				{ 95, 91, 89 }, //
				{ 67, 52, 60 }, //
				{ 79, 81, 85 }, //
		};
		int count = 0;
		int sum = 0;
		// TODO:
		for (int i = 0; i < scores.length; i++) {
			for (int j = 0; j < scores[i].length; j++) {
				count += 1;
				sum += scores[i][j];
			}
		}
		double average = (double) sum/count;
		System.out.println(average);
		if (Math.abs(average - 77.733333) < 0.000001) {
			System.out.println("测试成功");
		} else {
			System.out.println("测试失败");
		}
	}
}
