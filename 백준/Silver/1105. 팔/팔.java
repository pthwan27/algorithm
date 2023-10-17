import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] inputStr = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");

		int result = 0;

		String LString = inputStr[0];
		String RString = inputStr[1];

		int L = Integer.parseInt(LString);
		int R = Integer.parseInt(RString);

		if (L == R) {
			for (int i = 0; i < LString.length(); i++) {
				if (LString.charAt(i) == '8') {
					result++;
				}
			}
		} else {
			if (LString.length() != RString.length()) {
				result = 0;
			} else {
				int start = 0;

				while (start < LString.length()) {
					if (LString.charAt(start) == RString.charAt(start)) {
						if (LString.charAt(start) == '8')
							result++;
					} else {
						break;
					}
					start++;
				}
			}

		}

		System.out.println(result);
	}

}
