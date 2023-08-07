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
		
		//누적합구하기
		sumArr[0] = Integer.parseInt(st.nextToken());
		for(int i =1; i < N; i++) {
			sumArr[i] = sumArr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		//나머지 갯수 찾기, 이 때 누적합이 0이다? -> 구간의 합이 M으로 나누어 떨어진다 -> result++
		for(int i = 0; i < N; i++) {
			int remainder = (int) (sumArr[i] % M);
			if(remainder == 0) {
				result++;
			}
			remainArr[remainder]++;
		}

		// 나머지개수 C２
		// 나머지 개수가 2개 이상인것들은 크기가 2인 조합을 통해 갯수를 찾는다 
		// -> 1~3 , 1~5의 나머지가 같으면 1~5 빼기 1~3을 빼면 나머지가 0, 0은? M으로 나누어 떨어진다. -> result++
		for(int i = 0; i < M; i++) {
			if(remainArr[i] > 1) {
				result += (remainArr[i] * (remainArr[i] - 1) / 2 );
			}
		}
		
		System.out.println(result);
	}

}
