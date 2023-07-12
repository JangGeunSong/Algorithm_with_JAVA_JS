import java.util.ArrayList;
import java.util.List;

public class Algorithm_Q195 {
    /*
     * DFS를 사용해서 어떤 노드가 terminal 인지, safe인지 찾는 문제이다. 풀이를 확인하며, 원리를 이해하도록 하자.
     * https://leetcode.com/problems/find-eventual-safe-states/
     * 풀이를 확인해보자.
     */

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 이미 방문한 노드인지 체크하는 변수
        boolean[] visited = new boolean[n];
        // 현재 safe 노드로 올라온 노드인지 여부를 확인
        boolean[] inStack = new boolean[n];
        List<Integer> answer = new ArrayList<>();

        // 모든 노드에 대해 safe 노드 여부를 dfs로 체크
        for(int i = 0; i < n; i++) {
            dfs(i, graph, visited, inStack);
        }

        for(int i = 0; i < n; i++) {
            // 만약 현재 노드가 stack 안에 들어가 있지 않다면, 이는 순환 노드가 아니므로, 이때 답으로 추가한다.
            if(!inStack[i]) {
                answer.add(i);
            }
        }

        return answer;
    }

    public boolean dfs(int node, int[][] graph, boolean[] visited, boolean[] inStack) {
        // 지금 방문한 노드가 경로상 포함된 곳이라면 순횐 돌게 되므로, true 처리
        if(inStack[node]) {
            return true;
        }

        if(visited[node]) {
            // 이미 방문한 노드라면 더 방문 못하도록 false 처리 (순환 돌게 됨)
            return false;
        }

        visited[node] = true;
        inStack[node] = true;

        for(int k : graph[node]) {
            if(dfs(k, graph, visited, inStack)) {
                return true;
            }
        }
        
        // 이제 현재 노드가 경로상에 존재하지 않으므로, false 처리
        inStack[node] = false;

        // 여기까지 왔다면, 순환을 계속 돌고 있게 되거나 혹은 여러번의 방문 후 terminal노드로는 못가는 것이므로, false
        return false;
    }
}
