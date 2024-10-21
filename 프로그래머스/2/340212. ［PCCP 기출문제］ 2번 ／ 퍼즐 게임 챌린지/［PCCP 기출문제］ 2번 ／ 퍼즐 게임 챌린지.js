function solution(diffs, times, limit) {
    let answer = 1;
    
    const diffs_length = diffs.length;
    
    let [start_level, end_level] = [1, diffs.reduce((acc,cur) => Math.max(acc, cur),1)];

    let dur_time = times[0];
    let isPossible = true;
    
    while(start_level < end_level) {
        let mid_level = Math.floor((start_level + end_level) / 2);
        
        for(let i = 1; i < diffs.length; i++){
            if(mid_level >= diffs[i]) {
                dur_time += times[i];
            } else {
                dur_time += ((diffs[i] - mid_level) * (times[i] + times[i - 1])) + times[i];
            }
        }
        
        if(dur_time > limit) {
            start_level = mid_level + 1;
        } else {
            end_level = mid_level;
        }
        
        dur_time = times[0];
    }
    
    return end_level;
}