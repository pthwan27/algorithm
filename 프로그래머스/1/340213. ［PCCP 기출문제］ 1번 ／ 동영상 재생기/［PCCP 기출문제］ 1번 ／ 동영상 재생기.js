function solution(video_len, pos, op_start, op_end, commands) {
    let answer = "";
    [video_len, pos, op_start, op_end] = [strChangeSec(video_len), strChangeSec(pos), strChangeSec(op_start), strChangeSec(op_end)];
        
    commands.forEach(e => {
        if(pos >= op_start && pos <= op_end) {
            pos = op_end;
        }
        
        if(e === "next"){
            pos = pos + 10 > video_len ? video_len : pos + 10;    
        }else{
            pos = pos - 10 < 0 ? 0 : pos - 10;
        }
    });

    if(pos >= op_start && pos <= op_end) {
        pos = op_end;
    }
    
    answer = secChangeStr(pos);
    
    return answer;
    
    function strChangeSec(time) {
        let sec = time.split(":").reduce((acc,e,idx) => {
            return acc += idx === 0 ? Number(e) * 60 : Number(e)
        }, 0);
        
        return sec;
    }
    
    function secChangeStr(time) {
        let min = Math.floor(time / 60);
        let sec = time % 60;
        
        return String(min).padStart(2, '0') + ":" + String(sec).padStart(2, '0');
    }
}