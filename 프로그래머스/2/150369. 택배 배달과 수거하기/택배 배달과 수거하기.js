function solution(cap, n, deliveries, pickups) {
    var answer = 0;
    
    const findEndPoint = (arr) => {
        for(let i = arr.length - 1; i >= 0; i--){
            if(arr[i] > 0) return i;
            else arr.pop();
        }
        return -1;
    }
    
    const calc = (arr, cnt) => {
        let endPoint = findEndPoint(arr);
        
        let end = endPoint;
        
        if(end == -1) return -1;
        else{
            while(cnt > 0){
                if(arr[end] >= cnt){
                    arr[end] -= cnt;
                    cnt = 0;
                    break;
                }else{
                    cnt -= arr[end];
                    arr[end] = 0;
                    end--;
                }
            }
        }
        return findEndPoint(arr);
    }
    
    let deliveryEndPoint = findEndPoint(deliveries);
    let pickupEndPoint = findEndPoint(pickups);
    let maxEndPoint = Math.max(deliveryEndPoint, pickupEndPoint);
    
    while(maxEndPoint >= 0){
        if(maxEndPoint == 0) answer += 2;
        else answer += (maxEndPoint+1) * 2;
        
        deliveryEndPoint = calc(deliveries, cap);
        pickupEndPoint = calc(pickups, cap);
        maxEndPoint = Math.max(deliveryEndPoint, pickupEndPoint);
    }
    
    return answer;
}