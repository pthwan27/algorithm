function solution(coin, cards) {
    var round = 0;
    
    const targetSum = cards.length + 1;
    
    let handCards = cards.splice(0, cards.length / 3);
    let saveCards = [];
    
    while(cards.length){
        const pickedCards = cards.splice(0, 2);
        
        pickedCards.forEach((e) => saveCards.push(e));
        
        const hand_card = isOnePossible(handCards, targetSum);
        
        if(hand_card){
            handCards = handCards.filter((e) => !hand_card.includes(e));
            round++;
            continue;
        }
        
        const hand_save_card = isTwoPossible(handCards, saveCards, targetSum);
        
        if(hand_save_card && coin > 0){
            handCards = handCards.filter((e) => e != hand_save_card[0]);
            saveCards = saveCards.filter((e) => e != hand_save_card[1]);
            
            coin--;
            round++;
            continue;
        }
        
        const save_card = isOnePossible(saveCards, targetSum);
        
        if(save_card && coin > 1){
            saveCards = saveCards.filter((e) => !save_card.includes(e));
            
            coin -= 2;
            round++;
            continue;
        }
        
        break;
    }
    
    return round + 1;
}
const isOnePossible = (arr, targetSum) => {
    for(let i = 0; i < arr.length - 1; i++){
        for(let j = i + 1; j < arr.length; j++){
            if(arr[i] + arr[j] === targetSum){
                return [arr[i], arr[j]];
            }
        }
    }
    return null;
}

const isTwoPossible = (arr1, arr2, targetSum) => {
     for(let i = 0; i < arr1.length; i++){
        for(let j = 0; j < arr2.length; j++){
            if(arr1[i] + arr2[j] === targetSum){
                return [arr1[i], arr2[j]];
            }
        }
    }
    return null;
}
