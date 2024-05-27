function solution(book_time) {
    book_time.forEach(([start, end], idx) => {
        let [shh, smm] = start.split(":").map(e => Number(e));
        let [ehh, emm] = end.split(":").map(e => Number(e));
        
        book_time[idx] = [shh * 60 + smm, ehh* 60 + emm];
    });
     
    book_time.sort((a,b) => {
        if(a[0] === b[0]) return a[1] - b[1];
        else return a[0] - b[0];
    });
    const endTimes = [];
    
    for(const [start,end] of book_time){
        const findIdx = endTimes.findIndex(e => e <= start);
        
        if(findIdx === -1) endTimes.push(end + 10);
        else endTimes[findIdx] = end + 10;
    }

    return endTimes.length;
}