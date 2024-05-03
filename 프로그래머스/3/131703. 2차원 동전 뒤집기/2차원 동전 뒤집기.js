function solution(beginning, target) {
    let answer = 21;
    
    const N = beginning.length;
    const M = beginning[0].length;
    
    let k = 1 << (N + M);
    
    const flip = (isRC, r, c, map) => {
        if (isRC) {
            for(let tc = 0; tc < M; tc++) map[r][tc] ^= 1;
        } else {
            for(let tr = 0; tr < N; tr++) map[tr][c] ^= 1;
        }
    }
    
    const isOK = (copy) => {
        if(JSON.stringify(target) !== JSON.stringify(copy)) return false;
        return true;
    }
    
    for(let bi = 0; bi < k; bi++){
        let copy = JSON.parse(JSON.stringify(beginning));
        
        let cnt = 0;
        
        for(let r = 0; r < N; r++){
            let br = 1 << r;
            
            if((br & bi) != 0) {
                cnt++;
                flip(true, r, 0, copy);
            }
        }
        
        for(let c = 0; c < M; c++){
            let bc = 1 << (c + N);
            
            if((bc & bi) != 0) {
                cnt++;
                flip(false, 0, c, copy);
            }
        }
        
        if(isOK(copy)) {
            answer = Math.min(answer, cnt)
        };
    }
    if (answer === 21) {
        answer = -1;
    }
    return answer;
}