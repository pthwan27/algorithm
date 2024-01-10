import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int idx = 0;
        int start = 0;
        
        while(idx < number.length() - k){
            int max = 0;
            for(int i = start; i <= idx + k; i++){
                int n = number.charAt(i) - '0';
                if(max < n){
                    start = i + 1;
                    max = n;
                }
            } 
            sb.append(max);
            idx++;
        }
        
        return sb.toString();
    }
}