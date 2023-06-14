import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] sumArr;
	static long[] remainArr;
	
	static long result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sumArr = new long[N];
		
		//나머지의 갯수
		remainArr = new long[M];
		
		//결과
		result = 0;
		
		st = new StringTokenizer(in.readLine());
		
		sumArr[0] = Integer.parseInt(st.nextToken());
		
		for(int i =1; i < N; i++) {
			sumArr[i] = sumArr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			int remainder = (int) (sumArr[i] % M);
			if(remainder == 0) {
				result++;
			}
			remainArr[remainder]++;
		}
		

		for(int i = 0; i < M; i++) {
			if(remainArr[i] > 1) {
				result += (remainArr[i] * (remainArr[i] - 1) / 2 );
			}
		}
		
		System.out.println(result);
	}

}
