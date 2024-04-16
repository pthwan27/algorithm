function solution(edges) {    
    let maxIdx = edges.reduce((acc, edge) => Math.max(acc,edge[0],edge[1]), -1);
    let inout = new Array(maxIdx).fill(-1);
    
    let generatedV = -1;
    let donut = 0;
    let stickS = 0;
    let stickE = 0;
    let eight = 0;
    
    for (let i = 0; i <= maxIdx; i++) {
        inout[i] = new node();
    }

    edges.forEach(edge => {
        inout[edge[0]].out();
        inout[edge[1]].in();
    });
    
    for(let i = 1; i <= maxIdx; i++){
        if(inout[i].i == -1 || inout[i].o == -1) continue;
        
        if(inout[i].i == 0 && inout[i].o >= 2){
            generatedV = i;
        }
    }
    
    let generatedVout = inout[generatedV].o;
    
    edges.forEach(edge => {
        if(edge[0] == generatedV){ 
            inout[edge[0]].o--;
            inout[edge[1]].i--;
        }
    })
    
    for(let i = 1; i <= maxIdx; i++){
        if(i == generatedV) continue;
        if(inout[i].i == 2 && inout[i].o == 2) eight++;
        
        if(inout[i].i == 1 && inout[i].o == 0) stickE++;
        if(inout[i].i == 0 && inout[i].o == 1) stickS++;
        if(inout[i].i == 0 && inout[i].o == 0) {
            stickS++; stickE++;
        }
    }
    return [generatedV, generatedVout - (Math.min(stickE, stickS) + eight), Math.min(stickE, stickS), eight];
}
class node{
    constructor(){
        this.i = -1;
        this.o = -1;
    }
    in(){
        if(this.i == -1) {
            this.i = 1;
            this.o = 0;
        }
        else this.i++;
    }
    out(){
        if(this.o == -1) {
            this.o = 1;
            this.i = 0;
        }
        else this.o++;
    }
}