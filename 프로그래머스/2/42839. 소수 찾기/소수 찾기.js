function solution(numbers) {
    const numSet = new Set();
    
    const perm = (arr, selectedNums) => {
        for(let i = 0; i < arr.length; i++){
            const newNum = selectedNums + arr[i];
            
            const restArr = arr.filter((_, idx) => idx != i);
            
            if(!numSet.has(+newNum) && isPrime(+newNum)){
                numSet.add(+newNum);
            }
            perm(restArr, newNum)
        }
    }
    perm(Array.from(numbers), '');
    
    return numSet.size;
    
    function isPrime(num){
        if(num < 2) return false;
        
        else {
            for(let i = 2; i <= Math.sqrt(num); i++){
                if(num % i === 0) return false;
            }
            
            return true;
        }
    }
}