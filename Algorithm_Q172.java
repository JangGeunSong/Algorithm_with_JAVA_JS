public class Algorithm_Q172 {
    /*
     * 이 문제는 DP를 사용해 최적해를 구하는 문제이다.
     * https://leetcode.com/problems/stone-game-iii/
     * 풀이를 확인해보자.
     */

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp는 두 플레이어 간의 점수 차이값을 저장한다.
        int[] dp = new int[n + 1];

        // DP를 가장 마지막 순간의 값부터 출발해 계산한다.
        for(int i = n - 1; i >= 0; i--) {
            // 가장 끝에 돌을 고르는 순간부터 차이를 계산해 얻어온다
            // case 1 1개의 돌만 갖고 올때 점수 차이를 먼저 현재 i 시점에 저장한다.
            // 1개의 돌만 갖고 온다면, 현재 시점 + 1 부터는 상대방이 갖고 오기 때문에, 이를 고려해서 
            // 해당 점수차를 뺀다.
            dp[i] = stoneValue[i] - dp[i + 1];

            // 이후 3개까지는 갖고 올 수 있으므로, 현재 시점은 i에서 돌을 2개, 3개 까지 갖고 올 수 있다면
            // 한번 가져와 본 후 점수차가 가장 크게 나는 케이스를 dp 값으로 삼는다. 
            // 이렇게 점수차가 가장 크게 나도록 가져와야 서로 승리할 수 있기 때문이다.
            if(i + 2 <= n) {
                // 만약 현재 시점이 2개를 가져올 수 있을 때
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if(i + 3 <= n) {
                // 만약 현재 시점이 3개를 가져올 수 있을 때
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
        }

        // 모든 작업이 끝나면, 항상 Alice가 Bob 보다 먼저 시작한다고 했기 때문에, dp[0]에 값은 Alice의
        // Bob에 대항한 점수 차이이다. 즉, dp[0]이 음수라면 Bob의 점수가 더 높은 것이므로, Bob의 승리
        // 양수라면 Alice의 승리이며, 같다면 Tie로 리턴해야 한다.
        if(dp[0] > 0) {
            return "Alice";
        }

        if(dp[0] < 0) {
            return "Bob";
        }

        return "Tie";
    }
}
