package com.itranswarp.learnjava;

import java.util.Scanner;

/**
 * switch实现石头/剪子/布并判断胜负
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("please choice:");
		System.out.println(" 1: Rock");
		System.out.println(" 2: Scissors");
		System.out.println(" 3: Paper");
		// 用户输入:
		Scanner scanner = new Scanner(System.in);
		String opt = scanner.nextLine();
		// 计算机随机数 1, 2, 3:
		int random = 1 + (int) Math.random() * 3;
		System.out.printf("PC的选择是：%s\n",random == 1?"石头":random == 2? "剪刀":"布");
		switch (opt) {
		// TODO:
		case "1" -> System.out.print(random==1? "Even":random==2? "Win":"Loss");
		case "2" -> System.out.print(random==1? "Loss":random==2? "Even":"Loss");
		case "3" -> System.out.print(random==1? "Win":random==2? "Loss":"Even");
		default -> System.out.print("Please input the correct number");
		}
	}

}
