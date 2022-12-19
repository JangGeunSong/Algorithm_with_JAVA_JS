import java.util.*;

public class Algorithm_Q62 {
    /*
     * 해당 문제는 그래프를 활용한 문제이다. 난이도는 easy 이지만 구현 방법을 모르면 어렵게 느껴질 수 있는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-if-path-exists-in-graph/
     * 풀이를 확인해보자.
     */

     public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 그래프 순회 문제이다.
        // 어떻게 그래프를 구현할지를 생각하는게 관건으로 구현만 된다면 풀이는 쉽게 할 수 있다.
        if(source == destination) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];
        boolean[] answer = new boolean[1];
        answer[0] = false;
        List<Integer> list = null;

        // n 개의 버텍스 생성
        for(int i = 0; i < n; i++) {
            list = new ArrayList<>();
            graph.put(i, list);
        }

        // 각 버택스의 간선들을 추가
        for(int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        // DFS를 통해 결과를 도출
        checkValid(graph, visited, source, destination, source, answer);

        return answer[0];
    }

    public void checkValid(Map<Integer, List<Integer>> graph, boolean[] visited, int source, int destination, int curr, boolean[] answer) {
        if(visited[curr] == true || answer[0] == true) {
            return;
        }

        // 방문하지 않은 버텍스 이므로, 방문 한 것으로 처리
        visited[curr] = true;

        List<Integer> list = graph.get(curr);
        for(int vertex : list) {
            // 만약 현재 버텍스가 목적지라면
            if(vertex == destination) {
                answer[0] = true;
                break;
            }
            // 만약 방문 안한 버텍스라면
            if(visited[vertex] == false) {
                checkValid(graph, visited, source, destination, vertex, answer);
            }
        }
    }
}
