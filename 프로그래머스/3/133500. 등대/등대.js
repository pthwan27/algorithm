function solution(n, lighthouse) {
    let answer = 0;
    
    const checkArr = new Array(n + 1).fill(false);
    
    while(lighthouse.length) {
        const map = new Array(n + 1).fill().map(() => []);
        
        for(let [s,e] of lighthouse) {
            map[s].push(e);
            map[e].push(s);
        }
        
        map.filter(e => e.length === 1).forEach(([target]) => {
            if(!checkArr[target]){
                checkArr[target] = true;
                if(map[target].length !== 1) {
                    answer += 1;
                }else{
                    answer += 0.5;
                }
            }
        })
        
        lighthouse = lighthouse.filter(([s,e]) => {
            return !checkArr[s] && !checkArr[e];
        })
        
    }
    
    return answer;
}