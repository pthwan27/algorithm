function solution(numbers) {
    const answer = numbers.map(n => String(n));
    
    answer.sort((n1, n2) => (n2 + n1).localeCompare(n1 + n2));
    
    const sb = answer.join('');
    
    return sb.charAt(0) == '0' ? '0' : sb;
}