function solution(cap, n, deliveries, pickups) {
    let answer = 0;
    
    const dQ = [];
    const pQ = [];
    
    for(let i = 0; i < n; i++){
        if(deliveries[i]) dQ.push([i + 1, deliveries[i]])
        if(pickups[i]) pQ.push([i + 1,pickups[i]])
    }
    
    let dLen = calcLen(dQ);
    let pLen = calcLen(pQ);
    
    let maxLen = Math.max(dLen, pLen);
    
    while(maxLen) {
        let capCnt = cap;
        let maxIdx = -1;
        
        while(capCnt && dLen){
            let [idx, boxCnt] = dQ.pop();
            maxIdx = Math.max(maxIdx, idx);
            if(capCnt < boxCnt) {
                boxCnt -= capCnt;
                capCnt = 0;
            } else {
                capCnt -= boxCnt;
                boxCnt = 0;
            }
            
            if(boxCnt) {
                dQ.push([idx, boxCnt]);
                break;
            };
            dLen = calcLen(dQ);
        }
        
        capCnt = cap;
        
        while(capCnt && pLen){
            let [idx, boxCnt] = pQ.pop();
            maxIdx = Math.max(maxIdx, idx);
            if(capCnt < boxCnt) {
                boxCnt -= capCnt;
                capCnt = 0;
            } else {
                capCnt -= boxCnt;
                boxCnt = 0;
            }
            
            if(boxCnt) {
                pQ.push([idx,boxCnt]);
                break;
            };
            
            pLen = calcLen(pQ);
        }
        
        maxLen = Math.max(dLen, pLen);
        
        answer += maxIdx * 2;
    }
    
    return answer;
}

function calcLen(arr) {
    return !!arr[arr.length - 1] ? arr[arr.length - 1][0] : 0;
} 