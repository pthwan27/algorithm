import java.util.*;
import java.io.*;

public class Main {

	static String inputStr1;
	static String inputStr2;
	
	static int[][] dp;
	
	static int MaxLen = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		inputStr1 = in.readLine();
		inputStr2 = in.readLine();
		int str1Len = inputStr1.length();
		int str2Len = inputStr2.length();
		
		dp = new int[str1Len+1][str2Len+1];
		
		for(int r = 1; r <= str1Len; r++) {
			for(int c = 1; c <= str2Len; c++) {
				if(inputStr1.charAt(r-1) == inputStr2.charAt(c-1)) {
					//AB, CB -> ABC CBC 는 이전 값 + 1이랑 같기 때문에 
					dp[r][c] = dp[r-1][c-1] + 1;
				}else {
					//공통 부분수열은 이전 값을 계속해서 가지고 가야되기 때문에
					dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
				}				
			}
		}
		
		int maxLen = dp[str1Len][str2Len];
		char[] resultArr = new char[maxLen];
		
		int i = maxLen; int r = str1Len; int c = str2Len;
		while(dp[r][c] != 0 && i != 0 ) {
			if(inputStr1.charAt(r-1) == inputStr2.charAt(c-1)) {
				resultArr[i-1] = inputStr1.charAt(r-1);
				i--;
				r--;
				c--;
			}else if(dp[r][c] == dp[r-1][c]){
				r--;
			}else {
				c--;
			}
		}
		String result = "";
		for(i = 0; i < maxLen; i++) {
			result+=resultArr[i];
		}
		System.out.println(maxLen + "\n" + result);
	}
}