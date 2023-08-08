package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;
import java.math.*;

public class bj_1793_타일링 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger[] dp = new BigInteger[251];
		
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");
		dp[3] = new BigInteger("5");
		
		
		for(int i = 4; i < 251; i++) {
			dp[i] = dp[i-2].multiply(new BigInteger("2"));
			dp[i] = dp[i].add(dp[i-1]);
		}
		
		String inputStr = null;
		while((inputStr = in.readLine()) != null) {			
			int N = Integer.parseInt(inputStr);
			System.out.println(dp[N]);
		}		
	}

}
