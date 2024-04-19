function solution(players, callings) {
    const playerIdx = {};
    players.forEach((player,idx) => {
        playerIdx[player] = idx;
    })
    
    callings.forEach(call => {
        const callIdx = playerIdx[call];
        
        if(callIdx > 0){
            [players[callIdx], players[callIdx-1]] = [players[callIdx - 1], players[callIdx]];
            
            playerIdx[players[callIdx]]++;
            playerIdx[players[callIdx-1]]--;
        }
    });
    return players;
}