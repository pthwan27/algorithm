function solution(e, starts) {
    let cntArr = new Array(e+1).fill(0);
    
    for(let i = 1; i <= e; i++){
        for(let j = i; j <= e; j+=i){
            cntArr[j]++;
        }
    }
    
    let maxCntArr = new Array(e+1).fill(e);
    let max = cntArr[e];
    
    for(let i = e - 1; i >= 1; i--){
        if(max <= cntArr[i]) {
            maxCntArr[i] = i;
            max = cntArr[i];
        }
        else maxCntArr[i] = maxCntArr[i+1];
    }

    return starts.map(e => maxCntArr[e]);
}