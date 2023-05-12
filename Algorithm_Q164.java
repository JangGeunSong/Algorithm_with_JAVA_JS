public class Algorithm_Q164 {
    /*
     * 이 문제도 어제와 같이 DP를 활용해 풀이하는 문제다.
     * https://leetcode.com/problems/solving-questions-with-brainpower/
     * 풀이를 확인해보자.
     */

    // 공식 풀이를 확인하여 풀이를 작성 하였다.
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];

        // 마지막 질문은 항상 푼다고 가정하자 (어차피 더 이상 스킵할 문제가 없으니까)
        dp[n - 1] = questions[n - 1][0];

        // 역순으로 정렬할때 일단 현재 dp에는 지금 문제를 푼다고 가정한다.
        for(int i = n - 2; i >= 0; i--) {
            dp[i] = questions[i][0];
            // 건너 뛰어야 하는 단계를 받아오고
            int skip = questions[i][1];

            if(i + skip + 1 < n) {
                // 현재 단계 + skip 할 단계 + 1 (+1은 지금 보다 dp[i + 1] 지점의 값을 구하는 것이기 때문이다)
                // 위 단계에서의 값과 지금 dp 값을 더한 값과의 합산이 dp[i]의 결과 값이 된다.
                dp[i] = dp[i] + dp[i + skip + 1];
            }

            // 이제 dp에 최대값을 얻으려면 지금 문제를 풀었을 때와 풀지 않았을 때 중 하나를 골라야 한다.
            // 즉 dp[i]는 시작할때 문제를 풀어 결과값을 받았으므로, 이게 풀었을 때로 그리고 바로 앞의
            // dp[i + 1] 이 풀지 않았을 때인데 이때 비교를 진행한다.
            dp[i] = Math.max(dp[i], dp[i + 1]);
        }

        // 위 과정을 거쳤을 때 dp[0]은 역산해서 최대한 많은 점수를 얻기 위해 진행한 결과이므로 이를 리턴한다.
        return dp[0];
    }
}
