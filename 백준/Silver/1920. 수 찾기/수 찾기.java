import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		HashMap<Integer, Boolean> nMap = new HashMap<Integer, Boolean>();
		StringTokenizer st = new StringTokenizer(in.readLine());

		while(st.hasMoreTokens()) {
			int next = Integer.parseInt(st.nextToken());
			nMap.put(next, true);
		}
		
		int M = Integer.parseInt(in.readLine());
		
		st = new StringTokenizer(in.readLine());
		
		while(st.hasMoreTokens()) {
			int next = Integer.parseInt(st.nextToken());
			if(nMap.containsKey(next)) {
				sb.append(1 + "\n");
			}else {
				sb.append(0 + "\n");
			}
		}		
		System.out.println(sb);	
	}
}
