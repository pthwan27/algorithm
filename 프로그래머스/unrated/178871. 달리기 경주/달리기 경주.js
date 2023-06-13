function solution(players, callings) {
    const idxs = {};
    
    for(let i = 0; i < players.length; i++){
        idxs[players[i]] = i;
    }
    
    callings.forEach((callingPlayer) => {
        const callingPlayerIdx = idxs[callingPlayer];
        
        const prevPlayer = players[callingPlayerIdx - 1];
        
        players[callingPlayerIdx - 1] = callingPlayer;
        players[callingPlayerIdx] = prevPlayer;
        
        idxs[prevPlayer] = callingPlayerIdx;
        idxs[callingPlayer] = callingPlayerIdx - 1;
    })
    
    return players;
}