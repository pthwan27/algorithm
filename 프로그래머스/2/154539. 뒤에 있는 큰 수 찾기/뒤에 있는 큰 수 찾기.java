import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Stack<int[]> numStack = new Stack<>();
        
        numStack.push(new int[]{0, numbers[0]});
        
        for(int i = 1; i < numbers.length; i++){
            if(numStack.peek()[1] >= numbers[i]){
                numStack.push(new int[]{i, numbers[i]});                
            }else{
                while(!numStack.empty() && numStack.peek()[1] < numbers[i]){
                    answer[numStack.pop()[0]] = numbers[i];
                }
                numStack.push(new int[]{i, numbers[i]});   
            }
        }
        
        return answer;
    }
}