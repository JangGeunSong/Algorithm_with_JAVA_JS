import java.util.*;

public class Algorithm_Q102 {
    /*
     * 이 문제는 그래프와 BFS를 통해 풀이해야 하는 문제이다.
     * BFS는 queue를 사용하여 현재 node에서 도달 할 수 있는 모든 경우를 queue에 쌓고 다음으로 넘어가는 형태의 탐색 방법이다.
     * 이 문제는 이 BFS와 그래프의 간선 처리를 응용하여 푸는 문제로, 지문 자체가 이해하기 어렵다.
     * 관련하여 문제 풀이를 함께 확인하도록 하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/shortest-path-with-alternating-colors/
     * 풀이를 확인해보자.
     */

    // 풀이 코드 (https://www.youtube.com/watch?v=bP-HEu8ghDk) 해당 video 참고
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // both = 0, red = 1, blue = -1 으로 색인을 처리하고 계산
        int[] answer = new int[n];
        // 각 node 에 대해 간선의 색인을 갖고 있는 그래프를 생성
        int[][] edgePanel = new int[n][n];
        
        int start = 0;
        int end = 0;
        int length = 0;

        // 정답은 최소 길이 반환이므로, 모든 값을 우선 정수 최대값으로 초기화
        Arrays.fill(answer, Integer.MAX_VALUE);
        
        // 만약 어떠한 경로도 없을 경우 원하는 int 값으로 사전에 정의해서 해당 값을 모두 삽입해 둔다.
        for(int i = 0; i < n; i++) {
            Arrays.fill(edgePanel[i], 5);
        }

        // 간선 색인 그래프에 데이터를 삽입
        for(int i = 0; i < redEdges.length; i++) {
            start = redEdges[i][0];
            end = redEdges[i][1];
            edgePanel[start][end] = 1;
        }

        for(int i = 0; i < blueEdges.length; i++) {
            start = blueEdges[i][0];
            end = blueEdges[i][1];
            if(edgePanel[start][end] != 5) {
                edgePanel[start][end] = 0;
            } else {
                edgePanel[start][end] = -1;
            }
        }

        // 큐를 활용하여 BFS를 진행
        Queue<int[]> queue = new LinkedList<>();
        // 0번 index => 현재 node 번호, 1번 index => 해당 노드에서 간선 색깔
        // 0번에서 시작할때는 2가지 케이스가 있다.
        // 1. 빨간 간선으로 타고 갈 수 있는 경우
        // 2. 파란 간선으로 타고 갈 수 있는 경우
        // 간선 정보는 그렇게 갈 수 있는 정보만 주는 것이고, 우리는 일단 이 간선으로 간다는 정보를
        // 큐에 담는 것으로 이해하면 된다.
        queue.offer(new int[] {0, 1});
        queue.offer(new int[] {0, -1});
        // 0번 index는 시작이 0번 부터 임을 문제에서 적시 하였으므로, 0 으로 체크
        answer[0] = 0;

        // 방문 여부를 체크하기 위한 hashset 생성
        HashSet<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            // 현재 단계를 queue의 현재 size로 체크 하고 있고 이를 바탕으로 for loop를 돌기 때문에
            // 현재 단계의 이동 거리를 1 더해도 된다.
            length += 1;
            // 현재 큐의 사이즈 만큼 for loop를 돌면서 도달 가능할 때 처리를 진행
            for(int i = 0; i < size; i++) {
                int[] curNode = queue.poll();
                int node = curNode[0];
                int color = curNode[1];
                int opposite = -color;

                // 만약 그래프 안에서 모든 색을 다 갈 수 있다거나, 현재 색깔의 반대 색깔이라면
                // 이는 방문 가능한 것이기 때문에 visited 체크를 진행한다.
                // 이때 visited hash set 에 삽입이 안된다면, queue에 이동한 것으로 삽입 하면 안된다
                // BFS의 원리에 의해서... 따라서 이때는 continue를 한다.
                for(int j = 1; j < n; j++) {
                    if(edgePanel[node][j] == 0 || edgePanel[node][j] == opposite) {
                        if(!visited.add(j + "" + opposite)) {
                            continue;
                        } else {
                            // 방문을 하게 되었으면 (set에 없어서 add 된 경우)
                            // 큐에 현재 노드 번호와 반대 색깔이었던 간선 정보를 넣고
                            // 현재 길이와 현재 이 node 도달 거리의 최소 정보로 알고 있는 값을 
                            // 비교해서 최소값을 얻는다.
                            queue.offer(new int[] {j, opposite});
                            answer[j] = Math.min(answer[j], length);
                        }
                    }
                }
            }
        }

        // 모든 검토가 끝났을 때 만약 INTEGER 최대값이 아직도 남아 있다면, 이는 방문 자체를 못 한것이기 때문에
        // -1 로 값을 설정한다/
        for(int i = 0; i < n; i++) {
            if(answer[i] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
        }

        return answer;
    }
}
