let answer = Number.MAX_VALUE;
let mLen = 0;
function solution(picks, minerals) {
    const dia = picks[0];
    const iron = picks[1];
    const stone = picks[2];
    const pickCnt = picks.reduce((acc, e) => {
        return acc + e;
    }, 0)
    mLen = minerals.length;
    
    dfs(0, 0, dia, iron, stone, pickCnt, minerals);
    
    return answer;
}
const dfs = (cur, fatigue, d, i, s, pickCnt, minerals) => {
    if(pickCnt <= 0 || mLen <= cur){
        answer = Math.min(answer, fatigue);
        return;
    }
    
    if(d > 0){
        const temp = calc(cur, fatigue, 'D', minerals);
        dfs(temp[0], temp[1], d - 1, i, s, pickCnt - 1, minerals);
    }
    
    if(i > 0){
        const temp = calc(cur, fatigue, 'I', minerals);  
        dfs(temp[0], temp[1], d, i - 1, s, pickCnt - 1, minerals);
    }
    
    if(s > 0){
        const temp = calc(cur, fatigue, 'S', minerals);  
        dfs(temp[0], temp[1], d, i, s - 1, pickCnt - 1, minerals);
    }
}
const calc = (s, f, type, minerals) => {
    let e = s + 5 <= mLen ? s + 5 : mLen;
    
    for(s; s < e; s++){
        const m = minerals[s];
        
        if(type === 'D') f += 1;
        else if(type === 'I') {
            if(m === 'diamond') f += 5;
            else f+= 1
        }else {
            if(m === 'diamond')f += 25;
            else if(m === 'iron')f += 5;
            else f += 1;
        }
    }
    return [e, f];
}