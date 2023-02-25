public class Algorithm_Q111 {
    /*
     * 이 문제는 DP 문제이다.
     * 나의 경우에는 해당 문제가 최소값과 최대값에 조건이 있는 greedy로 보고 문제를 풀었다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int maxProfit_my_solution(int[] prices) {
        // 해당 문제는 구매와 판매에 대한 값을 각각 greedy 해야 하는 문제로, 판매를 위한 시간은
        // 구매 시간보다 항상 뒤에 있어야 함을 고려해서 풀이하면 된다.
        int answer = Integer.MIN_VALUE;
        // 구매 시간과 구매 가격
        int buyTime = 0;
        int buyPrice = Integer.MAX_VALUE;
        // 판매 시간과 판매 가격
        int sellTime = 0;
        int sellPrice = Integer.MIN_VALUE;

        // 위 가격을 처음부터 순회 하면서
        for(int i = 0; i < prices.length; i++) {
            // 최소 구매 가격을 정하기 위해 현재 구매 가격보다 지금 가격이 싸다면
            // 최소 구매 가격 갱신 및 구매 시점을 i 로 처리
            if(prices[i] < buyPrice) {
                buyPrice = prices[i];
                buyTime = i;
                // 이때, 판매 시간이 구매 시간보다 작다면, 판매 시간은 무조건 구매 시간뒤어야 하므로,
                // 판매 시간 및 판매 가격을 초기화
                if(sellTime < buyTime) {
                    sellTime = 0;
                    sellPrice = Integer.MIN_VALUE;
                }
            }

            // 판매 가격 찾기, 현재 판매 가격이 지금 가격보다 작다면 판매 가격 갱신 및 판매 시간을 현재인 i로 처리
            if(sellPrice < prices[i]) {
                sellPrice = prices[i];
                sellTime = i;
            }

            // 이윤 계산 : 구매 시간 < 판매시간 일때
            if(buyTime < sellTime) {
                // 구매 가격 < 판매 가격
                // 위 케이i스에만 편차를 계산
                if(sellPrice > buyPrice) {
                    // 현재까지 저장한 answer와 지금 이 시점에 판매 이윤중 최대값을 결정
                    answer = Math.max(sellPrice - buyPrice, answer);
                }
            }
        }

        // 만약 순회를 마친 후 아직도 answer가 정수의 최소값으로 잡혀 있다면, 원하는 정답이 없는 것이므로
        // 0으로 처리 -> 답이 없다면 0을 리턴하라고 했기 때문에
        if(answer == Integer.MIN_VALUE) {
            answer = 0;
        }

        // 정답을 반환
        return answer;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solutions/3226882/day-56-o-n-time-and-o-1-space-easiest-beginner-friendly-sol/
    // 풀이 참고
    public int maxProfit_fast_solution(int[] prices) {
        int n = prices.length;
        int maximumProfit = 0, minStockVal = Integer.MAX_VALUE;
        int i = 0;
        while (i < n) {
            // 처음부터 끝까지 진행하면서 최소 구매 가격을 지속적으로 갱신 결정
            minStockVal = Math.min(minStockVal, prices[i]);
            // 현재 가격이 최소 구매 가격보다 크거나 같은 경우 아래와 같이 최대 이윤을 계산
            if (prices[i] >= minStockVal)
                maximumProfit = Math.max(maximumProfit, prices[i] - minStockVal);
            i++;
        }
        return maximumProfit;
    }
}
