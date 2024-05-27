function solution(book_time) {
    let minHeap = new MinHeap();
    
    book_time.forEach(([start, end], idx) => {
        let [shh, smm] = start.split(":").map(e => Number(e));
        let [ehh, emm] = end.split(":").map(e => Number(e));
        
        book_time[idx] = [shh * 60 + smm, ehh* 60 + emm];
    });
    
    book_time.sort((a,b) => {
        if(a[0] === b[0]) return a[1] - b[1];
        else return a[0] - b[0];
    });
    minHeap.push(book_time[0]);
    
    for(let i = 1; i < book_time.length; i++){
        if(minHeap.peek()[1] + 10 > book_time[i][0]) {
            minHeap.push(book_time[i]);
        }else{
            minHeap.pop();
            minHeap.push(book_time[i]);
        }
    }
    
    return minHeap.size();
}
class MinHeap{
    constructor() {
        this.heap = [];
    }
    size() { 
        return this.heap.length;
    }
    peek() {
        if(this.heap.length === 0) return null;
        
        return this.heap[0];
    }
    
    push(e) {
        this.heap.push(e);
        
        this.bubbleUp();
    }
    
    bubbleUp() {
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);
        
        while(this.heap[parentIdx] && this.heap[curIdx][1] < this.heap[parentIdx][1]){
            this.swap(parentIdx, curIdx);
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx - 1) / 2);
        }
    }
    
    pop() {
        if(this.heap.length === 0) return null
        if(this.heap.length === 1) return this.heap.pop();
        
        let popValue = this.heap[0];
        
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
    }
    
    bubbleDown() {
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;
        
        while((this.heap[leftIdx] && this.heap[leftIdx][1] < this.heap[curIdx][1]) ||
             (this.heap[rightIdx] && this.heap[rightIdx][1] < this.heap[curIdx][1])){
            
            let smallerIdx = leftIdx;
            
            if(this.heap[rightIdx] && this.heap[rightIdx][1] < this.heap[leftIdx][1]) {
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