function solution(commands) {
    let answer = [];
    
    const valueMap = Array.from(new Array(51), () => new Array(51).fill('EMPTY'));
    const groupNumMap = Array.from(new Array(51), () => new Array(51).fill(0));
    
    const group = new Map();
    
    let gn = 1;
    
    commands.forEach(e => {
        let [command, ...rest] = e.split(" ");
        switch (command) {
            case 'UPDATE' : {
                if(rest.length === 3) {
                    let [r,c,v] = rest;
                    updateOne(r,c,v);
                }else{
                    let [v1, v2] = rest;
                    updateTwo(v1, v2);
                }
            } break;
            case 'MERGE' : {
                let [r1, c1, r2, c2] = rest;
                if(merge(r1, c1, r2, c2, gn)) gn++;
            } break;
            case 'UNMERGE' : {
                let [r,c] = rest;
                unmerge(r, c);
            } break;
            case 'PRINT' :{
                let [r,c] = rest;
                print(r,c);
            } break;
                
            default : break;
        }
    });
    
    
    function updateOne(r, c, v){
        let groupNum = groupNumMap[r][c];
        if(groupNum === 0) valueMap[r][c] = v;        
        else{
            group.set(groupNum, v);
            for(let ri = 1; ri < 51; ri++){
                for(let cj = 1; cj < 51; cj++){
                    if(groupNumMap[ri][cj] === groupNum) valueMap[ri][cj] = v;
                }    
            } 
        }
    }
    function updateTwo(v1, v2){
        for(let r = 1; r < 51; r++){
            for(let c = 1; c < 51; c++){
                if(valueMap[r][c] === v1) {
                    let groupNum = groupNumMap[r][c];
                    group.set(groupNum, v2);
                    valueMap[r][c] = v2;
                } 
            }    
        }
    }
    
    function merge(r1, c1, r2, c2, gn){
        let [v1, v2] = [valueMap[r1][c1], valueMap[r2][c2]];
        let [g1, g2] = [groupNumMap[r1][c1], groupNumMap[r2][c2]];
        
        let value = v1;
        
        if(v1 === 'EMPTY' && v2 !== 'EMPTY') {
            value = v2;
        }
        if (g1 !== 0 && g2 !== 0 && g1 === g2) {
            return false;
        }
        
        [valueMap[r1][c1], groupNumMap[r1][c1]] = [value, gn];
        [valueMap[r2][c2], groupNumMap[r2][c2]] = [value, gn];
       

        for(let r = 1; r < 51; r++){
            for(let c = 1; c < 51; c++){
                if((g1 !== 0 && groupNumMap[r][c] === g1) || (g2 !== 0 && groupNumMap[r][c] === g2)) {
                    groupNumMap[r][c] = gn;
                    valueMap[r][c] = value;
                } 
            }    
        }
        group.set(gn, value);
        
        
        return true;
    }
    
    function unmerge(r, c){
        let value = valueMap[r][c];
        let groupNum = groupNumMap[r][c];
        
        if(groupNum === 0) return;
        
        for(let ri = 1; ri < 51; ri++){
            for(let cj = 1; cj < 51; cj++){
                if(groupNumMap[ri][cj] === groupNum) {
                    [groupNumMap[ri][cj],valueMap[ri][cj]] = [0,'EMPTY'];
                } 
            }    
        }
        valueMap[r][c] = value;
        groupNumMap[r][c] = groupNum;
    }
    
    function print(r, c){
        answer.push(valueMap[r][c]);
    }   
   
    return answer;
}