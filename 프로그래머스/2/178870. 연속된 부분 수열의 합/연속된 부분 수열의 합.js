function solution(sequence, k) {
    let answer = [];
    
    let s = 0;
    let e = 0;
    let sum = sequence[0];
    let sumMinLen = sequence.length;
    
    while(s < sequence.length && e < sequence.length && s <= e){
        if(sum == k && sumMinLen > e-s){
            answer = [s,e];
            sumMinLen = e-s;
        }
        
        if(sum <= k) {
            e++;
            sum += sequence[e];
        }        
        
        if(sum > k){
            sum -= sequence[s];
            s++;
        }        
        // console.log(`${sum} / ${s}, ${e} // ${answer}` );
    }
    return answer;
}