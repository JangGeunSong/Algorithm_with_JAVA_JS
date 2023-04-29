import java.util.*;

public class Algorithm_Q154 {
    /*
     * 이 문제는 union find를 통해 풀이해야 하는 문제다.
     * 문제 자체만 읽었을 때에는 DFS를 통해 풀어야 할것 처럼 보였으나 실제 풀이가 많이 다른 케이스였다.
     * 이런 케이스에 대해서 눈여겨 보도록 하자.
     * https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
     * 풀이를 확인해보자.
     */

    // 각 그룹의 리더를 담는 배열
    // https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/solutions/1192227/java-clean-union-find-solution-with-detailed-comments/
    // 위 경로 참고
    private int[] parents;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        parents = new int[n];
        int m = queries.length;
        boolean[] answer = new boolean[m];

        // 모든 그룹의 리더는 우선 자기 자신으로 초기화
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // 쿼리들을 정렬하는데, 이때 정렬 기준은 제한 거리값이다. (오름차순 정렬)
        int[][] sortedQueries = new int[m][4];
        for(int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, (a, b) -> a[2] - b[2]);

        // 간선 정보 역시 두 간선 사이의 거리에 대해 오름차순 정렬한다.
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        int idx = 0;

        // 모든 쿼리를 돌면서
        for(int i = 0; i < m; i++) {
            int[] q = sortedQueries[i];
            int w = q[2];

            // 현재 간선이 제한 길이값에 초과되지 않는다면 두 노드를 하나의 그룹으로 묶는다.
            while(idx < edgeList.length && edgeList[idx][2] < w) {
                int[] e = edgeList[idx++];
                int u = e[0];
                int v = e[1];
                union(u, v);
            }

            // 각 쿼리에 대해 데이터를 union 해서 찾았으므로 이게 해당 쿼리의 id가 4번째 원소이므로
            // 이를 받고 두 노드 u v 에 대해서 받아준다.
            int uQuery = q[0];
            int vQuery = q[1];
            int id = q[3];

            // 해당 id에서 두 노드가 같은 그룹에 속해 있다면 true로 반환하고 (길이 제한에 걸리지 않아 그룹으로 묶인 것이기 때문에 답이 되므로)
            // 아니라면 false 처리한다.
            answer[id] = (find(uQuery) == find(vQuery));
        }

        // 얻은 결과를 리턴한다.
        return answer;
    }

    // 두 숫자를 하나의 그룹으로 합치는 함수
    // 각 숫자의 그룹 리더를 찾아서 parent에 담고
    // u의 그룹의 리더를 v의 그룹의 리더로 둔다.
    public void union(int u, int v) {
        int uParent = find(u);
        int vParent = find(v);
        parents[uParent] = vParent;
    }
    
    // 주어진 u 에 대해 어느 그룹에 속하는지 찾는 함수
    public int find(int u) {
        // 만약 u의 그룹 리더 (parent[u]의 값)이 본인이 아니라면
        // 해당 값 즉 u의 그룹 리더에 그룹 리더를 찾아가서 최종적으로 진짜 그룹 리더가 본인인게 나오면 그 값을 리턴한다.
        while(u != parents[u]) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }

    // 잘못된 풀이 -> DFS를 사용해 풀이 시도한 코드
    public boolean[] distanceLimitedPathsExist_useDFS(int n, int[][] edgeList, int[][] queries) {
        // Map을 구성하여 그래프 데이터를 처리한다.
        // graph 안에는 노드 번호를 key로 해서 value에는 해당 노드가 가지는 이웃의 노드 번호와 거리를 저장할 수 있게 한다.
        // 이때 해당 거리 정보 map에는 key가 이웃 간선 정보 value는 거리로 잡는다.
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        // 만약 이웃 노드가 많아서 여러 방법으로 목적지에 도달할 수 있다면 이때는 방문 여부를 체크하면서 진행해야 한다.
        // 따라서 visited를 하나 잡아두고 기본값이 false 이므로, false면 미방문 상태, true면 방문 상태로 처리해 진행한다.
        boolean[] visited = new boolean[n];
        // 노드까지 도달할 때 존재하는 모든 path에 대해 저장하고 있는 heap -> 크기 역순
        PriorityQueue<Integer> pathHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 각 쿼리별로 결과를 true / false로 보내야 하므로 answer 선언
        boolean[] answer = new boolean[queries.length];

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }

        for(int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            // edgeList의 경우 from to가 명확한 방향 그래프이기 때문에 
            // 상황에 맞춰 노드번호로 거리를 입력한다.
            Map<Integer, Integer> nodeA = graph.get(u);
            nodeA.put(v, edge[2]);
            graph.put(u, nodeA);
        }

        for(int i = 0; i < queries.length; i++) {
            Arrays.fill(visited, false);
            pathHeap.clear();
            // 쿼리 배열의 경우 0 : 시작노드, 1 : 종점노드, 2 : 제한거리 이므로 초반에 방문한 노드를 먼저 처리하고
            int currNode = queries[i][0];
            int destNode = queries[i][1];
            int limitDist = queries[i][2];
            // BFS or DFS를 돌면서 진행한다.
            dfs(graph, visited, answer, currNode, destNode, pathHeap, limitDist, i);
        }

        return answer;
    }

    public void dfs(Map<Integer, Map<Integer, Integer>> graph, boolean[] visited, boolean[] answer, int currNode, int destNode, PriorityQueue<Integer> pathHeap, int limitDist, int ansIndex) {
        // 종료조건 목적지 노드에 도착 했을때 -> 거리 제한조건과 일치하면 true로 처리해주고 아니면 그냥 넘어감
        if(currNode == destNode) {
            // 만약 힙에 아무것도 없다면 경로 자체를 저장 안한것이기 때문에 아무것도 하지 않고 종료한다.
            if(pathHeap.isEmpty()) {
                return;
            }
            // 힙의 peek를 확인하면 그게 지금까지 저장된 거리 중 최대 값인데, 여기서 그 값이 limit 보다 작다면
            // 나머지 모든 경로가 다 조건에 만족하는 것이기 때문에 true로 받고 아니면 넘어간다.
            if(pathHeap.peek() < limitDist) {
                answer[ansIndex] = true;
            }
            return;
        }
        // 지금 노드와 이 노드의 key들 즉 이웃 노드 정보를 받아서 아래 반복문을 진행
        Map<Integer, Integer> node = graph.get(currNode);
        Iterator<Integer> itr = node.keySet().iterator();
        visited[currNode] = true;
        while(itr.hasNext()) {
            // 다음 노드를 받고
            int next = itr.next();
            if(!visited[next]) {
                // 현재 경로를 heap에 삽입한다.
                pathHeap.offer(node.get(next));
                // 여기를 아직 방문 안헀다면 현재거리에서 다음 노드까지 거리를 더하고 dfs를 진행
                dfs(graph, visited, answer, next, destNode, pathHeap, limitDist, ansIndex);
                // dfs를 돌면서 방문 처리를 했을것이기 때문에 next 노드는 방문 하지 않은것으로 처리
                visited[next] = false;
            }
        }
    }
}
