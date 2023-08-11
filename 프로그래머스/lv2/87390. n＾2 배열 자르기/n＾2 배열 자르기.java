import java.util.*;
import java.io.*;

// left ~ right 를 n으로 나누고 몫 + 1, 나머지 + 1은 2차원배열에서의 r , c
// 몫 + 1 = r , 나머지 + 1 = c

class Solution {
    public ArrayList<Long> solution(int n, long left, long right) {
        ArrayList<Long> answerList = new ArrayList<>();
        
        for(long i = left; i <= right; i++){
            long r = i / n + 1;
            long c = i % n + 1;
            
            answerList.add(Math.max(r,c));
        }
        
        return answerList;
    }
}