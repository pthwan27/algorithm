function solution(users, emoticons) {
    let answer = [];
    let discounts = [10, 20, 30, 40];
    
    const comb = (arr, size) => {
        if(size === 1) return arr.map(e => [e]);
        
        let result = [];
        
        arr.forEach((e, idx, origin) => {
            let fixed = e;
            let combArr = comb(origin, size - 1);
            let attachedArr = combArr.map(v => [fixed, ...v]);
            
            result.push(...attachedArr);
        });
        return result;
    }
    const calc = (u, d, e) => {
        let result = new Array(2).fill(0);
        e.forEach((emoticon, i) => {
            if(u[0] <= d[i]) {
                result[0] += (emoticon * (100 - d[i])) / 100;
            }
        });
        if(result[0] >= u[1]){
            result[0] = 0;
            result[1] = 1;
        }
        return result;
    }
    
    let discountArr = comb(discounts, emoticons.length);
    
    let maxSubscriber = -1;
    let maxTotal = -1;
    
    discountArr.forEach((discount, idx) => {
        let totalSubscriber = 0;
        let totalPrice = 0;
        
        users.forEach(user => {
            let [price, subscriber] = calc(user, discount, emoticons);
            totalPrice += price;
            totalSubscriber += subscriber;
        });
        
        if(totalSubscriber > maxSubscriber){
            maxSubscriber = totalSubscriber;
            maxTotal = totalPrice;
        }else if(totalSubscriber === maxSubscriber && totalPrice > maxTotal){
            maxTotal = totalPrice;
        }
    });
    
    
    return [maxSubscriber, maxTotal];
}