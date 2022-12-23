import java.util.*;

public class Algorithm_Q66 {
    /*
     * 해당 문제는 DP 문제인데 이해하려면 기본적인 개념이 필요하다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     * 풀이를 확인해보자.
     */

    // 개념을 바탕으로 구현한 나의 풀이
    // https://www.youtube.com/watch?v=I7j0F7AHpb8 내용 참고
    public int maxProfit(int[] prices) {
        // 문제의 마지막에 구매했다면 또 구매는 불가능 하다고 되어 있다는 점에 주의
        int[][] dp = new int[prices.length][2]; // 0 => 구매 예정 true, 1 => 판매 예정 true 일때 인덱스
        int answer = 0; // 정답 변수

        // 모든 dp의 값을 INTEGER MIN VALUE로 처리한다 => 최대값을 구하는 문제이기 떄문에
        for(int i = 0; i < prices.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        // 0부터 시작해서 true로 구매를 시작한다고 시작해야 의미있는 변화가 생기므로, 아래와 같이 진행하여
        // 결과값을 받는다.
        answer = dfs(prices, dp, 0, true);

        // 답을 리턴한다.
        return answer;
    }

    public int dfs(int[] prices, int[][] dp, int i, boolean isBuying) {
        // Edge case 1 out of bound 가 나타날 때
        if(i >= prices.length) {
            return 0;
        }

        // Edge case 2 이미 DP에 값이 저장 되어 있을 때
        if(isBuying) {
            // 만약 구매를 할 예정이라면 i번째 날에 구매 예정을 알리는 0번 인덱스가 값이 MIN VALUE가 아니면
            // 이미 여기에 최선의 값이 이미 캐싱되었다는 이야기 이므로, 아래 계산을 할 필요가 없어 그냥 
            // 캐싱된 값을 리턴
            if(dp[i][0] != Integer.MIN_VALUE) {
                return dp[i][0];
            }
        } else {
            // 만약 판매 예정이면 위와 같이 이미 판매예정을 알리는 1번 인덱스 값이 MIN VALUE가 아니면
            // 최선의 값이 캐싱 되어 있는 것이므로, 그냥 캐싱된 값을 리턴
            if(dp[i][1] != Integer.MIN_VALUE) {
                return dp[i][1];
            }
        }

        // 실제 계산 로직
        // 휴식을 한 날은 구매를 헀던 안했던 동일하므로, 공통으로 위에 값을 둔다.
        int cooldownCost = dfs(prices, dp, i + 1, isBuying);
        // 만약 구매 예정이라면
        if(isBuying) {
            // 구매 혹은 휴식 2개뿐이므로, 이를 값을 잡고 dp[i][0] 에 값을 추가 
            int buyCost = dfs(prices, dp, i + 1, !isBuying) - prices[i];
            dp[i][0] = Math.max(buyCost, cooldownCost);
        } else {
            // 판매 예정이라면 아래 값을 추가 판매하면 다음날은 아무것도 못하므로, 의사결정이 가능한 날은 +2 로 처리
            int sellCost = dfs(prices, dp, i + 2, !isBuying) + prices[i];
            dp[i][1] = Math.max(sellCost, cooldownCost);
        }

        // 2가지 케이스에 맞춰서 최대 값을 구해 결과를 구한다.
        return Math.max(dp[i][0], dp[i][1]);
    }

}
 
