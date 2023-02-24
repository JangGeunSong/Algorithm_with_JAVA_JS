import java.util.*;

public class Algorithm_Q110 {
    /*
     * 이 문제는 우선순위 큐를 사용해 풀이하는 문제이다.
     * 어려움 단계에 있는 문제로 생각해볼만한 부분들이 많다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimize-deviation-in-array/
     * 풀이를 확인해보자.
     */

    public int minimumDeviation(int[] nums) {
        // 짝수면 2로 나누고, 홀수면 2로 곱한다.
        // 홀수는 어떤 수가 와도 2배를 무조건 해야 하므로, 최대 숫자가 홀수면 최대값은 더이상 갱신 X
        // 짝수는 2로 나눌 수 있으므로, 최소값에 대해서는 짝수일 때에는 2로 나누어서 얻은 값과 비교 해야 함
        // 일단 짝수의 경우 해당 짝수가 최소값을 만들 수도 있으므로, 고민을 진행해본다.

        // 내림차순 우선순위 큐
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        // 우선 홀수에 대해서만 모두 2를 곱해서 전부 짝수일 때를 만들어 준다.
        // 여기서 최소값을 일단 먼저 만들어 준다.
        for(int num : nums) {
            if(num % 2 == 1) {
                num = num * 2;
            }
            priorityQueue.offer(num);
            minValue = Math.min(minValue, num);
        }

        // 해당 반복문에서는 최대값에 대해서만 바라본다.
        while(true) {
            // 우선 이 큐의 제일 상단에는 가장 큰 값이 있을 것이기 때문에 이를 빼준다.
            maxValue = priorityQueue.poll();
            // 편차는 최대값 - 최소값 이므로 계산한 후 최소값을 구한다.
            answer = Math.min(answer, maxValue - minValue);

            // 그리고 이 최대값이 홀수라면, 나머지 짝수들은 2로 나누어 봐야 이 홀수보다 클 수 없으므로 종료한다.
            if(maxValue % 2 == 1) {
                break;
            }

            // 해당 최대값이 짝수라면 2로 나누어서 큐에 넣고 (이 계산 이후 홀수 or 짝수 상관 없음)
            // 이게 최소값을 수 있으므로, 최소값 갱신을 한다.
            maxValue = maxValue / 2;
            priorityQueue.offer(maxValue);
            minValue = Math.min(minValue, maxValue);
        }

        // 이렇게 돌게된 반복문의 결과를 리턴한다.
        return answer;
    }
    
}