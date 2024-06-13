package com.cognixia.jump;

public class FlowControlExample {
	public static void main(String[] args)
	{
		ifStatment();
	}
	public static void ifStatment() {
		int num1 = 3;
		if(num1 > 0)
		{
			System.out.println(num1 + " is positive");
		}
		else if(num1<0)
		{
			System.out.println(num1 + " is negative");
		}
		else if(num1 > 2) // will never get because  2>0 first if is 1st
		{
			System.out.println(num1 + " is larger than 2");
		}
		else
		{
			System.out.println(num1 + " is zero");
		}
		
		if(num1 % 2==0)
		{
			System.out.println(num1 +" is even");
			if(num1 > 0)
			{
				System.out.println(num1 + " is positive");
			}
		}
		else
		{
			System.out.println(num1 +" is odd");
		}
	}
}
