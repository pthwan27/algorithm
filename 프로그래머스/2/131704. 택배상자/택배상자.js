function solution(order) {
    let answer = 0;
    
    let idx = 1;
    let orderIdx = 0;
    
    let subBelt = [];
    
    while(idx <= order.length){
        subBelt.push(idx);
        
        while(subBelt.length !== 0 && subBelt[subBelt.length-1] === order[orderIdx]){
            subBelt.pop();
            orderIdx++;
        }
        
        idx++;
    }
    
    return orderIdx;
}