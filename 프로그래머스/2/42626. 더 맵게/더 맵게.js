function solution(scoville, K) {
    var answer = 0;
    
    let minHeap = new MinHeap();
    
    scoville.forEach((e) => minHeap.push(e));
            minHeap.print();

    while(minHeap.size() >= 2 && minHeap.peek() < K){
        let notSpicyFood = minHeap.pop();
        let notSpicyFood2 = minHeap.pop();

        minHeap.push(notSpicyFood + notSpicyFood2 * 2);
        answer++;
    }
    return minHeap.peek() >= K ? answer : -1;
}

class MinHeap {
    constructor() {
        this.heap = [];
    }
    
    size() {
        return this.heap.length;
    }
    print(){
        return console.log(this.heap);
    }
    peek(){
        if(this.heap.length == 0) return null;
        else return this.heap[0];
    }
    push(e){
        this.heap.push(e);
        this.bubbleUp();
    }    
    bubbleUp(){
        let curIdx = this.heap.length - 1;
        let parentIdx = Math.floor((curIdx- 1) / 2);
        
        while(curIdx > 0 && this.heap[curIdx] < this.heap[parentIdx]){
            const temp = this.heap[curIdx];
            this.heap[curIdx] = this.heap[parentIdx];
            this.heap[parentIdx] = temp;
            
            curIdx = parentIdx;
            parentIdx = Math.floor((curIdx - 1) / 2);
        }
    }
    
    pop(){
        if(this.heap.length == 0) return null;
        
        if(this.heap.length == 1) return this.heap.pop();
        

        let value = this.heap[0];
        this.heap[0] = this.heap.pop();
        
        this.bubbleDown();
        
        return value;
    }
    
    bubbleDown(){
        let idx = 0;
        
        while(idx * 2 + 1 < this.heap.length){
            let leftIdx = idx * 2 + 1;
            let rightIdx = idx * 2 + 2;
            
            let smallerIdx = leftIdx;
            
            if(rightIdx < this.heap.length && this.heap[rightIdx] < this.heap[leftIdx]){
                smallerIdx = rightIdx;
            }
            
            if(this.heap[idx] < this.heap[smallerIdx]){
                break;
            }
            
            const temp = this.heap[idx];
            this.heap[idx] = this.heap[smallerIdx];
            this.heap[smallerIdx] = temp;
            
            idx = smallerIdx;
        }
    }
    
    swap(idx1, idx2){
        [this.heap[idx1],this.heap[idx2]] = [this.heap[idx2],this.heap[idx1]];
    }
}