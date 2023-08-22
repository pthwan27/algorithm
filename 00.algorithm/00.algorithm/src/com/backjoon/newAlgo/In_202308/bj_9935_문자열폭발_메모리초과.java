package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_9935_문자열폭발_메모리초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String inputStr = in.readLine();
		
		String bombStr = in.readLine();
		
		while(inputStr.contains(bombStr)) {
			String[] changeStr = inputStr.split(bombStr);
			sb = new StringBuilder();
			for(int i = 0; i < changeStr.length; i++) {
				sb.append(changeStr[i]); 
			}
			
			inputStr = sb.toString();
		}
		
		System.out.println(inputStr.length() == 0 ? "FRULA" : inputStr);
	}
}
