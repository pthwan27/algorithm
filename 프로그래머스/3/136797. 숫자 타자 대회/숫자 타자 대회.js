function solution(numbers) {
    let answer = 0;
    
    let pointArr = new Array(10);
    
    pointArr[0] = [3,1];
    let cnt = 1;
    for(let r = 0; r < 3; r++){
        for(let c = 0; c < 3; c++){
            pointArr[cnt++] = [r,c];
        }
    }
    
    let distanceArr = Array.from({length : 10}, () => new Array(10));
    for(let r = 0; r < 10; r++){
        for(let c = 0; c < 10; c++){
            if(r === c) {
                distanceArr[r][c] = 1;
                continue;
            }
            let start = pointArr[r];
            let end = pointArr[c];
            
            let diff = [Math.abs(start[0] - end[0]), Math.abs(start[1] - end[1])];
            let min = Math.min(diff[0], diff[1]);
            
            let dist = min * 3 + (diff[0] - min) * 2 + (diff[1] - min) * 2; 
            
            distanceArr[r][c] = dist;
        }
    }
    
    let dp = Array.from({length : numbers.length + 1}, () => Array.from({length : 10}, () => new Array(10).fill(Infinity)));
    
    dp[0][4][6] = 0;
    for(let i = 0; i < numbers.length; i++){
        let num = numbers[i];
        
        let prevDP = dp[i];
        let nextDP = dp[i + 1];
        
        for(let r = 0; r < 10; r++){
            for(let c = 0; c < 10; c++){
                let prevValue = prevDP[r][c];
                if(prevValue === Infinity || r === c) continue;
            
                if(nextDP[num][c] > prevValue + distanceArr[r][num]){
                    nextDP[num][c] = prevValue + distanceArr[r][num];
                }
                
                if(nextDP[r][num] > prevValue + distanceArr[c][num]){
                    nextDP[r][num] = prevValue + distanceArr[c][num];
                }
            }
        }
    }
    return Math.min(...dp[numbers.length].flat());
}