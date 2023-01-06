import java.util.*;

public class Algorithm_Q77 {
    /*
     * 그리디 문제이다. 이 문제는 단순하게 정렬만 오름차순으로 하면 쉽게 풀린다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-ice-cream-bars/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int maxIceCream(int[] costs, int coins) {
        int answer = 0;
        // 같은 돈으로 가장 많이 사려면 저렴한 것 부터 먼저 사야 한다. 따라서 ASC 순으로 sorting 한다.
        Arrays.sort(costs);

        // 반복문을 돌면서 물건 가격이 coins 보다 작으면 구매하고 (저렴한 순으로 정렬했으니까) 남은 돈을 반환
        for(int i = 0; i < costs.length; i++) {
            if(costs[i] <= coins) {
                answer += 1;
                coins -= costs[i];
            }
        }

        // 최종적으로 구매한 갯수 리턴
        return answer;
    }
}
