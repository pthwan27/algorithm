function solution(board) {
    let answer = -1;
    
    let oCnt = 0;
    let xCnt = 0;
    
    let oWinCnt = 0;
    let xWinCnt = 0;
    
    for(let r = 0; r < 3; r++){
        for(let c = 0; c < 3; c++){
            if(board[r][c] === '.') continue;
            else if(board[r][c] === 'O') oCnt++
            else xCnt++;
            
            if(r === 0) {
                if(rWinChk(board,c)) {
                    if(board[r][c] === 'O') oWinCnt++;
                    else xWinCnt++;
                } 
            } 
            
            if(c === 0) {
                if(cWinChk(board,r)) {
                    if(board[r][c] === 'O') oWinCnt++;
                    else xWinCnt++;
                } 
            } 
            
            if(r === 0 && c === 0){
                if(crossWinChk(board)) {
                    if(board[r][c] === 'O') oWinCnt++;
                    else xWinCnt++;
                } 
            } 
            
            if(r === 0 && c === 2){
                if(reverseCrossWinChk(board)) {
                    if(board[r][c] === 'O') oWinCnt++;
                    else xWinCnt++;
                } 
            }
        }
    }
    
    console.log(oCnt, xCnt, oWinCnt, xWinCnt);
    if(oCnt < xCnt) return 0;
    
    if(oCnt - xCnt > 1) return 0;
    
    if(oWinCnt > 0 && xWinCnt !== 0) return 0;
    
    if(oWinCnt > 0 && xCnt + 1 !== oCnt) return 0;
    
    if(xWinCnt > 0 && oWinCnt !== 0) return 0;
    
    if(xWinCnt > 0 && oCnt !== xCnt) return 0;
    
    if(oWinCnt === 0 && xWinCnt === 0 && xCnt > oCnt) return 0;
   
    return 1;
}
function rWinChk(map,c){
    return map[0][c] === map[1][c] && map[1][c] === map[2][c]; 
}

function cWinChk(map,r){
    return map[r][0] === map[r][1] && map[r][1] === map[r][2]; 
}

function crossWinChk(map){
    return map[0][0] === map[1][1] && map[1][1] === map[2][2]; 
}

function reverseCrossWinChk(map){
    return map[0][2] === map[1][1] && map[1][1] === map[2][0]; 
}
