function solution(nums) {
    var uniqueArr = nums.filter((num, i) => {
        return nums.indexOf(num) === i;
    });
    
    return nums.length / 2 > uniqueArr.length ? uniqueArr.length : nums.length / 2;
}