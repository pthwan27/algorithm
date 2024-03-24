class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int start = 0, end = 0;
        
        int sum = sequence[0];
        
        int minLength = Integer.MAX_VALUE;
        while(end < sequence.length && start <= end){            
            while(sum < k && end < sequence.length - 1){
                sum += sequence[++end];
            }
            
            // System.out.println("Start : " + start + " " + "End : " + end + " " + "Sum : " + sum);
            
            
            if(sum == k){
                if(minLength > end - start){
                    minLength = end - start;
                    answer[0] = start;
                    answer[1] = end;
                }
            }
            sum -= sequence[start++];
            
            // System.out.println("Start : " + start + " " + "End : " + end + " " + "Sum : " + sum);
        }
        
        
        return answer;
    }
}