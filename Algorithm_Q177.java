import java.util.*;

public class Algorithm_Q177 {
    /*
     * 이 문제는 BFS를 통해 풀이하는 문제이다.
     * 핵심은 현재 직원에게 정보가 도달할 때 걸리는 시간은 가장 최 상단에서부터 걸렸던 시간들의 누적합으로 생각해야 한다는 점이다.
     * https://leetcode.com/problems/time-needed-to-inform-all-employees/
     * 풀이를 확인해보자.
     */

    int answer = Integer.MIN_VALUE;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> adjList = new ArrayList<>();

        // edge case 만약 직원이 혼자라면 어떠한 시간도 걸리지 않으므로, 0으로 리턴
        if(n == 1) {
            return 0;
        }

        // 2차원 배열을 통해 각 직원의 부하 직원 리스트를 만들어 준다.
        for(int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            adjList.add(list);
        }

        for(int i = 0; i < n; i++) {
            if(manager[i] != -1) {
                adjList.get(manager[i]).add(i);
            }
        }

        // 큐에서 pair 는 지금 직원 번호와 이 직원이 사장으로 부터 자기까지 왔을 때 총 걸린 시간으로 만든다.
        // 즉 이렇게 만든 value의 경우 최종적으로 마지막 직원에 도달 했을 때, 사장으로 부터 말단인 자기까지
        // 전달된 최종 걸린 시간과 같아지므로, 이때 최대값을 답으로 보면 된다.
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(headID, 0));

        // BFS 진행
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> employeePair = queue.poll();

            int parent = employeePair.getKey();
            int time = employeePair.getValue();

            // 현재 직원의 부하 직원들을 순회 하면서 
            // 현재 직원의 전파시간 + 현재 직원의 상사의 사장으로 부터 자기까지의 전파 시간
            // 을 더한다.
            for(int adjacent : adjList.get(parent)) {
                queue.offer(new Pair<>(adjacent, time + informTime[parent]));
                // 현재 시간과 정답 사이에 최대값을 받는다.
                answer = Math.max(answer, time + informTime[parent]);
            }
        }

        // 정답을 리턴
        return answer;
    }

    public class Pair<T1, T2> {
        private int x;
        private int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getKey(){
            return x;
        }

        public int getValue(){
            return y;
        }
    }
    
}
