import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input = in.readLine();

		int aCnt = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == 'a') {
				aCnt++;
			}
		}
		int start = 0;
		int end = aCnt;

		int minResult = Integer.MAX_VALUE;
		
		int changeCnt = 0;
		for (int i = 0; i < end; i++) {
			if (input.charAt(i) == 'b') {
				changeCnt++;
			}
		}
		minResult = changeCnt;
		
		while (start < input.length()) {
			if(input.charAt(start) == 'b') {
				changeCnt--;
			}
			
			if(input.charAt((end) % input.length()) == 'b') {
				changeCnt++;
			}
			
			minResult = Math.min(minResult, changeCnt);
			
			start++;
			end++;			
		}
		
		System.out.println(minResult);
	}

}
