function solution(dice) {
    let answer = [];
    let maxWinRate = -1;
    
    const N = dice.length;
    
    let combArr = makeComb(N);
    
    combArr.forEach(e => {
        let A = [];
        let B = [];
        
        for(let i = 0; i < N; i++){
           if(e[i]) A.push(dice[i]);
           else B.push(dice[i]);
        }
        
        let winRate = calcWinRate(A,B);
        
        if(maxWinRate < winRate) {
            answer = e;
            maxWinRate = winRate;
        }
    });
    return answer.reduce((acc, e, idx) => {
        if(e) acc.push(idx + 1);
        return acc;
    }, []);
}

function makeComb(diceLen) {
    let result = [];
    let size = diceLen / 2;
    let selectedDice = Array(diceLen).fill(false);

    function comb(idx, cnt) {
        if(cnt === size) {
            result.push([...selectedDice]);

            return;
        }

        for(let i = idx; i < diceLen; i++){
            selectedDice[i] = true;
            comb(i + 1, cnt + 1);
            selectedDice[i] = false;
        }
    }

    comb(0, 0);

    return result;
}

function calcWinRate(A, B) {    
    function calcDicesScore(cnt, score, dices) {
        const diceCnt = A.length;
        let result = [];
        
        function calc(cnt, score, dices) {
            if(cnt === diceCnt) {
                result.push(score);
                return;
            }

            for(let i = 0; i < 6; i++){
                calc(cnt + 1, score + dices[cnt][i], dices);
            }        
        }
        
        calc(cnt, score, dices);
        
        return result;
    }
    
    let AScoreList = calcDicesScore(0, 0, A);
    let BScoreList = calcDicesScore(0, 0, B).sort((a,b) => a - b);
    
    let scoreListLen = AScoreList.length;
    let winCnt = 0;
    
    AScoreList.forEach(score => {
        let start = 0;
        let end = scoreListLen - 1;
        
        if(score > BScoreList[end]) {
            start = scoreListLen;
            end = scoreListLen;
        }
        
        while(start < end) {
            let mid = Math.floor((start + end) / 2);

            if(score <= BScoreList[mid]) {
                end = mid;
            }else {
                start = mid + 1;
            }
        }
        
        winCnt += end;
    });
    
    return winCnt;
}