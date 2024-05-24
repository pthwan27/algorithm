function solution(coin, cards) {
    let round = 1;
    
    let n = cards.length;    
    let startCards = cards.splice(0, cards.length / 3); 
    let newCards = [];

    while(cards.length) {
        let drawCards = cards.splice(0, 2);
        drawCards.map(e => newCards.push(e));
        
        let selectedCards = isPossible(startCards);
        if(selectedCards) {
            startCards = startCards.filter(e => e !== selectedCards[0] && e !== selectedCards[1]);
            round++;
            continue;            
        }
        
        selectedCards = isPossibleWithNew(startCards, newCards);
        if(selectedCards && coin > 0) {
            startCards = startCards.filter(e => e !== selectedCards[0]);
            newCards = newCards.filter(e => e !== selectedCards[1]);
            coin--;
            round++;
            continue;
        }
        
        selectedCards = isPossible(newCards);
        if(selectedCards && coin > 1){
            newCards = newCards.filter(e => e !== selectedCards[0] && e !== selectedCards[1]);
            coin -= 2;
            round++;
            continue;
        }
        
        break;
    }
    
    function isPossible(cards) {
        for(let i = 0; i < cards.length; i++){
            for(let j = i+1; j < cards.length; j++){
                if(cards[i] + cards[j] === n+1) return [cards[i], cards[j]];
            }
        }
        return false;
    }
    
    function isPossibleWithNew(handsCard, newCard) {
        for(let i = 0; i < handsCard.length; i++){
            for(let j = 0; j < newCard.length; j++){
                if(handsCard[i] + newCard[j] === n+1) return [handsCard[i], newCard[j]];
            }
        }
        return false;
    }
    
    return round;
}