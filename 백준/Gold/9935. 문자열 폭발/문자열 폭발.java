
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String inputStr = in.readLine();

		String bombStr = in.readLine();

		Stack<Character> alphaStack = new Stack<>();
		for (int a = 0; a < inputStr.length(); a++) {
			alphaStack.push(inputStr.charAt(a));

			if (alphaStack.size() >= bombStr.length()) {
				if (isContainsBomb(alphaStack, bombStr)) {
					for (int b = 0; b < bombStr.length(); b++) {
						alphaStack.pop();
					}
				}
			}
		}

		while (!alphaStack.isEmpty()) {
			sb.append(alphaStack.pop());
		}
		inputStr = sb.reverse().toString();
		
		System.out.println(inputStr.length() == 0 ? "FRULA" : inputStr);
	}

	public static boolean isContainsBomb(Stack<Character> stack, String bomb) {
		for (int i = 0; i < bomb.length(); i++) {
			if (stack.get(stack.size() - bomb.length() + i) != bomb.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
