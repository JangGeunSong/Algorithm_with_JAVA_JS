import java.util.*;

public class Algorithm_Q145 {
    /*
     * 이 문제는 2차원 DP를 활용한 문제다.
     * 구현을 위해서는 어제 보았던 문제보다 더 어려운 구현이 필요해진다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/
     * 풀이를 확인해보자.
     */

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        // 2차원 DP 저장소 선언 및 초기화
        int[][] dp = new int[piles.size() + 1][k + 1];

        // 맨 왼쪽 DP는 0번 pile은 없으므로, 모두 0으로 초기화
        Arrays.fill(dp[0], 0);

        // 나머지 모든 파트에 0번째 코인 선택은 답이 0 이므로 전부 0 으로 초기화
        for(int i = 1; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        // 모든 더미를 순회 진행
        for(int i = 1; i <= piles.size(); i++) {
            // 1번째 동전부터 k 번째 동전 선택까지 각 더미에서 체크
            for(int j = 1; j <= k; j++) {
                // 현재 선택한 동전의 합산을 저장하는 변수 선언
                int cur = 0;
                // 동전 뽑기를 몇개나 할지 반복문 진행 만약 현재 더미에 크기가 j 즉 처음부터 j 개까지 선택이 되는지
                // min 값으로 체크해서 더미수가 그거보다 작으면 해당 더미 수 만큼만 가져갈 수 있게 처리
                for(int idx = 0; idx < Math.min(piles.get(i - 1).size(), j); idx++) {
                    // 현재 값 = 현재 값 + 해당 더미에 idx 번째 동전 선택한 결과값
                    cur += piles.get(i - 1).get(idx);
                    // dp[현재 더미][j번째 동전까지 선택했을 때 최대 값]
                    // = dp[i][j](0) vs dp[이전 더미에서][j번째 동전중 현재 index 만큼 먼저 선택 했을때] 최대값 저장 데이터
                    // 그중 큰 값을 일단 dp[현재 더미][j번째 동전까지 선택했을 때 최대 값] 에 저장
                    dp[i][j] = Math.max(dp[i][j], cur + dp[i - 1][j - idx - 1]);
                }
                // 이 dp[현재 더미][j번째 동전까지 선택했을 때 최대 값] vs dp[이전 더미에서][j 번째동전 까지 선택했을때] 값
                // 여기서 최대값을 dp[현재 더미][j번째 동전까지 선택했을 때 최대 값]에 다시 담는다.
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        // 이 과정의 결과 최후로 얻게된
        // dp[pile 전체를 돌았을 때][k 개의 동전 선택시] 최대 값을 리턴한다.
        return dp[piles.size()][k];
    }
}
