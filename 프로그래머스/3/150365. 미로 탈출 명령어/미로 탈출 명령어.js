let minStr = '';

const dr = [1, 0,0,-1];
const dc = [0,-1,1, 0];
const alpha = ['d', 'l', 'r', 'u'];

function solution(n, m, x, y, r, c, k) {
    let answer = 'impossible';
    let map = Array.from({length : n + 1}, () => new Array(m + 1).fill('.'));
    map[x][y] = 'S';
    map[r][c] = 'E';
    
    const start = [x,y];
    const end = [r,c];
    
    if(k % 2 !== (Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1])) % 2 ) return answer;
    else dfs(start, 0, '');

    return answer;
    

    function dfs(cur, cnt, result) {
        if(answer !== 'impossible') return;
        if(k - cnt < (Math.abs(cur[0] - end[0]) + Math.abs(cur[1] - end[1]))) return;

        if(cnt > k) return;

        if(cnt === k && map[cur[0]][cur[1]] === 'E') {
            answer = result;
            return;
        }

        for(let i = 0; i < 4; i++){
            let nextR = cur[0] + dr[i];
            let nextC = cur[1] + dc[i];

            let curAlpha = alpha[i];

            if(isIn(nextR, nextC, n, m)){
                dfs([nextR,nextC], cnt+1, result + curAlpha);
            }
        }
    }
}

function isIn(r, c, n, m){
    return r >= 1 && r <= n && c >= 1 && c <= m;
}