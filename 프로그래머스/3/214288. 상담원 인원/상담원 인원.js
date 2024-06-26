class minHeap {
    constructor(){
        this.heap = [];
    }
    size() {
        return this.heap.length;
    }
    
    push(e){
        this.heap.push(e);
        
        this.bubbleUp();
    }
    pop(){
        if(this.heap.length === 0) return null;
        if(this.heap.length === 1) return this.heap.pop();
        
        let minValue = this.heap[0];
        
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
        return minValue;
    }
    
    bubbleUp(){
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);
        
        while(this.heap[parentIdx] && this.heap[curIdx] < this.heap[parentIdx]){
            this.swap(curIdx, parentIdx);
            
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx - 1) / 2);
        }
    }
    
    bubbleDown(){
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;
        
        while((this.heap[leftIdx] && this.heap[leftIdx] < this.heap[curIdx]) ||
             (this.heap[rightIdx] && this.heap[rightIdx] < this.heap[curIdx])) {
            let smallerIdx = leftIdx;
            
            if(this.heap[rightIdx] && this.heap[rightIdx] < this.heap[leftIdx]){
                smallerIdx = rightIdx;
            }
            
            this.swap(smallerIdx, curIdx);
            
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
    let result = [];
    
    let mentos = new Array(k+1).fill(1);
    
    function comb(remain, idx) {
        if(idx > k) return;
        
        if(remain === 0) {
            result.push(calcTime(mentos));
            return;
        }
        if(idx === k) {
            mentos[idx] += remain;
            comb(0, idx + 1);
            mentos[idx] -= remain;
            return;
        }
        for(let i = 0; i <= remain; i++){
            mentos[idx + 1] += i;
            comb(remain - i, idx + 1);
            mentos[idx + 1] -= i;
        }
    }
    
    function calcTime(mento) {
        let mentoHeap = new Array(k+1).fill().map(e => new minHeap());
        let totalWaitingTime = 0;
        
        reqs.forEach(([startTime, durTime, type]) => {
            if(mentoHeap[type].size() < mento[type]){
                mentoHeap[type].push(startTime + durTime);
            }else{
                let popTime = mentoHeap[type].pop();
                
                if(popTime > startTime){
                    totalWaitingTime += popTime - startTime;
                    mentoHeap[type].push(popTime + durTime);
                }else{
                    mentoHeap[type].push(startTime + durTime);
                }
                    
            }
        });
        
        return totalWaitingTime;
    }
    
    comb(n-k, 0);
    
    return Math.min(...result);
}