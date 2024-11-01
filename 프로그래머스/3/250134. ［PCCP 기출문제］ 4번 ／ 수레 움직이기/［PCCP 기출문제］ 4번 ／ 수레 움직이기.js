function solution(maze) {
    let answer = Infinity;
    const R_SIZE = maze.length;
    const C_SIZE = maze[0].length;
    
    const redStartPoint = new Array(2).fill(-1);
    const blueStartPoint = new Array(2).fill(-1);
    
    const isRedVisited = Array.from(Array(R_SIZE), () => new Array(C_SIZE).fill(false));
    const isBlueVisited = Array.from(Array(R_SIZE), () => new Array(C_SIZE).fill(false));
    
    const dr = [-1, 0, 1, 0];
    const dc = [0, -1, 0 ,1];
    
    maze.forEach((line,r) => {
        line.forEach((e,c) => {
            if(e === 1) {
                [redStartPoint[0],redStartPoint[1]] = [r,c];
                isRedVisited[r][c] = true;
            }else if(e === 2) {
                [blueStartPoint[0],blueStartPoint[1]] = [r,c];
                isBlueVisited[r][c] = true;
            }
        })
    });
    
    dfs(redStartPoint, blueStartPoint, false, false, 0);
    
    return answer === Infinity ? 0 : answer;
    
    function dfs(redPoint, bluePoint, isRedFinished, isBlueFinished, cnt) {
        if(isRedFinished && isBlueFinished) {
            answer = Math.min(answer, cnt);            
            return;
        }
        
        for(let r = 0; r < 4; r++){
            for(let c = 0; c < 4; c++){
                let nextRed = isRedFinished ? [redPoint[0], redPoint[1]] : [redPoint[0] + dr[r], redPoint[1] + dc[r]];                
                let nextBlue = isBlueFinished ? [bluePoint[0], bluePoint[1]] : [bluePoint[0] + dr[c], bluePoint[1] + dc[c]];
                    
                if(isInPoint(nextRed, nextBlue) && !isVisited(nextRed, isRedFinished, isRedVisited) && !isVisited(nextBlue, isBlueFinished, isBlueVisited) && !isCross(redPoint, bluePoint, nextRed, nextBlue) && !isSame(nextRed, nextBlue)) {
                    isRedVisited[nextRed[0]][nextRed[1]] = true;
                    isBlueVisited[nextBlue[0]][nextBlue[1]] = true;
                    
                    if(maze[nextRed[0]][nextRed[1]] === 3) isRedFinished = true;
                    if(maze[nextBlue[0]][nextBlue[1]] === 4) isBlueFinished = true;
                    
                    dfs(nextRed, nextBlue, isRedFinished, isBlueFinished, cnt + 1);
                    
                    isRedVisited[nextRed[0]][nextRed[1]] = false;
                    isBlueVisited[nextBlue[0]][nextBlue[1]] = false;
                    isRedFinished = false;
                    isBlueFinished = false;
                }
            }
        }
    }
    
    function isCross(point_1, point_2, next_point_1, next_point_2) {
        return point_1[0] === next_point_2[0] && point_1[1] === next_point_2[1] 
        && point_2[0] === next_point_1[0] && point_2[1] === next_point_1[1];
    }
    function isSame(point_1, point_2) {
        return point_1[0] === point_2[0] && point_1[1] === point_2[1];
    }
    function isInPoint(point_1, point_2) {
        return isIn(point_1) && isIn(point_2) && maze[point_1[0]][point_1[1]] !== 5 && maze[point_2[0]][point_2[1]] !== 5;
    }
    function isIn(point){
        return point[0] >= 0 && point[0] < R_SIZE && point[1] >= 0 && point[1] < C_SIZE;
    }
    function isVisited(point, finished, visited) {
        return finished ? false : visited[point[0]][point[1]]; 
    }
    
}

