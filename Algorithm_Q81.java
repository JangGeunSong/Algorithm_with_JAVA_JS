import java.util.*;

public class Algorithm_Q81 {
    /*
     * 그래프의 순회를 통한 결과를 도출하는 문제이다.
     * 무 방향 그래프의 구현은 쉽지만, 이후의 답을 도출하기 위한 DFS 를 고민하는게 어려워 힌트들을 참고 해서 풀었다.
     * 핵심은 이 문제가 tree 라는 것이며, 따라서, 일단 0번 노드에서 출발한다 그리고 그 0번 노드는 path 에 포함이 안된다는 점을 기억하면 된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
     * 풀이를 확인해보자.
     */

    // 구현에 주의하며 살펴보자
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Map<Integer, List<Integer>> nodes = new HashMap<>();

        // 그래프의 노드 추가
        for(int i = 0; i < n; i++) {
            List<Integer> node = new ArrayList<>();

            nodes.put(i, node);
        }

        // 그래프의 간선 정보를 통해 서로를 연결 처리
        for(int i = 0; i < edges.length; i++) {
            List<Integer> node1 = nodes.get(edges[i][0]);
            List<Integer> node2 = nodes.get(edges[i][1]);

            node1.add(edges[i][1]);
            node2.add(edges[i][0]);

            nodes.put(edges[i][0], node1);
            nodes.put(edges[i][1], node2);
        }

        // dfs를 통해 필요한 결과값을 도출
        answer = dfs(0, nodes, hasApple, visited);

        return answer;
    }

    public int dfs(int curr, Map<Integer, List<Integer>> nodes, List<Boolean> hasApple, boolean[] visited) {
        
        visited[curr] = true;

        // 현재 노드 정보를 꺼낸다.
        List<Integer> node = nodes.get(curr);
        
        // 현재 node에서 dfs를 처리하면서 얻게된 결과값을 여기에 받는다.
        int res = 0;

        // 주변 간선 정보를 읽으면서 이동할 곳으로 이동한다.
        // 이때, 만약 방문한 적이 있다면, pass 한다.
        for(int i = 0; i < node.size(); i++) {
            int pos = node.get(i);
            if(visited[pos] == false) {
                res += dfs(pos, nodes, hasApple, visited);
            }
        }

        // 만약 위 반복문을 모두 거쳤다면, 둘 중 하나이다.
        // 1. 모든 인접 노드를 다 방문한 후의 현재
        // 2. 지금 노드가 그래프의 가장 끝의 노드
        // 이 상황에서 만약 지금 노드가 사과를 갖고 있거나 혹은 path가 0이 아니라면
        // 지금 노드는 방문해서 지나가는 path가 더해져야 할 필요가 있다. 따라서 2를 더한다.
        // 대신 0번 노드는 왔다 갔다 하는 경우가 없으므로, 사과가 있다고 해도 2를 더하지 않는다.
        if((hasApple.get(curr) || res != 0) && curr != 0) {
            res += 2;
        }

        return res;
    }
}
