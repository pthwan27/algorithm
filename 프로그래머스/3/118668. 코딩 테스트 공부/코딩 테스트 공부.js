function solution(alp, cop, problems) {
    let reqMaxAlp = 0;
    let reqMaxCop = 0;
    
    problems.forEach(([alp_req, cop_req, ...rest]) => {
        reqMaxAlp = Math.max(reqMaxAlp, alp_req);
        reqMaxCop = Math.max(reqMaxCop, cop_req);
    });
    
    const dp = Array.from({length : reqMaxAlp + 1}, () => new Array(reqMaxCop + 1).fill(Infinity));
    
    alp = Math.min(reqMaxAlp, alp);
    cop = Math.min(reqMaxCop, cop);
    dp[alp][cop] = 0;
    
    for(let a = alp; a <= reqMaxAlp; a++){ 
        for(let c = cop; c <= reqMaxCop; c++){
            if(a < reqMaxAlp) dp[a+1][c] = Math.min(dp[a][c] + 1, dp[a+1][c]);
            if(c < reqMaxAlp) dp[a][c+1] = Math.min(dp[a][c] + 1, dp[a][c+1]);
            
            problems.forEach(([alp_req, cop_req, alp_rwd, cop_rwd, cost]) => {
                if(a >= alp_req && c >= cop_req) {
                    let newAlp = a + alp_rwd < reqMaxAlp ? a+alp_rwd : reqMaxAlp;
                    let newCop = c + cop_rwd < reqMaxCop ? c+cop_rwd : reqMaxCop;
                    
                    dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[a][c] + cost); 
                }
            })
        }
    }
    
    
    return dp[reqMaxAlp][reqMaxCop];
}