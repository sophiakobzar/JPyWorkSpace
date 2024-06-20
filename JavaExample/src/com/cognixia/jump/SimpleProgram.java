package com.cognixia.jump;

import java.util.Scanner;

public class SimpleProgram {

	public static void main(String[] args) {
		// scanner
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name  : ");
		String name = sc.nextLine();
		String reverseName = new StringBuilder(name).reverse().toString();
		System.out.println("using built in   : " + reverseName);
		System.out.println("created function : " + reverseString(name.toCharArray()));
		sc.close();
	}
	
	public static String reverseString(char[] str)
	{
		char[] reCharacters = new char[str.length];
		int index = 0;
		for(int i = (str.length-1); i >=0; i--)
		{
			reCharacters[index] = str[i];
			index++;
		}
		return new String(reCharacters);
	}
}