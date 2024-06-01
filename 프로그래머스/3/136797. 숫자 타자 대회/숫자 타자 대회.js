function solution(numbers) {
    let numberMap = new Map();
    let num = 1;
    for(let i = 0; i < 3; i++){
        for(let j = 0; j < 3; j++){
            numberMap.set(num++, [i, j]);
        }
    }
    
    const distance = Array.from({length:10}, () => new Array(10).fill());
    distance[0] = [1,7,6,7,5,4,5,3,2,3];    
    for(let start = 1; start < 10; start++){
        for(let end = 0; end < 10; end++){
            if(start === end){ 
                distance[start][end] = 1;
                continue;
            }
            if(end === 0) {
                distance[start][end] = distance[end][start];
                continue;
            } 
            let [sr, sc] = numberMap.get(start);
            let [er, ec] = numberMap.get(end);
            
            let dis = 0;
            
            let diff = [Math.abs(sr - er), Math.abs(sc - ec)];
            if(diff[0] > 0 && diff[1] > 0) {
                let min = Math.min(diff[0], diff[1]);
                dis += min * 3;
                diff = [diff[0] - min, diff[1] - min];
            }
            
            dis += diff[0] * 2 + diff[1] * 2;
            
            distance[start][end] = dis;
        }
    }
    const dp = Array.from({length : numbers.length + 1}, () => Array.from({length:10}, () => new Array(10).fill(Infinity)));
    dp[0][4][6] = 0;
    
    for(let i = 0; i < numbers.length; i++) {
        let num = numbers[i];
        
        let prevDP = dp[i];
        let curDP = dp[i+1];
        
        for(let i = 0; i < 10; i++){
            for(let j = 0; j < 10; j++){
                let prevValue = prevDP[i][j];
                if(prevValue === Infinity || i === j) continue;
                
                if(curDP[num][j] > prevValue + distance[i][num]){
                    curDP[num][j] = prevValue + distance[i][num];
                }
                
                if(curDP[i][num] > prevValue + distance[num][j]) {
                    curDP[i][num] = prevValue + distance[num][j];
                }
            }
        }
    }
    
    return Math.min(...dp[numbers.length].flat());;
}
