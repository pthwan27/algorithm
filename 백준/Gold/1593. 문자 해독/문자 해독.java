import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int wLen;
	static int sLen;

	static String W;
	static String S;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		wLen = Integer.parseInt(st.nextToken());
		sLen = Integer.parseInt(st.nextToken());

		W = in.readLine();
		S = in.readLine();

		int[] wArr = new int[52];
		int[] sArr = new int[52];

		for (char c : W.toCharArray()) {
			inputChar(c, wArr, 1);
		}

		int start = 0;
		int cnt = 0;
		int size = 0;

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			inputChar(c, sArr, 1);
			size++;
			
			//array1.equals(array2)는 array1 == array2 같은지 비교
			//Arrays.equals(array1,array2)는 두 배열의 내용물들이 같은지를 비교
			if(size == W.length()) {
				if(Arrays.equals(wArr, sArr)) {
					cnt++;
				}
				inputChar(S.charAt(start), sArr, -1);
				start++;
				size--;
				
			}
		}
		
		System.out.println(cnt);
	}

	private static void inputChar(char c, int[] arr, int w) {
		if ('A' <= c && c <= 'Z') {
			arr[c - 'A'] += w;
		} else {
			arr[c - 'a' + 26] += w;
		}
	}

}
