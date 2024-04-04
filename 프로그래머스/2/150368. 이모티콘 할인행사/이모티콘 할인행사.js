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
    console.log(emoDiscounts);
    
    emoDiscounts.forEach((discount, idx) => {        
        let subsCnt = 0;
        let totalAmt = 0;
        
        users.forEach((user) => {
            let amt = 0;
            
            for(let i = 0; i < emoticons.length; i++){
                //유저 구매 할인 기준 충족 시
                if(user[0] <= discount[i]){
                    amt += (emoticons[i] * (100 - discount[i])) / 100;
                }
            }
            
            if(amt >= user[1]){
                subsCnt++;
                amt = 0;
            }else{
                totalAmt += amt;
            }
        });
        
        if(subsCnt > answer[0]){
            answer = [subsCnt, totalAmt];
        }else if(subsCnt === answer[0] && totalAmt > answer[1]){
            answer[1] = totalAmt;   
        }
    });
    
    return answer;
}