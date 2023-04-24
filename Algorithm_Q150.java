import java.util.*;

public class Algorithm_Q150 {
    /*
     * 이 문제는 힙(우선순위 큐)를 활용한 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/last-stone-weight/
     * 풀이를 확인해보자.
     */
    public int lastStoneWeight(int[] stones) {
        // 우선순위 큐를 생성한다. 해당 큐는 오름차순 정렬이 되기 때문에 역순 정렬을 위한 선언을 진행
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;

        // 모든 돈을 queue에 삽입
        for(int stone : stones) {
            queue.offer(stone);
        }

        // 큐의 사이즈가 1보다 크다면 반복문을 계속해서 돈다.
        while(queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();

            // a와 b가 같다면 크기가 0이기 때문에 아무것도 하지 않는다. 하지만 두 돌의 무게가 다르다면
            // 이때는 두 돌의 차이를 큐에 삽입한다.
            if(a != b) {
                queue.offer(a - b);
            }
        }

        // 큐의 크기가 0 이라면 answer에는 0을 이 외에는 answer에 큐의 마지막 남은 값을 하나 뺴서 넣는다.
        if(queue.size() != 0) {
            answer = queue.poll();
        }

        // 이렇게 남은 돌의 무게가 답이므로 리턴한다.
        return answer;
    }
}
