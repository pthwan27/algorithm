class MinHeap{
    constructor() {
        this.heap = [];
    }
    size() {
        return this.heap.length;
    }
    peek() {
        if(this.heap.length === 0) return null;
        else return this.heap[0];
    }
    push(e) {
        this.heap.push(e);
        
        this.bubbleUp();
    }
    
    bubbleUp() {
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);
        
        while(this.heap[parentIdx] && this.heap[parentIdx] > this.heap[curIdx]){
            this.swap(curIdx, parentIdx);
            
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx - 1) / 2);
        }
    }
    
    pop(){
        if(this.heap.length === 0) return null;
        if(this.heap.length === 1) return this.heap.pop();
        
        let curValue = this.heap[0];
        
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
        
        return curValue;
    }
    bubbleDown(){
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;
        
        while((this.heap[leftIdx] && this.heap[leftIdx] < this.heap[curIdx]) ||
              (this.heap[rightIdx] && this.heap[rightIdx] < this.heap[curIdx])){
            let smallerIdx = leftIdx;
            
            if(this.heap[rightIdx] && this.heap[rightIdx] < this.heap[leftIdx]) {
                smallerIdx = rightIdx;
            }
            
            this.swap(curIdx, smallerIdx);
            
            curIdx = smallerIdx;
            leftIdx = curIdx * 2 + 1;
            rightIdx = curIdx * 2 + 2;
        }
    }
    
    swap(idx1, idx2) {
        [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
    }
}
function solution(k, n, reqs) {
    let answer = Infinity;
    
    const comb = (remain, curIdx, combArr, calcTime) => {
        if(curIdx === k) {
            combArr[curIdx] = remain + 1;
            
            calcTime(combArr);
            return;
        }
        
        for(let i = 0; i <= remain; i++){
            combArr[curIdx] = i + 1;
            comb(remain - i, curIdx + 1, combArr, calcTime);
        }
    }
    const calcTime = (mentos) => {
        let minHeapArr = new Array(k+1).fill().map(() => new MinHeap());
        let totalDelayTime = 0;
        
        reqs.forEach(([sT, dT, type], idx) => {
            if(minHeapArr[type].peek() <= sT){
                minHeapArr[type].pop();
                minHeapArr[type].push(sT + dT);
            }else{
                if(minHeapArr[type].size() < mentos[type]){
                    minHeapArr[type].push(sT + dT);
                }else{
                    let nextEndTime = minHeapArr[type].pop();
                    totalDelayTime += nextEndTime - sT;
                    minHeapArr[type].push(nextEndTime + dT);
                }
            }
        });
        
        answer = Math.min(answer, totalDelayTime);
    }
    
    comb(n-k, 1, new Array(k).fill(0), calcTime);
    
    return answer;
}