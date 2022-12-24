public class Algorithm_Q67 {
    /*
     * 해당 문제는 DP 문제이다. 이 문제를 해결하기 위해서는 이전의 결과를 가져와 또 활용할 수 있도록 해야하며, 이에 따른 규칙을 발견해 식을 세우면 된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/domino-and-tromino-tiling/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int numTilings(int n) {
        // 문제들의 각 n에서의 숫자를 나열하고 점화식을 만들어 보면 쉽게 해결이 된다.
        // n == 4, n == 5를 나열해 두고 계산을 해보면 규칙을 얻어낼 수 있다.
        long[] dp = new long[n];
        int answer = 0;

        if(n <= 3) {
            if(n == 1) {
                return 1;
            } else if(n == 2) {
                return 2;
            } else {
                return 5;
            }
        }

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 5;

        for(int i = 3; i < n; i++) {
            // n이 증가할 때 관계를 체크해 볼 때 점화식은 An = An-1 * 2 + An-3 의 공식이 성립
            // 이때 결과값이 long의 값 범위도 넘어서는 경우가 있으므로 반드시 modulo를 처리할 것
            dp[i] = ((dp[i - 1] * 2) + dp[i - 3]) % 1000000007;
        }

        answer = (int) dp[n - 1];

        return answer;
    }
}
