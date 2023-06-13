import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		int preMooLen = 0;
		int mooLen = 3;
		int increment = 3;
		while (N > mooLen) {
			preMooLen = mooLen;
			mooLen = preMooLen * 2 + (increment + 1);
			increment++;
		}

		while (true) {
			//새로 추가된 Moooo 일때
			if (preMooLen < N && N <= preMooLen + increment) {
				N -= preMooLen;
				if(N == 1) {
					System.out.println("m");
				}else {
					System.out.println("o");
				}
				break;
			}

			//새로 추가된 Moooo기준 왼쪽
			if (N <= preMooLen) {
				mooLen = preMooLen;
				increment--;
				preMooLen = (mooLen - increment) / 2;
			}
			//오른쪽
			else {
				mooLen = preMooLen;
				N -= (mooLen + increment);
				increment--;
				preMooLen = (mooLen - increment) / 2;
			}
		}
	}

}