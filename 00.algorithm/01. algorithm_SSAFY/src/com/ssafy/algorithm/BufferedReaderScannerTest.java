package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BufferedReaderScannerTest {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("inputTC.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		

		int Tc = Integer.parseInt(in.readLine());
		
		for (int c = 1; c <= Tc; c++) {

			long start = System.nanoTime();
			int N = Integer.parseInt(in.readLine());
			
			int Sum = 0;

			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j = 0; j < N; j++) {
					Sum += Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + c + " " + Sum);
			
			long end = System.nanoTime();
			
			System.out.println((end - start) / 100000000);
		}
	
	}

	
	public static void main2 (String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("inputTC.txt"));
		Scanner sc = new Scanner(System.in);

		long start = System.nanoTime();
		int Tc = sc.nextInt();
		for (int c = 1; c <= Tc; c++) {
			int N = sc.nextInt();

			int Sum = 0;

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					Sum += sc.nextInt();					
				}
			}
			System.out.println("#" + c + " " + Sum);
		}
		long end = System.nanoTime();
		
		System.out.println((end - start) / 1000000000);
	}

}
