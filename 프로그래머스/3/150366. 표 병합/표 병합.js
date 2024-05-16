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
                if(rest.length > 2) {
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
        if(v1 === 'EMPTY') {
            v1 = v2;
        }
        
        if(g1 === 0 && g2 === 0) {
            valueMap[r1][c1] = v1, valueMap[r2][c2] = v1;
            groupNumMap[r1][c1] = gn, groupNumMap[r2][c2] = gn;
            
            group.set(gn, v1);
            return true;
        }
        
        if(g1 !== 0 && g2 !== 0){
            group.delete(g2);
            
            for(let r = 1; r < 51; r++){
                for(let c = 1; c < 51; c++){
                    if(groupNumMap[r][c] = g2) {
                        groupNumMap[r][c] = g1;
                        
                        valueMap[r][c] = v1;
                    } 
                }    
            }
        }
        if(g1 === 0) {
            valueMap[r1][c1] = v2;
            groupNumMap[r1][c1] = g2;
        }
        
        if(g2 === 0) {
            valueMap[r2][c2] = v1;
            groupNumMap[r2][c2] = g1;
        }
        
        return false;
    }
    
    function unmerge(r, c){
        let value = valueMap[r][c];
        let groupNum = groupNumMap[r][c];
        
        for(let ri = 1; ri < 51; ri++){
            for(let cj = 1; cj < 51; cj++){
                if(groupNumMap[ri][cj] === groupNum) {
                    groupNumMap[ri][cj] = 0;

                    valueMap[ri][cj] = 'EMPTY';
                } 
            }    
        }
        valueMap[r][c] = value;
    }
    
    function print(r, c){
        answer.push(valueMap[r][c]);
    }   
   
    return answer;
}