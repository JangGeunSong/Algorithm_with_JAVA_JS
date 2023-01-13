import java.util.*;

public class Algorithm_Q82 {
    /*
     * 그래프의 순회를 통한 결과를 도출하는 문제이다.
     * 트리 문제라고는 하지만, 시작이 root 가 아니어도 되는 상황이라면, 이는 그래프 문제로 보고 접근하는 것이 좋다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
     * 풀이를 확인해보자.
     */

    // 풀이 코드 => https://leetcode.com/problems/longest-path-with-different-adjacent-characters/solutions/3043288/super-easy-explanation-dfs-o-n-java/?orderBy=hot
    // 위 경로의 풀이를 참고
    public int longestPath(int[] parent, String s) {
        int[] answer = new int[1];
        List<List<Integer>> graph = new ArrayList<>();

        // 만약 문자열의 길이가 1이면 그냥 경로의 길이는 1이다.
        if(s.length() == 1) {
            return 1;
        }

        // 그래프 초기화 진행
        for(int i = 0; i < s.length(); i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }

        for(int i = 1; i < s.length(); i++) {
            List<Integer> list = graph.get(parent[i]);
            list.add(i);
            graph.set(parent[i], list);
        }

        findLongestPath(graph, 0, s, answer);

        return answer[0];
    }

    public int findLongestPath(List<List<Integer>> graph, int curr, String s, int[] answer) {
        // 현재 노드에서 얻는 2개의 패스를 구하기 위해 선언
        int path1 = 0;
        int path2 = 0;

        List<Integer> list = graph.get(curr);

        // 현재 node의 인접 정보를 순회
        for(int i = 0; i < list.size(); i++) {
            // 일단 인접 그래프 방문해 얻는 최대 길이를 받는다
            int result = findLongestPath(graph, list.get(i), s, answer);
            // 만약 현재 노드와 인접 노드가 같은 문자라면 그냥 pass
            if(s.charAt(curr) == s.charAt(list.get(i))) {
                continue;
            }

            // 1번 경로 2번 경로의 값을 구한다.
            if(result > path1) {
                path2 = path1;
                path1 = result;
            } else if(result > path2) {
                path2 = result;
            }
        }

        // 최종 결과는 현재 최대값과 1 + 1번경로 + 2번 경로의 결과다.
        answer[0] = Math.max(answer[0], 1 + path1 + path2);

        // 이 반복의 결과값은 제일 긴 값이 분명한 1번 패스 + 1로 보낸다.
        return 1 + path1;
    }
}
