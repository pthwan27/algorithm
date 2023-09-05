import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, R, C;

	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int Size = (int) Math.pow(2, N);		
		find(R, C, Size);
		
		System.out.println(result);
	}

	private static void find(int r, int c, int size) {	
		//목표 위치에 도착
		if(size == 1) {
			return;
		}
		
		// result += Math.pow(divSize,2) * 1;
		// 재귀로 나눠지는 사각형의 시작 순번(0,0) 더해주기		
		
		int divSize = size / 2;
		
		//4분의 1씩 나눠서 탐색
		//왼쪽 위 탐색
		if(r < divSize && c < divSize) {
			find(r, c, divSize);			
		}		
		//오른쪽 위
		else if(r < divSize && c >= divSize) {			
			result += Math.pow(divSize,2) * 1;
			find(r, c-divSize, divSize);
			
		}
		//왼쪽 아래
		else if(r >= divSize && c < divSize) {
			result += Math.pow(divSize,2) * 2;
			find(r-divSize, c, divSize);
		}
		//오른쪽 아래
		else {
			result += Math.pow(divSize,2) * 3;
			find(r-divSize, c-divSize, divSize);
		}		
	}
}
