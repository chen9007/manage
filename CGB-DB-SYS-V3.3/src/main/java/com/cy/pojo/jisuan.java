package com.cy.pojo;

import java.util.Scanner;

public class jisuan {
	public static void main(String[] args) {
		System.out.println("输入i的值:");
		int i=new Scanner(System.in).nextInt();
		int sum=0;
		int start=i*i-(i-1);
		for (int j = start; j <=start+2*i-2; j++) {
			if (j%2==1) {
				
				sum+=j;
			}
		}
		System.out.println(sum);
	}
}
