import java.util.*;

public class Algorithm_Q132 {
    /*
     * 이 문제는 DP를 통한 메모를 진행하여 답을 찾아야 하는 문제이다.
     * 문제의 풀이가 이해가지 않는 부분도 있으므로, 주의해서 보도록 하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-cost-for-tickets/
     * 풀이를 확인해보자.
     */

     public int mincostTickets(int[] days, int[] costs) {
        // 365일 정보를 담을 memo 배열 선언
        int[] memo = new int[366];
        // 실제 여행하는 날짜를 담은 set 선언
        Set<Integer> set = new HashSet<>();

        // 모든 메모에 방문 하지 않았을 때는 -1로 초기화
        Arrays.fill(memo, -1);

        // 여행하는 날을 set에 기록
        for(int day : days) {
            set.add(day);
        }

        // 1일차 부터 시작해서 dp를 진행
        return dp(1, set, memo, costs);
    }

    public int dp(int day, Set<Integer> set, int[] memo, int[] costs) {
        // 종료 조건 1 day 가 365일이 넘어갈 때, => 1년이 넘어갔으므로, pass
        if(day > 365) {
            return 0;
        }

        // 종료 조건 2 memo 가 -1 이 아닐 때, -1이 아니라는 것은 현재 날짜에 구매한 기록이 있다는 것으로 해당 정보를 리턴
        if(memo[day] != -1) {
            return memo[day];
        }

        int answer = 0;

        // 만약 day가 여행한 날일 때
        if(set.contains(day)) {
            // 1일치, 7일치, 30일치 표를 각각 구매한 후 DP를 돌았을 때, 가장 적은 비용이 들어가는 케이스를 answer에 저장
            answer = Math.min(dp(day + 1, set, memo, costs) + costs[0], dp(day + 7, set, memo, costs) + costs[1]);
            answer = Math.min(answer, dp(day + 30, set, memo, costs) + costs[2]);
        } else {
            // 여행하는 날이 아닐 때, answer값은 하루를 더하고 얻게 되는 DP 값을 받도록 처리
            answer = dp(day + 1, set, memo, costs);
        }

        // day에는 answer 값을 담고
        memo[day] = answer;

        // answer를 리턴
        return answer;
    }
}
