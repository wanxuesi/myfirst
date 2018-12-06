package com.fuguo.util;

public class Son {

	/**
	 * @param args
	 */
	public static void print99Chengfa() {

		int result = 0;
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				result = j * i;

				System.out.print(j + "*" + i + "=" + result + " ");

				if (j == i) {
					System.out.println("\n");
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		print99Chengfa();

	}

}
