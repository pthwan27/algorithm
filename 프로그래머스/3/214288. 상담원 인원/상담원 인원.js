function solution(k, n, reqs) {
    let answer = 0;
    
    const comb = (remain, curIdx, curArr, calcTime) => {
        if(curIdx === k) {
            curArr[curIdx] = remain + 1;
            
            calcTime(curArr);
            return;
        }
        
        for(let i = 0; i <= remain; i++){
            curArr[curIdx] = i + 1;
            comb(remain - i, curIdx + 1, curArr, calcTime);
        }
    }
    const calcTime = (mentos) => {
        console.log(mentos);
    }
    
    comb(n-k, 1, new Array(k).fill(0), calcTime);
    
    return answer;
}