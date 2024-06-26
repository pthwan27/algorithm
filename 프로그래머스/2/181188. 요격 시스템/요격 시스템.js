function solution(targets) {
    let answer = 1;
    
    targets.sort((a,b) => {
        return a[1] - b[1];
    });
    
    let lastMissile = targets[0][1];
    
    for(let i = 1; i < targets.length; i++){
        let [s,e] = targets[i];
        if(s < lastMissile) continue;
        else {
            answer++;
            lastMissile = e;
        }
    }
    return answer;
}