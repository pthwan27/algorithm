

const mergeValue = new Map();
function solution(commands) {
    let answer = [];
    let map = Array.from({length: 51}, () => new Array(51));
    
    for(let r = 1; r <= 50; r++){
        for(let c = 1; c <= 50; c++){
            map[r][c] = [0, 'EMPTY'];
        }    
    }
    
    let groupNum = 1;
    commands.forEach(e => {
        let [command, ...rest] = e.split(' ');
        if(command === 'UPDATE'){
            if(rest.length === 3){
                updateOne(rest, map);
                
            }else{
                updateAll(rest, map);
            }
        }
        else if(command === 'MERGE'){
            groupNum = merge(rest, map, groupNum) ? groupNum + 1 : groupNum;            
        }
        else if(command === 'UNMERGE'){
            unmerge(rest, map);
        }
        else {
            const [r,c] = rest;
            answer.push(map[r][c][1]);
        }
    });
    
    return answer;
}

function updateOne(rest, map){
    const [r, c, v] = rest;
    const group = map[r][c][0];
    map[r][c][1] = v;
    
    if(group !== 0){
        for(let i = 1; i < 51; i++){
            for(let j = 1; j < 51; j++){
                if(map[i][j][0] === group) map[i][j][1] = v;
            }
        }
    }
}
function updateAll(rest, map){
    const [v1, v2] = rest;
    
    for(let i = 1; i < 51; i++){
        for(let j = 1; j < 51; j++){
            if(map[i][j][1] === v1) map[i][j][1] = v2;
        }
    }
}

function merge(rest, map, groupNum){
    const [r1, c1, r2, c2] = rest;
    const [gn1, v1] = map[r1][c1];
    const [gn2, v2] = map[r2][c2];
    
    let value = 'EMPTY';
    
    if (gn1 !== 0 && gn2 !== 0 && gn1 === gn2) {
        return false;
    }
    
    if(v1 === 'EMPTY' && v2 !== 'EMPTY') value = v2;
    else value = v1;
    
    map[r1][c1] = [groupNum, value];
    map[r2][c2] = [groupNum, value];
    
    for(let i = 1; i < 51; i++){
        for(let j = 1; j < 51; j++){
            if((gn1 !== 0 && map[i][j][0] === gn1) || (gn2 !== 0 && map[i][j][0] === gn2)) {
                map[i][j] = [groupNum,value];
            }            
        }
    }
    return true;
}
function unmerge(rest, map){
    const [r,c] = rest;
    const [group, value] = map[r][c];
    
    if(group === 0) return;
    
    for(let i = 1; i < 51; i++){
        for(let j = 1; j < 51; j++){
            if(map[i][j][0] === group) map[i][j]= [0, 'EMPTY'];
        }
    }
    map[r][c] = [group, value];
}