function solution(storey) {
    let answer = Number.MAX_VALUE;
    
    const dfs = (idx, cnt, num) => {
        if(cnt > answer) return;
        
        if(Math.pow(10,idx) > num){
            let last = num / Math.pow(10,idx - 1);
            
            cnt += last > 5 ? 10 - last + 1 : last;
            answer = Math.min(cnt, answer);
            return;
        }
        
        let plusValue = 0;
        let plusCnt = 0;
        let minusValue = 0;
        let minusCnt = 0;
        
        let remain = num % Math.pow(10, idx);
        let curDigit = Math.pow(10, idx - 1);
        
        if(idx === 1) {
            plusCnt = plusValue = 10 - remain;
            minusCnt = minusValue = remain;
        }else{
            plusValue = (10 - (remain / curDigit)) * curDigit;
            plusCnt = plusValue / curDigit;
            
            minusValue = (remain / curDigit) * curDigit;
            minusCnt = minusValue / curDigit;
        }

        dfs(idx + 1, cnt + plusCnt, num + plusValue);
        dfs(idx + 1, cnt + minusCnt, num - minusValue);
    }
    dfs(1, 0, storey);
    
    return answer;
}