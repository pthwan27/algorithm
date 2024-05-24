function solution(sequence, k) {
    let answer = new Array(2);
    answer[0] = 0; 
    answer[1] = 1000000;
    
    let s = 0;
    let e = 0;
    
    let sum = sequence[0];
    
    while(s <= e && e < sequence.length) {
        if(sum === k && answer[1] - answer[0] > e - s) {
            answer = [s,e];
        }
        
        if(sum > k) {
            sum -= sequence[s];
            s++;
        }else{
            e++;
            sum += sequence[e];
        }
    }
    
    return answer;
}