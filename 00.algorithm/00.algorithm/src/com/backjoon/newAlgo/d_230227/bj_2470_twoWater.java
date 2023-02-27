import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2470_twoWater {
    static int N;
    static int[] nArr;

    static int minSum = Integer.MAX_VALUE;
    static int ansStart, ansEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        nArr = new int[N];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArr);

        int start = 0;
        int end = nArr.length - 1;
        ansStart = 0;
        ansEnd = nArr.length - 1;

        System.out.println(Arrays.toString(nArr));

        //-99 -2 -1 4 98
        while (start < end) {
            int temp = nArr[start] + nArr[end];

            //양 끝을 더한게 0일 때
            if (temp == 0) {
                ansStart = nArr[start];
                ansEnd = nArr[end];

                break;
            }

            if (Math.abs(temp) < Math.abs(minSum)) {
                ansStart = nArr[start];
                ansEnd = nArr[end];
                minSum = temp;
            }

            if (temp > 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(ansStart + " " + ansEnd);
    }
}
