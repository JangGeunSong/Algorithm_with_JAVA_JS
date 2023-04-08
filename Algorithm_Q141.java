import java.util.*;

public class Algorithm_Q141 {
    /*
     * 이 문제는 BFS를 통해 그래프를 순회하면서 완전히 똑같은 형태로 새로운 메모리 위에 복사를 해야 한다.
     * 복사한 노드와 방문을 이미 한 노드를 어떤 방식으로 저장하면서 논리가 진행 되어야 하는지 고민하며 풀이해 보아야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/clone-graph/
     * 풀이를 확인해보자.
     */

    public Node cloneGraph(Node node) {
        // BFS를 위한 queue 선언 및 생성
        Queue<Node> queue = new LinkedList<>();
        // 복사가 된 노드들에 대해서 저장하는 해시맵
        Map<Integer, Node> map = new HashMap<>();
        // 이웃으로 확인되어 이미 큐에 들어갔는지 체크하기 위한 해시셋
        Set<Node> nodeSet = new HashSet<>();
        // 리턴할 결과 노드 변수
        Node resultNode = null;
        // BFS를 돌면서 queue에서 꺼낸 현재 노드
        Node currNode = null;
        // map 안에 없어서 copy로 만든 신규 노드
        Node copyNode = null;
        // 이미 방문한 노드인지 여부를 체크 하기 위한 배열 (각 노드의 val은 유니크 함)
        boolean[] visited = new boolean[101];
        // 방문 여부를 모두 false로 초기화
        Arrays.fill(visited, false);

        // 종료 조건 : 만약 현재 node가 null 이면 null 상태로 리턴
        if(node == null) {
            return resultNode;
        }

        // null 아니면 결과 노드에 신규로 생성 진행
        resultNode = new Node(node.val);

        // 큐에 원본 노드 삽입
        queue.offer(node);
        // 맵에 현재 결과 노드 삽입
        map.put(node.val, resultNode);
        // 셋에 원본 노드 삽입
        nodeSet.add(node);

        // BFS 순회 시작
        while(!queue.isEmpty()) {
            currNode = queue.poll();

            // 노드 방문 처리
            visited[currNode.val] = true;

            if(map.get(currNode.val) == null) {
                // 현재 노드가 복사가 안되었다면 복사를 위해 생성 및 map에 삽입
                copyNode = new Node(currNode.val);
                map.put(copyNode.val, copyNode);
            }

            // 현재 방문한 노드의 이웃을 순회
            List<Node> neighborNodes = currNode.neighbors;

            for(Node neighbor : neighborNodes) {
                // 이웃들을 순회하면서
                if(visited[neighbor.val]) {
                    // 이미 방문 했다면 나와 이웃간에 무방향 간선이 연결되므로,
                    // 양쪽 이웃 리스트에 각각 서로를 등록 (복사한 노드로 해야함)
                    Node originalNode = map.get(neighbor.val);
                    originalNode.neighbors.add(copyNode);
                    copyNode.neighbors.add(originalNode);
                } else {
                    // 만약 hashset에 이미 이웃이 포함되어 있다면 현재 로직 실행 X
                    if(nodeSet.contains(neighbor)) {
                        continue;
                    } else {
                        // 그게 아니라면 큐에 이웃을 삽입하고 큐에 넣었다는 의미로 set에 이웃을 삽입
                        queue.offer(neighbor);
                        nodeSet.add(neighbor);
                    }
                }
            }
        }

        // 모든 순회가 끝난 결과를 리턴
        return resultNode;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
