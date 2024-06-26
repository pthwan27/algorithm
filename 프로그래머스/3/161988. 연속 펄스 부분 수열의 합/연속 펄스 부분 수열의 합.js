function solution(sequence) {
    let answer = 0;
    
    let dpPM = new Array(sequence.length).fill(-Infinity);
    let dpMP = new Array(sequence.length).fill(-Infinity);
    
    let PM = +1;
    let MP = -1;
    
    dpPM[0] = sequence[0] * PM;
    dpMP[0] = sequence[0] * MP;
    
    for(let i = 0; i < sequence.length; i++){
        if(i > 0) {
            dpPM[i] = Math.max(dpPM[i-1] + sequence[i] * PM, sequence[i] * PM);
            dpMP[i] = Math.max(dpMP[i-1] + sequence[i] * MP, sequence[i] * MP);
        }        
        
        PM *= -1;
        MP *= -1;
        
        answer = Math.max(answer, Math.max(dpPM[i], dpMP[i]));
    }
    
    return answer;
}