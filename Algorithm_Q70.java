import java.util.*;

public class Algorithm_Q70 {
    /*
     * 해당 문제는 우선순위큐를 활용한 기본적인 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/remove-stones-to-minimize-the-total/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int minStoneSum(int[] piles, int k) {
        // PriorityQueue를 가장 큰 값이 먼저 갈 수 있도록 Collections.reverseOrder()를 처리
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        double peek = 0.0;
        double val = 0.0;
        Iterator<Double> itr = null;
        
        // PriorityQueue를 통해 가장 큰 값이 올라갈 수 있게 삽입
        for(int i = 0; i < piles.length; i++) {
            priorityQueue.offer(Double.valueOf(piles[i]));
        }

        // k번 동안 문제에서 요구한 연산을 진행
        for(int i = 0; i < k; i++) {
            // 가장 큰 값을 꺼내고
            peek = priorityQueue.poll();
            // 반으로 나눈 후 반올림
            val = Math.round(peek / 2);
            // 해당 값을 다시 priority queue에 삽입
            priorityQueue.offer(val);
        }

        // priority queue를 순회한다
        itr = priorityQueue.iterator();

        while(itr.hasNext()) {
            val = itr.next();
            // double을 int형으로 변환 => 5.0 => "5" => 5로 변환 진행
            answer = answer + Integer.parseInt(String.valueOf(Math.round(val)));
        }

        return answer;
    }
}
