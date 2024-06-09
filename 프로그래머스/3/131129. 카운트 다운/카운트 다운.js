function solution(target) {
    let answer = [];
    
    let checkedArr = new Array(71).fill().map((e,idx) => {
        if(idx >= 0 && idx < 21) return [1,1];
        else if((idx % 2 === 0 && idx < 41) || (idx % 3 === 0 && idx < 61)) return [1,0];
        else if(idx >= 20 && idx < 40) return [2,2];
        else if(idx >= 50 && idx < 71) return [2,2];
        else return [2,1];
    });
    checkedArr[50] = [1,1];
    checkedArr[60] = [1,0];
    
    let targetArr = new Array(100001).fill();
    
    for(let i = 1; i < 100001; i++){
        if(i < 71) {
            targetArr[i] = checkedArr[i];
            continue;
        }
        
        let next = [targetArr[i - 50][0] + targetArr[50][0], targetArr[i - 50][1] + targetArr[50][1]];
        let B = [targetArr[i - 60][0] + targetArr[60][0], targetArr[i - 60][1] + targetArr[60][1]];
        let C = [targetArr[i - 70][0] + targetArr[70][0], targetArr[i - 70][1] + targetArr[70][1]];
        
        if(next[0] > B[0]) next = B;
        
        if(next[0] === B[0] && next[1] < B[1]) next = B;
        
        if(next[0] > C[0]) next = C;
        
        if(next[0] === C[0] && next[1] < C[1]) next = C;
        
        targetArr[i] = next;
    }
    
    
    return targetArr[target];
}