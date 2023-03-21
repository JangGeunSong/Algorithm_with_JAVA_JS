public class Algorithm_Q128 {
    /*
     * 이 문제는 수학적인 내용을 기반으로 풀이하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/number-of-zero-filled-subarrays/
     * 풀이를 확인해보자.
     */

    // 풀이 코드 -> 오랜만의 순열의 합 공식이 나왔다. 자주 나오는 형태의 패턴이므로 이 값은 기억해 두자.
    public long zeroFilledSubarray(int[] nums) {
        // 해당 문제는 수학적인 풀이를 구현하면 되는 문제이다.
        // 부분 집합의 수 = (총 배열의 길이 * (총 배열의 길이 + 1)) / 2
        // 즉 (n * (n + 1)) / 2 임을 먼저 체크하고 풀이를 진행한다.
        // 부분 집합의 수는 자주 나오는 패턴이므로, 이 공식을 암기해도 되고, 직접 풀이해도 될 것 같다.
        // n개의 원소 숫자를 A_n 이라 할 때 A_n 까지의 합 공식을 기억하면 된다.
        // 즉 1 ~ n 까지의 합을 구하는 공식 이므로. 위와 같은 공식이 된다.
        long n = 0L;
        long answer = 0L;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                n += 1L;
            } else {
                // 만약 0이 나오지 않았다면, n이 0 이 아닐 때 합공식을 적용해 answer에 처리
                if(n != 0) {
                    answer += (n * (n + 1)) / 2;
                }
                // n을 0으로
                n = 0L;
            }
        }

        // n 이 반복문이 끝났을 때 0 이 아니라면 다시 합공식을 한번 더 진행한다.
        if(n != 0) {
            answer += (n * (n + 1)) / 2;
        }

        return answer;
    }
}
