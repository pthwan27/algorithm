class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long[] dp1 = new long[sequence.length];
        long[] dp2 = new long[sequence.length];
        
        int a = 1;
        int b = -1;
        
        dp1[0] = sequence[0] * a;
        dp2[0] = sequence[0] * b;
        
        answer = Math.max(dp1[0], dp2[0]);
        
        for(int i = 1; i < sequence.length; i++){
            a *= -1;
            b *= -1;
            
            int next1 = sequence[i] * a;
            int next2 = sequence[i] * b;
            
            
            dp1[i] = Math.max(dp1[i-1] + next1, next1);
            dp2[i] = Math.max(dp2[i-1] + next2, next2);
            
            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
        
        return answer;
    }
}