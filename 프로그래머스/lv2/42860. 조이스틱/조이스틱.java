import java.io.*;
import java.util.*;

class Solution {
    public int solution(String name) {   
        // 중요한건 어떻게 이동할지 -> 조이스틱 상하가 아닌 좌우가 중요
        
        // 어떻게해야 최소로 뽑지??
        // 아래와 같은 경우 
        // JAN -> J -> N 한번만이동했는뎅..            
        // JAAAAABAAN 이렇게 오면?  어떻게?!!   
        // AAB는 ?
        
        // 이동할 수 있는 최소값 -> name의 length -1  또는 연속되는 A를 제외한 값?
        // J-----BAAN
        // 가장 긴 A를 빼놓고 못가게 처리한다음에 계산해보자
        int answer = 0;
        
        int name_len = name.length();
        int move = name_len-1;
        
        for(int i = 0; i < name.length(); i++){
            if(name.charAt(i) < 'N'){
                answer += name.charAt(i) - 'A';               
            }else{
                answer += 'Z'-name.charAt(i) + 1;
            }
            
            int conA= i+1;
            while(conA < name_len && name.charAt(conA) =='A'){
                conA++;
            }
            move= Math.min(move, i+(name_len-conA) + Math.min(i, name_len-conA));
        }
        
        return answer+move;
    }
}

