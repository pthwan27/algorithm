function solution(maps) {
    let n = maps.length;
    let m = maps[0].length;
    
    let isVisited = Array.from({length : n}, () => new Array(m).fill(0));
    
    const dr = [-1, 0, 1, 0];
    const dc = [0, -1, 0, 1];
    
    const isIn = (r,c) => {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    let minHeap = new MinHeap();
    
    minHeap.push([0, 0, 0]);
    isVisited[0][0] = 1;
    
    while(minHeap.size() > 0){
        let next = minHeap.pop();
        
        for(let i = 0; i < 4; i++){
            let nextDr = next[0] + dr[i];
            let nextDc = next[1] + dc[i];
            let nextCnt = next[2] + 1;
            if(nextDr == n-1 && nextDc == m-1) return nextCnt + 1;
            
            if(!isIn(nextDr, nextDc)) continue;
            
            if(!isVisited[nextDr][nextDc] && maps[nextDr][nextDc]){
                isVisited[nextDr][nextDc] = 1;
                minHeap.push([nextDr, nextDc, nextCnt]);
            }
        }
    }
    
    return -1;
}
class MinHeap {
    constructor() {
        this.heap = [];
    }

    size() {
        return this.heap.length;
    }

    push(e) {
        this.heap.push(e);

        this.bubbleUp();
    }

    bubbleUp(){
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);

        while(this.heap[parentIdx] && this.heap[curIdx][2] < this.heap[parentIdx][2]){
            this.swap(parentIdx, curIdx);

            curIdx = parentIdx;
            parentIdx =  Math.floor((curIdx - 1) / 2);
        }
    }

    pop() {
        if(this.heap.length == 0) return null;

        if(this.heap.length == 1) return this.heap.pop();

        let minValue = this.heap[0];

        this.heap[0] = this.heap.pop();

        this.bubbleDown();

        return minValue;
    }
    bubbleDown(){
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;

        while((this.heap[leftIdx] && this.heap[leftIdx][2] < this.heap[curIdx][2]) ||
              (this.heap[rightIdx] && this.heap[rightIdx][2] < this.heap[curIdx][2])){
            let smallerIdx = leftIdx;

            if(this.heap[rightIdx] && this.heap[rightIdx][2] < this.heap[leftIdx][2]){
                smallerIdx = rightIdx;
            }

            this.swap(smallerIdx, curIdx);

            curIdx = smallerIdx;
            leftIdx = curIdx * 2 + 1;
            rightIdx = curIdx * 2 + 2;
        } 
    }

    swap(idx1, idx2){
        [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]]; 
    }
}


