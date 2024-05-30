class MinHeap { 
    constructor() {
        this.heap = [];
    }
    
    push(e) {
        this.heap.push(e);
        
        this.bubbleUp();
    }
    
    bubbleUp() {
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx - 1) / 2);
        
        while(this.heap[parentIdx] && this.heap[curIdx] < this.heap[parentIdx]){
            this.swap(curIdx, parentIdx);
            
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx -1) / 2);
        }
    }
    
    pop() {
        if(this.heap.length === 0) return null;
        if(this.heap.length === 1) return this.heap.pop();
        
        let minValue = this.heap[0];
        
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
        
        return minValue;
    }
    bubbleDown() {
        let curIdx = 0;
        let leftIdx = curIdx * 2 + 1;
        let rightIdx = curIdx * 2 + 2;
        
        while((this.heap[leftIdx] && this.heap[leftIdx] < this.heap[curIdx]) ||
             (this.heap[rightIdx] && this.heap[rightIdx] < this.heap[curIdx])){
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
    
    swap(idx1, idx2){
        [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
    }
}

function solution(n, k, enemy) {
    let answer = enemy.length;
    
    let minHeap = new MinHeap();
    
    for(let i = 0; i < enemy.length; i++){
        if(i < k) {
            minHeap.push(enemy[i]);
        }else{
            let popEnemy = minHeap.pop();
            
            if(popEnemy < enemy[i] && n - popEnemy >= 0) {
                n -= popEnemy;
                minHeap.push(enemy[i]); 
            } else {
                n -= enemy[i];
                minHeap.push(popEnemy);
            }
            
            if(n < 0) return i;
        }
    }
    
    return answer;
}