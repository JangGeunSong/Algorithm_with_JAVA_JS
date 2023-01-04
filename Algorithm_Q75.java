import java.util.HashMap;
import java.util.Map;

public class Algorithm_Q75 {
    /*
     * 기초적인 그리디 문제이다. 그리디의 특성은 가장 이득이 되는 부분에 대한 패턴을 찾는데에서 시작한다.
     * 해당 문제는 그 패턴에 대한 생각 방식을 체크하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
     * 풀이를 확인해보자.
     */

    // 나의 풀이 => discussion의 힌트를 참고했다. 힌트의 내용은 주석을 살펴보자.
    class Solution {
        public int minimumRounds(int[] tasks) {
            Map<Integer, Integer> taskMap = new HashMap<>();
            int answer = 0;
            // HashMap에 task[i] 즉 level을 key로 그 갯수를 value 로 하는 값을 담는다.
            for(int i : tasks) {
                taskMap.put(i, taskMap.getOrDefault(i, 0) + 1);
            }
    
            // 모든 HashMap의 entry를 순회하면서 작업 완료 여부를 체크한다.
            for(Map.Entry<Integer, Integer> entry : taskMap.entrySet()) {
                int task = entry.getValue();
    
                // 만약 작업수가 1 이라면 무조건 -1을 반환한다.
                if(task == 1) {
                    return -1;
                }
    
                // 이외의 경우에는 다음을 생각하며 아래 계산의 결론으로 떨어진다.
                // 만약 2 => 2 한번 연산
                // 3 => 3 한번 연산
                // 4 => 2 + 2
                // 5 => 2 + 3
                // 6 => 3 + 3
                // 7 => 2 + 2 + 3
                // 8 => 2 + 3 + 3
                // 10 => 2 + 2 + 3 + 3
                // 11 => 2 + 3 + 3 + 3
                // 50 => 2 + (3 * 16)
                // 어떤수가 와도 2와 3의 합으로 쪼개보면 2를 소모하는 작업은 2개 이상 넘지 않는다.
                // 즉 1씩 증가할 때마다 2를 더하는 작업이 하나 더 생기고 이후 그걸 1을 더해서 
                // 2 + 3 + 3 으로 변화한다.
                // 이는 2가 들어가는 작업이 사실은 3이 들어가는 작업과 같은 것으로 생각하고 계산해도 되는것을 의미하므로
                // task + 2를 하면 2가 들어가는 작업에 1씩 추가해서 3이 들어가는 작업과 마찬가지로 계산할 수 있게 만든다
                answer += (task + 2) / 3;
            }
    
            // 최종적으로 얻은 값을 리턴한다.
            return answer;
        }
    }
}
