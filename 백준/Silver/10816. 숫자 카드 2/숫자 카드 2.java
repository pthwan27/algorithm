import java.util.*;
import java.io.*;

// HashMap에 담아서도 풀 수 있을 듯 ?
// 있는지 없는지 확인하면서 
public class Main {
	static int N;
	static int[] numArr;

	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		numArr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());

		int i = 0;
		while (st.hasMoreTokens()) {
			numArr[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		Arrays.sort(numArr);

		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (i = 0; i < M; i++) {
			int targetNum = Integer.parseInt(st.nextToken());

			int start = startPosition(targetNum);
			int end = endPosition(targetNum);

			sb.append(end - start).append(" ");
		}

		System.out.println(sb);
	}

	//타켓이랑 같은 값 시작위치
	private static int startPosition(int targetNum) {
		int start = 0;
		int end = numArr.length;

		while (start < end) {
			int center = (start + end) / 2;
			if (targetNum <= numArr[center]) {
				end = center;
			} else {
				start = center + 1;
			}
		}
		return start;
	}
	
	//target보다 큰 값 위치
	private static int endPosition(int targetNum) {
		int start = 0;
		int end = numArr.length;
		
		while(start < end) {
			int center = (start+end) / 2;
			if(targetNum < numArr[center]) {
				end = center;
			}else {
				start = center+1;
			}
		}
		return start;
	}

}