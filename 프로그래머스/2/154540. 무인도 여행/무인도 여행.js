const dr = [-1,0,1,0];
const dc = [0,-1,0,1];

function solution(maps) {
    let answer = [];
    const R = maps.length;
    const C = maps[0].length;
    
    const newMap = Array.from({length : R}, () => new Array(C));
    
    maps.forEach((line,r) => {
        line.split('').forEach((e,c) => {
            newMap[r][c] = e;
        })
    })
    
    for(let r = 0; r < R; r++) {
        for(let c = 0; c < C; c++){
            if(newMap[r][c] !== 'X'){
                answer.push(bfs(r,c));
            }
        }
    }
    
    
    return answer.length === 0 ? [-1] : answer.sort((a,b) => a - b);
    
    function bfs(r, c) {
        let bfsQ = [];
        let cnt = newMap[r][c] * 1;
        newMap[r][c] = 'X';
        
        bfsQ.push([r,c]);
        
        while(bfsQ.length){
            let [r,c] = bfsQ.pop();
            
            for(let i = 0; i < 4; i++){
                let nextR = r + dr[i];
                let nextC = c + dc[i];
                
                if(isIn(nextR, nextC) && newMap[nextR][nextC] !== 'X'){
                    cnt += newMap[nextR][nextC] * 1;
                    
                    newMap[nextR][nextC] = 'X'

                    bfsQ.push([nextR,nextC]);
                }
            }
        }
        
        return cnt;
    }
    
    function isIn(r,c) {
        return r >= 0 && r < R && c >= 0 && c < C; 
    }
}