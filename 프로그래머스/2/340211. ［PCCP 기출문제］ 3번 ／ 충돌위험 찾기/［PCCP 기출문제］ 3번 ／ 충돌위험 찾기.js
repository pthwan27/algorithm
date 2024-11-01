class Point{
    constructor(r,c){
        this.r = r;
        this.c = c;
    }
}
function solution(points, routes) {
    let pointArr = [];
    
    routes.forEach((route) => {
        let routeArr = [];
        for(let i = 0; i < route.length; i++){ 
            let point = new Point(...points[route[i] - 1]);
            routeArr.push(point);
        }
        pointArr.push(routeArr);
    })
    
    // 충돌확인 로직
    let cnt = isCrashCheck(pointArr);

    while(pointArr.length > 0) {
        let pointArrLen = pointArr.length;         
        let curPoints = [];        
        while(pointArrLen--) {
            let points = pointArr.pop();
            
            if(points[0].r !== points[1].r) {
                points[0].r < points[1].r ? points[0].r += 1 : points[0].r -= 1;
            } else{
                points[0].c < points[1].c ? points[0].c += 1 : points[0].c -= 1;
            }
            curPoints.push(points);
        }
    
        pointArr = isFinishedCheck(curPoints);
        cnt += isCrashCheck(curPoints);
    }
    return cnt;
    
    function isCrashCheck(points) {
        let cnt = 0;
        const isCheck = new Array(points.length).fill(false);
        for(let i = 0; i < points.length; i++){
            if(isCheck[i]) continue;
            
            for(let j = 0; j < points.length; j++){
                if(i === j) continue;
                if(isCheck[j]) continue;
                
                if(points[i][0].r === points[j][0].r && points[i][0].c === points[j][0].c){
                    isCheck[i] = true;
                    isCheck[j] = true;
                }
            }
            
            if(isCheck[i]) cnt++
        }
        
        return cnt;
    }
    
    function isFinishedCheck(points) {
        const changedArr = [];
        
        for(let i = 0; i < points.length; i++){
            //경유하지 않을 때, 목적지에 도착했다면
            if(points[i].length === 2 && points[i][0].r === points[i][1].r && points[i][0].c === points[i][1].c) {
                continue;
            }
            
            if(points[i].length > 2 && points[i][0].r === points[i][1].r && points[i][0].c === points[i][1].c) {
                points[i].shift();
                
            }
            changedArr.push(points[i]);
        }
        return changedArr;
    }
    
}