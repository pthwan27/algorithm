import java.util.*;

class Solution {
    static int answer; 
    static int[] pickArr;
    static int pickCnt;
    static String[] mineralArr;
    
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        
        pickArr = picks;
        pickCnt = 0;
        for(int i = 0; i < 3; i++){
            pickCnt += picks[i];
        }
        mineralArr = minerals;
        
        dfs(0, 0);
        return answer;
    }
    
    public static void dfs(int count, int point){
        // System.out.println(pickCnt + " " + count + " " + point);
        if(pickCnt == count || count * 5 > mineralArr.length){
            answer = Math.min(answer, point);
            return;
        }
        
        if(point > answer){
            return;
        }
        for(int i = 0; i < 3; i++){
            if(pickArr[i] > 0){
                pickArr[i]--;
                int getPoint = countingPoint(count, i);
                point += getPoint;
                dfs(count+1, point);
                point -= getPoint;
                pickArr[i]++;
            }
        }
    }
    public static int countingPoint(int cnt, int kind){
        int point = 0;
        for(int i = cnt*5; i < mineralArr.length && i < (cnt+1) * 5; i++){
            switch (kind) {
                case 0: 
                    point +=1;
                    break;
                case 1: 
                    point += mineralArr[i].equals("diamond") ? 5 : 1;
                    break;
                case 2:
                    point += mineralArr[i].equals("diamond") ? 25 : (mineralArr[i].equals("iron") ? 5 : 1);
                    break;
            }
        }
        
        return point;
    }
}