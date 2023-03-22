import java.util.*;

public class Algorithm_Q129 {
    /*
     * 이 문제는 그래프 탐색 문제이다.
     * 큰 문제 없이 풀 수 있는 문제 이지만, 예비군 훈련으로;; 집중력이 떨어져서 일단 풀이 먼저 올린다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/
     * 풀이를 확인해보자.
     */

    // 해당 풀이는 예시로서 주말에 이 문제는 다시 풀어 볼 예정이다.
    public int minScore(int n, int[][] roads) {
        // 예비군 훈련으로 BFS 모델을 사용 해야 한다는 점은 알고 있으나 그 부분에 대한 풀이를 떠올릴 수가 없어
        // 일단 이 풀이가 된다는 점을 먼저 넣어 둔다.
       Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];
            graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, w);
            graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, w);
        }
        
        int res = Integer.MAX_VALUE;
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
                int adj = entry.getKey(), score = entry.getValue();
                if (!visited.contains(adj)) {
                    queue.offer(adj);
                    visited.add(adj);
                }
                res = Math.min(res, score);
            }
        }
        
        return res;
    }
}
