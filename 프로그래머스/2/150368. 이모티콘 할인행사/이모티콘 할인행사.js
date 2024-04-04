function solution(users, emoticons) {
    let answer = new Array(2).fill(0);
    
    let discounts = [10, 20, 30, 40];
    
    const comb = (arr, size) => {
        if(size === 1) return arr.map((e) => [e]);
        
        let resultArr = [];
        
        arr.forEach((e, i, origin) => {
            let fixer = e
            let combArr = comb(origin, size - 1);
            let attachArr = combArr.map((v) => [fixer, ...v]);

            resultArr.push(...attachArr);              
        });
        return resultArr;
    }
    
    let emoDiscounts = comb(discounts, emoticons.length);
    
    emoDiscounts.forEach((discount, idx) => {        
        let subsCnt = 0;
        let totalAmt = 0;
        
        users.forEach((user) => {
            let amt = 0;
            
            emoticons.forEach((emo, idx) => {
                if(user[0] <= discount[idx]){
                    amt += (emoticons[idx] * (100 - discount[idx])) / 100;
                }
            });
            
            if(amt >= user[1]){
                subsCnt++;
                amt = 0;
            }else{
                totalAmt += amt;
            }
        });
        
        if(subsCnt > answer[0] || (subsCnt === answer[0] && totalAmt > answer[1])){
            answer = [subsCnt, totalAmt];
        }
    });
    
    return answer;
}