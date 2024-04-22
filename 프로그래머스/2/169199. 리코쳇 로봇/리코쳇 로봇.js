
class MinHeap {
    constructor() {
        this.heap = [];
    }
    size() {
        return this.heap.length;
    }
    
    pop() {
        if(this.heap.length == 0) return null;
        if(this.heap.length == 1) return this.heap.pop();
        
        let minValue = this.heap[0];
        
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
        
        return minValue;
    }
    
    push(e) {
        this.heap.push(e);
        
        this.bubbleUp();
    }
    
    bubbleDown() {
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;
        
        while((this.heap[leftIdx] && this.heap[curIdx].cnt > this.heap[leftIdx].cnt)
             ||(this.heap[rightIdx] && this.heap[curIdx].cnt > this.heap[rightIdx].cnt)){
            let smallerIdx = leftIdx;
            
            if(this.heap[rightIdx] && this.heap[leftIdx].cnt > this.heap[rightIdx].cnt){
                smallerIdx = rightIdx;
            }
            this.swap(smallerIdx, curIdx);
            
            curIdx = smallerIdx; 
            leftIdx = curIdx * 2 + 1;
            rightIdx = curIdx * 2 + 2;
        }
    }
    
    
    bubbleUp(){
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);
        
        while(this.heap[parentIdx] && this.heap[parentIdx].cnt > this.heap[curIdx].cnt){
            this.swap(curIdx, parentIdx);
            
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx - 1) / 2);
        }
    }
    
    swap(idx1, idx2){
        [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
    }
}
class Node {
    constructor(r, c, cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

function solution(board) {
    let answer = 0;
    const R = board.length;
    const C = board[0].length;
    let isVisited = Array.from({length : R}, () => new Array(C).fill(0));
    let start = [];
    let end = [];
    
    board.forEach((e1, r) => {
        e1.split('').forEach((e2, c) => {
            if(board[r][c] === 'R') {
                start = [r,c];
            }
            if(board[r][c] === 'G') {
                end = [r,c];
            }
            if(board[r][c] === 'D'){
                isVisited[r][c] = 1;
            }
        })
    });
    
    answer = bfs(start, end, board, isVisited, R, C);
    return answer;
}
const dr = [-1, 0, 1, 0];
const dc = [0, -1, 0, 1];

function bfs(s, e, map, isVisited, R, C){
    let bfsQ = new MinHeap();
    
    bfsQ.push(new Node(s[0], s[1], 0));
    
    while(bfsQ.size()){
        const curNode = bfsQ.pop();
        
        const curR = curNode.r;
        const curC = curNode.c;
        const curCnt = curNode.cnt;
        
        if(e[0] == curR && e[1] == curC) return curCnt;
        
        for(let i = 0; i < 4; i++){
            let nextR = curR + dr[i];
            let nextC = curC + dc[i];
            let nextCnt = curCnt + 1;
            
            while(isIn(nextR, nextC, R, C) && map[nextR][nextC] !== 'D'){
                nextR += dr[i];
                nextC += dc[i];
            }
            nextR -= dr[i];
            nextC -= dc[i];
    

            if(isIn(nextR, nextC, R, C) && !isVisited[nextR][nextC]){
                isVisited[nextR][nextC] = 1;
                bfsQ.push(new Node(nextR, nextC, nextCnt));
            }
            
        }
    }
    return -1
}

function isIn(r, c, R, C){
    return r >= 0 && r < R && c >= 0 && c < C;
}