import java.util.*;

public class Algorithm_Q130 {
    /*
     * 이 문제는 무방향 그래프 탐색 문제이다.
     * 풀이를 떠올리기 쉽지 않은 구조로 답을 구하는 논리를 잘 확인해보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
     * 풀이를 확인해보자.
     */

    // https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/solutions/3337487/python-java-c-simple-solution-easy-to-understand/
    // 위 풀이를 참고
    class Solution_for_best_practice {

        // 그래프 구성
        public List<List<Integer>> graph; 
    
        public long countPairs(int n, int[][] edges) {
            // 그래프 생성 진행
            createGraph(n, edges);
            // 방문 여부 체크 및 현재까지 방문 한 노드의 수 체크
            boolean[] visited = new boolean[n];
            int numberOfVisitedNodes = 0;
            long answer = 0L;
    
            for(int i = 0; i < n; ++i) {
                if(!visited[i]) {
                    // 아직 해당 노드에 방문 하지 않았다면 이 노드와 그룹으로 연결된 노드를 모두 구하고
                    int numberOfCurrentGroupNodes = dfs_traverse_nodes(i, visited);
                    // 구한 노드와 현재까지 방문한 노드의 수를 곱해서 정답에 추가
                    answer += (long) numberOfCurrentGroupNodes * numberOfVisitedNodes;
                    // 이후 방문한 노드의 수에 해당 그룹에 들어간 노드의 수를 추가
                    numberOfVisitedNodes += numberOfCurrentGroupNodes;
                }
            }
    
            // 답을 리턴한다.
            return answer;
        }
    
        // 주어진 노드에서 DFS를 통해 방문 가능한 모든 노드의 수를 구하는 함수
        public int dfs_traverse_nodes(int node, boolean[] visited) {
            visited[node] = true;
            int connectedNumber = 1;
    
            for(int neighbor : graph.get(node)) {
                if(!visited[neighbor]) {
                    connectedNumber += dfs_traverse_nodes(neighbor, visited);
                }
            }
    
            return connectedNumber;
        }
    
        // 그래프 생성 함수
        public void createGraph(int n, int[][] edges) {
            graph = new ArrayList<>();
    
            for(int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
    
            for(int[] edge : edges) {
                List<Integer> a = graph.get(edge[0]);
                List<Integer> b = graph.get(edge[1]);
                a.add(edge[1]);
                b.add(edge[0]);
                graph.set(edge[0], a);
                graph.set(edge[1], b);
            }
    
            return;
        }
    }
}
