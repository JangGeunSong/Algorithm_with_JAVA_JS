import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Algorithm_Q196 {
    /*
     * 어제 풀었던 문제에 대해 BFS 로 풀이하는 방식이다. 이렇게 푸는 방법을 Kahn's Algorithm 이라고 부르는데, 
     * 방향 비싸이클 그래프(DAG, Directed Acyclic Graph) 문제 즉, 보통 작업간의 우선순위를 표현할 때 어떤거 부터 진행할지, 그리고 전부 할 수 있긴 한지를 체크하는 문제이다.
     * https://leetcode.com/problems/course-schedule/
     * 풀이를 확인해보자.
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];

        // 그래프 정의및 생성
        for(int i = 0; i < numCourses; i++) {
            List<Integer> list = new ArrayList<>();
            graph.put(i, list);
        }

        for(int[] edge : prerequisites) {
            int a  = edge[0];
            int b  = edge[1];

            List<Integer> list = graph.get(b);

            list.add(a);

            graph.put(b, list);
            // a를 목적지로 하는 노드가 하나추가 되었으므로, 해당 노드 a는 indegree를 1 추가한다.
            indegree[a] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        
        // i 번 노드를 대상으로 진입하는 노드가 존재하지 않는 경우 해당 노드는 큐에 삽입해서 출발지로 사용한다.
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 현재 방문한 노드의 수를 세는 변수 추가
        int cnt = 0;

        // 해당 큐의 순회에서 서로가 서로에 대해 방문을 해야 하는 상황이었다면, indegree가 전부 0이 아니기 때문에
        // 그냥 해당 반복문은 진행을 하지 않는다.
        while(!queue.isEmpty()) {
            int node = queue.poll();
            cnt += 1;
            for(int k : graph.get(node)) {
                // k번 노드를 향하고 있던 노드중 하나가 방문하면서 처리 되었기 때문에
                // 이제 k번 노드를 목표로 하는 노드의 수를 하나 줄일 수 있다.
                indegree[k] -= 1;
                // 이렇게 k번 노드가 어떠한 목표물도 되지 않은 시점에 큐에 삽입한다.
                // 즉, 사이클을 돌게 되는 순간 큐에는 삽입이 될 수가 없다.
                if(indegree[k] == 0) {
                    queue.offer(k);
                }
            }
        }

        // 방문했던 노드의 수가 전체 노드의 수와 같다면 true를 리턴
        if(cnt == numCourses) {
            return true;
        }

        // 아니라면 false를 리턴
        return false;
    }
}
