import java.util.*;

public class Algorithm_Q90 {
    /*
     * 이 문제는 그래프와 DFS를 통해 원하는 조건을 찾아 풀이하는 문제이다.
     * 지문이 난해하기 때문에 풀이 코드에 주석을 상세하게 달아 두었다. 독해가 더 어려운 문제였다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-closest-node-to-given-two-nodes/
     * 풀이를 확인해보자.
     */

    // 나의 풀이 코드
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        // 해당 문제는 정답이 두 노드가 모두 도달 가능한 노드의 위치를 구하는 문제로
        // 여기에 조건은 다음과 같다.
        // 1. 두 노드가 모두 도달 가능할 것
        // 2. 각 노드가 해당 노드로 도달할 때, 두 노드가 가야하는 거리값 중 최대값을 구할것
        // 3. 해당 최대값을 저장한 후 다른 노드를 확인할 때, 이 노드로 도착하기 위해 처음 두 노드가 가야하는
        //    거리의 최대값을 구하고, 이전에 저장한 값과 비교할것
        // 4. 이렇게 계산했을 때, 도달 가능한 노드 중 두 노드 모두가 가장 짧게 간선을 건너면 되는 노드의 인덱스 구하기
        // 5. 만약 어떠한 노드도 위 조건을 만족하지 못한다면 그냥 -1을 리턴하기
        int answer = Integer.MAX_VALUE;
        // 경로의 최소값을 얻는 변수 => 새로운 min 값을 찾으면 갱신됨
        int pathLength = Integer.MAX_VALUE;
        // 이전 경로의 최소값을 갖는 변수 => 갱신된 min값을 감지하기 위한 변수
        int prevPath = Integer.MAX_VALUE;
        int n = edges.length;
        // 2개의 노드가 해당 index의 노드에 도달할때 까지 걸리는 거리의 수를 담는 배열
        int[] node1Route = new int[n];
        int[] node2Route = new int[n];

        // cycle을 돌 수 있으므로, 방문 여부를 체크하는 배열
        boolean[] visited = new boolean[n];

        // 방문하지 않은 노드의 index 안에는 -1로 채워둔다
        Arrays.fill(node1Route, -1);
        Arrays.fill(node2Route, -1);

        // 방문 하지 않았다는 의미로 false로 채운다
        Arrays.fill(visited, false);
        // DFS 순회 node 1로 진행
        findRoute(edges, 1, node1, node1Route, visited);
        
        // 방문 하지 않았다는 의미로 false로 채운다
        Arrays.fill(visited, false);
        // DFS 순회 node 2로 진행
        findRoute(edges, 1, node2, node2Route, visited);

        // 반복문을 돌면서 위 조건에 맞는 경우를 체크한다.
        for(int i = 0; i < n; i++) {
            // node route 정보가 모두 -1이 아니라면 이 index의 노드는 두 노드 모두 도달 가능한 노드다
            if(node1Route[i] != -1 && node2Route[i] != -1) {
                // 이때 해당 노드로 도달하기 까지 걸리는 간선 길이를 구하고 이 값의 최대값과 현재 최대간선 길이를
                // 비교해서 그중 가장 작은 값을 얻는다.
                pathLength = Math.min(pathLength, Math.max(node1Route[i], node2Route[i]));
                if(prevPath != pathLength) {
                    // 최소값이 갱신되었다면, 이 노드의 인덱스가 정답이 될 예정이므로 answer에 담아두고
                    // 최소값 백업을 현재 값으로 한다.
                    prevPath = pathLength;
                    answer = i;
                }
            }
        }

        // 만약 초기화한 값으로 answer가 그대로 유지 된다면 정답이 없는 것이므로 -1로 바꿔준다.
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    // DFS 순회
    public void findRoute(int[] edges, int curr, int node, int[] routeInfo, boolean[] visited) {
        // 종료조건 1. node가 -1 이어서 앞의 노드에서 더 이상 갈 곳이 없다
        // 종료조건 2. node가 이미 방문한 상태다
        if(node == -1 || visited[node]) {
            return;
        }

        // 위 종료 조건이 아니라면 방문 표시 하고 현재 route 정보는 여태까지 지나온 간선의 수 만큼 저장한 curr를 담는다.
        visited[node] = true;
        routeInfo[node] = curr;

        // DFS 순회 진행 curr + 1 하고 node는 해당 node의 간선 정보를 읽어서 다음 노드를 지정해 진행
        findRoute(edges, curr + 1, edges[node], routeInfo, visited);

        return;
    }
}
