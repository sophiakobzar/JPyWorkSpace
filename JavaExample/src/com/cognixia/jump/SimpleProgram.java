package com.cognixia.jump;

import java.util.Scanner;

public class SimpleProgram {

	public static void main(String[] args) {
		
		// single line comment 
		
		/* 	this is a 
		 	java doc	 
		 	comment	*/
		
		System.out.println("Hello");
		System.out.println("Hello2");
		System.out.print("Hello 3");
		System.out.print("Hello 4\n");
		
		
		int i = 5;
		double d = 3.14;
		char c = 'a';
		float f = 2.108f;
		
		System.out.println("\nint: " + i);
		System.out.println("double: " +d);
		System.out.println("char: " +c);
		System.out.println("float: "+f);		 
		// constants 
		System.out.println("\nconstants");
		
		final int iF = 5;
		final double dF = 3.14;
		final char cF = 'a';
		final float fF = 2.108f;
		
		System.out.println("int: " + iF);
		System.out.println("double: " +dF);
		System.out.println("char: " +cF);
		System.out.println("float: "+fF +"\n");
		
		System.out.println(3 + 4 * 5); // 23
		System.out.println((3 + 4) * 5);//35
		System.out.println("\nhello " + 4*2);
		System.out.println("hello " + 4+2);
		System.out.println("hello " + (4*2));
		
		// scanner
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		System.out.println("hello "+name);
		
		System.out.print("Enter your age: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Your age is "+age);
		
		System.out.print("Enter a num: ");
		double num = Double.parseDouble(sc.nextLine());
		//sc.nextLine();
		System.out.println("Your entered "+num);
		
		Integer a = 4;
		
		Double.toString(d);
		Integer.toString(a);
		
		sc.close();
	}
}