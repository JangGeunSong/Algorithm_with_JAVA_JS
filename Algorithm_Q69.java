import java.util.*;

public class Algorithm_Q69 {
    /*
     * 해당 문제는 간단한 그리디 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        // 이 문제를 해결하기 위해서는 조금만 더 넣으면 꽉 차는 가방을 우선순위로 먼저 두어서 채울 수 있도록
        // 정렬하는 과정이 필요하다.
        int answer = 0;
        int n = capacity.length;
        PriorityQueue<Integer> restCapacity = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            if(capacity[i] - rocks[i] == 0) {
                // 이미 가득 차 있는 가방이면 answer 에 1을 더한다
                answer += 1;
            } else {
                // 그렇지 않다면, 더 넣을 수 있는 돌의 개수를 구한 후 이를 restCapacity 우선순위 큐에 넣는다.
                int canHoldCapacity = capacity[i] - rocks[i];
                restCapacity.offer(canHoldCapacity);
            }
        }

        while(additionalRocks >= 0) {
            // peek할 내용이 전부 없어졌는지 체크
            if(restCapacity.peek() == null) {
                break;
            }
            int peek = restCapacity.peek();
            if(additionalRocks >= peek) {
                // 만약 더 넣을 수 있는 돌의 수가 현재 넣을 수 있는 공간량보다 많거나 같다면 꽉 채울 수 있는 가방수를
                // 하나 더 추가하고 더 넣을 수 있는 공간의 수 만큼 돌의 수를 뺸다
                restCapacity.poll();
                additionalRocks -= peek;
                answer += 1;
            } else {
                // 위 조건에 만족하지 않으면 반복문을 멈춘다.
                break;
            }
        }

        return answer;
    }
}
