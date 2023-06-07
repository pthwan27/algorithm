import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int [] arr = new int [N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }
        int size=0;
        int [] ans = new int [N];
        for(int i=0;i<N;i++) {
            int target=Arrays.binarySearch(ans, 0, size, arr[i]);
            if(target>-1) {
                continue;
            }
            target = (target*-1)-1;
            ans[target]=arr[i];
            if(target==size) {
                size++;
            }
        }
        System.out.println(size);
    }
}
