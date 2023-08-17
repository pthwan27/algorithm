package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_2954_창영이의일기장 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String inputStr = in.readLine();

		inputStr = inputStr.replace("apa", "a");
		inputStr = inputStr.replace("epe", "e");
		inputStr = inputStr.replace("ipi", "i");
		inputStr = inputStr.replace("opo", "o");
		inputStr = inputStr.replace("upu", "u");
		
		System.out.println(inputStr);
	}

}
