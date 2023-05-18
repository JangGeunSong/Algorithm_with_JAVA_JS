import java.util.*;

public class Algorithm_Q166 {
    /*
     * 이 문제는 얼핏 보면 BFS로 풀어주면 될것 같으나, 조건이 해당 방식으로 풀면 너무 오래 걸리기 때문에 그래프 이론을 활용해 푸는 문제다.
     * 방향 그래프의 indegree, outdegree를 이해하면 곧바로 풀 수 있으니 참고하자.
     * https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
     * 풀이를 확인해보자.
     */

    // https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=minichuuuuu&logNo=220808115381 해당 링크를 통해 아래 구현의 이론을 먼저 확인하자.
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // 처음 BFS로 풀이했을 때는 무조건 0번부터 시작해야 하고, 랜덤하게 뽑아야 한다면
        // 너무 많은 시행횟수를 거쳐야만 한다.
        // 따라서 이 문제는 그래프 이론의 방향성에 이름을 붙인 indegree와 outdegree를 활용해 풀어본다.
        List<Integer> answer = new ArrayList<>();
        // indegree가 최소 1 이상 즉 자기에게 하나라도 정점이 도착할 수 있는지 여부를 체크하는 자료구조 시작은 모두 false
        boolean[] isDestinationOtherVertex = new boolean[n];

        // 모든 간선을 순회하면서
        for(List<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);

            // to 즉 목적지가 된 정점의 index는 무조건 indegree가 1 증가하므로, 이런 경우에는 true로 처리
            isDestinationOtherVertex[to] = true;
        }

        // 모든 정점을 순회 하면서
        for(int i = 0; i < n; i++) {
            // indegree가 0. 즉, 어떠한 정점도 자기를 목적지로 갖지 않는 정점일때 정답으로 추가한다.
            // 이는 해당 정점은 모든 정점을 도착하기 위해 꼭 포함 되어야 하는 존재들이기 때문이다.
            if(!isDestinationOtherVertex[i]) {
                answer.add(i);
            }
        }

        // 얻은 결과를 리턴한다.
        return answer;
    }
}
