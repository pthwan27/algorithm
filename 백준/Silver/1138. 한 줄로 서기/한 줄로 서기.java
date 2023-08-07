import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		
		int[] result = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int a = 1; a <= n; a++) {
			// 왼쪽에 서 있는 큰 사람 수
			int leftCnt = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			 // 왼쪽 서 있는 사람 수가 들어갈 수 있는 위치 찾기
			for(int b = 1; b <= n; b++) {
				if(cnt == leftCnt && result[b] == 0) {
					result[b] = a;
					break;
				}
				
				if(result[b] == 0) {
					cnt++;
				}
			}			
		}
		
		for(int i = 1; i <= n; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}