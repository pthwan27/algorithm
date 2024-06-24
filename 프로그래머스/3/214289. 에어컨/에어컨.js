function solution(temperature, t1, t2, a, b, onboard) {
    temperature += 10;
    t1 += 10;
    t2 += 10;
    
    const dp = Array.from({length : onboard.length + 1}, () => new Array(52).fill(Infinity));
    dp[0][temperature] = 0;
    
    
    if(temperature >= t1 && temperature <= t2) return 0;
    
    for(let time = 1; time < dp.length; time++){
        let start = 0; 
        let end = 0;
        
        if(onboard[time - 1]){
            start = t1;
            end = t2;
        }else{
            start = Math.min(t1, temperature);
            end = Math.max(t2, temperature);
        }
        
        for(let temp = start; temp <= end; temp++){
            if(temp < temperature) {
                if(temp - 1 >= 0){
                    dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1]);
                }
                
                dp[time][temp] = Math.min(dp[time][temp], Math.min(dp[time - 1][temp] + b, dp[time - 1][temp + 1] + a)); 
            }else if(temp > temperature){
                if(temp - 1 >= 0){
                    dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1] + a);
                }
                
                dp[time][temp] = Math.min(dp[time][temp],Math.min( dp[time - 1][temp] + b), dp[time - 1][temp + 1]); 
            }else {
                if(temp - 1 >= 0){
                    dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1]);
                }
                
                dp[time][temp] = Math.min(dp[time][temp], Math.min(dp[time - 1][temp], dp[time - 1][temp + 1]));
            }
        }
    }
    return (Math.min(...dp[dp.length - 1]));
}