public class Algorithm_Q103 {
    /*
     * 이 문제는 입력값이 많아 brute force를 쓰지 않고 풀어야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
     * 풀이를 확인해보자.
     */
    
    // 수학적 계산을 통한 풀이 -> 단 한번의 계산만으로 종료
    public int countOdds(int low, int high) {
        int answer = 0;
        
        // 숫자들을 나열해 보면 모두 짝수면 사이에 홀수의 갯수는 높은값 - 낮은값 의 반이다
        if(low % 2 == 0 && high % 2 == 0) {
            answer = (high - low) / 2;
        } else {
            // 이 외의 경우는 높은값 - 낮은값 의 반에 1을 더한 값이 된다
            answer = ((high - low) / 2) + 1;
        }
        
        return answer;
    }

    // brute force => TLE occur!!
    public int countOdds_bruteforce(int low, int high) {
        int answer = 0;

        // brute force
        // 특정 범위 사이에 홀수만 고르려면 low 부터 시작해서 high 까지 도달할 때
        // 1씩 증가하게 만들어 주고, 여기에 대해 2로 나눌 때 나머지가 1인 것을 찾아서 더한다.
        while(low <= high) {
            if(low % 2 == 1) {
                answer += 1;
            }
            low += 1;
        }

        return answer;
    }
}
