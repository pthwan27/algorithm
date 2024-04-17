function solution(n, tops) {
    const mod = 10007;
    
    let dp = new Array({length : n+1}).fill(0);
    let dp3 = new Array({length : n + 1}).fill(0);
    
    dp[1] = tops[0] ? 3 : 2;
    dp3[1] = 1;
    
    for(let i = 2; i < n + 1; i++){
        if(tops[i - 1]){
            dp[i] = (dp[i - 1] * 3 + dp3[i - 1] * 2) % mod;
        }else{
            dp[i] = (dp[i - 1] * 2 + dp3[i - 1]) % mod;
        }
        dp3[i] = (dp[i - 1] % mod) + (dp3[i - 1] % mod);
    }
    return (dp[n] + dp3[n]) % mod;
}