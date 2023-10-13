import java.io.*;
import java.util.*; 

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
   
        double doubleR1 = Math.pow(r1, 2);
        double doubleR2 = Math.pow(r2, 2);
        
        double bigCircle = 0;
        double smallCircle = 0;
        
        for(int r = 1; r < r2; r++){
            answer += Math.floor(Math.sqrt(doubleR2 - Math.pow(r,2)));
        }
        
        for(int r = 1; r < r1; r++){
            double max = Math.sqrt(doubleR1 - Math.pow(r,2));
            if(max - (int)max == 0.0){
                answer -= Math.floor(max) -1;
            }else{
                answer -= Math.floor(max);
            }
        }
        
        return answer * 4 + (r2-r1+1) * 4;
    }
}