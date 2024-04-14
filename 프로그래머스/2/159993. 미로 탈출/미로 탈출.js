   
class node {
    constructor(r,c,cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

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
        
        return minValue
    }
    bubbleDown(){
        let curIdx = 0;
        let leftIdx = curIdx + 1;
        let rightIdx = curIdx + 2;
        
        while((this.heap[leftIdx] && this.heap[leftIdx].cnt < this.heap[curIdx].cnt) ||
              (this.heap[rightIdx] && this.heap[rightIdx].cnt < this.heap[curIdx].cnt)){
            let smallerIdx = leftIdx;
            
            if(this.heap[rightIdx] && this.heap[rightIdx].cnt < this.heap[smallerIdx].cnt){
                smallerIdx = rightIdx;
            }
            this.swap(smallerIdx, curIdx);
            
            curIdx = smallerIdx;
            leftIdx = curIdx + 1;
            rightIdx = curIdx + 2;
        }
    }
    
    push(e){
        this.heap.push(e);
        
        this.bubbleUp();
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

let rSize = 0;
let cSize = 0;
function solution(maps) {
    let answer = 0;
    rSize = maps.length;
    cSize = maps[0].length;
    
    let start = [];
    let lever = [];
    let exit = [];
    let isVisited = Array.from(Array(rSize), () => Array(cSize).fill(0));
    
    maps.forEach((floor, r) => {
        floor.split('').forEach((e, c) => {
            if(e === 'S') start = [r,c];
            if(e === 'L') lever = [r,c];
            if(e === 'E') exit = [r,c];
            
            if(e === 'X') isVisited[r][c] = 1;
        })
    });
    
    answer += bfs(maps, start, lever, isVisited.map(row => [...row]));
    
    answer += bfs(maps, lever, exit, isVisited.map(row => [...row]));    
    
    return answer ? answer : -1;
}

const dr = [-1,0,1,0];
const dc = [0,-1,0,1];
const bfs = (map, S, E, isVisited) => {
    let minHeap = new MinHeap();
    minHeap.push(new node(S[0],S[1], 0));
    isVisited[S[0]][S[1]] = 1;
    
    while(minHeap.size() > 0){
        let curNode = minHeap.pop();

        let cR = curNode.r;
        let cC = curNode.c;
        let cCnt = curNode.cnt;
        
        if(cR == E[0] && cC == E[1]){
            return cCnt;
        }
        
        for(let i = 0; i < 4; i++){
            let nextR = cR + dr[i];
            let nextC = cC + dc[i];
            let nextCnt = cCnt + 1;
            
            if(isIn(nextR, nextC) && !isVisited[nextR][nextC]){
                isVisited[nextR][nextC] = 1;
                minHeap.push(new node(nextR, nextC, nextCnt));
            }
        }
    }   
}
const isIn = (r, c) => {
    return r >= 0 && r < rSize && c >= 0 && c < cSize;
}

    
 