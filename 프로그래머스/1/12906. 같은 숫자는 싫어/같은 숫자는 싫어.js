function solution(arr)
{
    const notContinusArr = arr.filter((num, i) => {
        if(i > 0){
            if(arr[i-1] != arr[i]) return true;
            else return false;
        }else{
            return true;
        }
    })
    
    return notContinusArr;
}