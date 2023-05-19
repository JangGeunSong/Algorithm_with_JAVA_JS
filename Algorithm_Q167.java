import java.util.*;

public class Algorithm_Q167 {
    /*
     * 이 문제는 BFS를 통해 무방향 그래프가 있을 때 이들 노드가 정확히 2개의 그룹으로 갈라질 수 있는지 묻는 문제다.
     * 아래 링크와 같이 이전에도 한번 접했었던 문제 였는데, 다시 만난 유형이다. 잘 확인해 보자.
     * https://leetcode.com/problems/is-graph-bipartite/
     * 풀이를 확인해보자.
     */
    
    // https://leetcode.com/problems/possible-bipartition/
    // 해당 문제와 정확히 같은 형태의 문제다. 과거에 풀이 했으나 그때도 잘 못풀었던 문제였다.
    // 다시 이 유형의 문제가 찾아왔으니 눈여겨 보도록 하자.
    public boolean isBipartite(int[][] graph) {
        // 그래프의 갯수
        int n = graph.length;
        // 각 노드를 그룹으로 치고 그룹을 만들어 준다. 해당 index가 노드이며, 값이 그룹 번호다.
        int[] group = new int[n];
        
        // 모든 그래프를 순회하면서
        for(int i = 0; i < n; i++) {
            // 만약 현재 노드가 그룹의 값이 0이 아니라면 그냥 다음 노드로 진행
            if(group[i] != 0) {
                continue;
            }

            // 0이라면 1로 해당 노드의 그룹을 잡아둔 후 시작
            group[i] = 1;

            // 큐를 선언해서 BFS 즉 해당 노드 주변의 모든 노드를 순회
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);

            // BFS 시작
            while(!queue.isEmpty()) {
                // 지금 큐에서 뺀 노드를 받고
                int node = queue.poll();

                // 해당 노드가 갈 수 있는 모든 다른 노드들을 to라고 잡을 때
                for(int to : graph[node]) {
                    // 만약 현재 노드와 to 노드 사이에 그룹이 같다면 이분화 될 수 없으니 false로 리턴
                    // 왜? 간선이 연결되었다는 것은 서로 다른 그룹이어야 한다는 것을 의미하기 때문이다.
                    if(group[node] == group[to]) {
                        return false;
                    }
                    
                    // 지금 to 노드가 0 이라면 현재 노드 그룹과 다른 그룹이 되기 위해 현재 노드의 그룹의 음수값으로 인덱싱
                    if(group[to] == 0) {
                        group[to] = -group[node];
                        // 해당 to 노드를 큐에 삽입
                        queue.offer(to);
                    }
                }
            }
        }

        // 모든 노드를 그룹을 2개로 분리 가능하게 되었으므로, 현재에 도달해 있다. true로 리턴
        return true;
    }
}
