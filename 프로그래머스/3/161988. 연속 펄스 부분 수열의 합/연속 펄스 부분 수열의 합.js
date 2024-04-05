function solution(sequence) {
    let answer = 0;
    
    let minusDP = new Array(sequence.length).fill(0);
    let plusDP = new Array(sequence.length).fill(0);
    
    plusDP[0] = +sequence[0]; 
    minusDP[0] = -sequence[0];
    
    let plus = 1;
    let minus = -1;
    
    for(let i = 0; i < sequence.length; i++){
        if(i > 0){
            plusDP[i] = Math.max(sequence[i] * plus, plusDP[i - 1] + sequence[i] * plus);
            minusDP[i] = Math.max(sequence[i] * minus, minusDP[i - 1] + sequence[i] * minus);
        }        
        plus *= -1;
        minus *= -1;
        
        answer = Math.max(answer, plusDP[i], minusDP[i]);
    }
    
    return answer;
}