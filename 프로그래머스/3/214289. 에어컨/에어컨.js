function solution(temperature, t1, t2, a, b, onboard) {
    temperature += 10;
    t1 += 10;
    t2 += 10;
    
    let minT = t1 < temperature ? t1 : temperature; 
    let maxT = t2 > temperature ? t2 : temperature; 
    
    let dp = Array.from({length : onboard.length + 1}, () => new Array(52).fill(Infinity));
    dp[0][temperature] = 0;
    
    for(let i = 1; i < dp.length; i++){
        let start = 0;
        let end = 50;
        
        if(onboard[i-1]) {
            start = t1;
            end = t2;
        }else{
            start = minT;
            end = maxT;
        }
        
        for(let j = start; j <= end; j++){
            if(temperature < j){
                if(j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1] + a, dp[i][j]);
                }
                dp[i][j] = Math.min(dp[i-1][j+1], Math.min(dp[i][j], dp[i-1][j] + b));

            }else if(temperature > j) {
                if(j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j]);
                }
                dp[i][j] = Math.min(dp[i-1][j+1] + a, Math.min(dp[i][j], dp[i-1][j] + b));

            }else {
                if(j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j]);
                }
                dp[i][j] = Math.min(dp[i-1][j+1], Math.min(dp[i][j], dp[i-1][j]));
                
            }
        }
    }
    
    return Math.min(...dp[dp.length - 1]);
}