function solution(e, starts) {
    let count = new Array(e+1).fill(0);
    
    for(let i = 1; i <= e; i++){
        for(let j = i; j <= e; j += i){
            count[j]++;
        }
    }
    let maxArr = new Array(e+1).fill(e);
    let max = count[e];
    for(let i = e, mIdx = e; i > 0; i--){
        if(count[i] >= max) {
            mIdx = i;
            max = count[i];
        }        
        maxArr[i] = mIdx;
    }
    
    return starts.map(e => maxArr[e]);
}