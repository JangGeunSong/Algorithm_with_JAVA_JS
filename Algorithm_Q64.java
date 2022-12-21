import java.util.*;

public class Algorithm_Q64 {
    /*
     * 해당 문제는 그래프를 활용한 문제이다. 그래프 유형 문제는 익숙치 않으므로 잘 확인해보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/possible-bipartition/
     * 풀이를 확인해보자.
     */
    

    // BFS 접근 방법
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] group = new int[n];
        int a = 0;
        int b = 0;

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 싫어하는 사람들의 연결고리를 간선으로 정보 저장
        for(int i = 0; i < dislikes.length; i++) {
            a = dislikes[i][0] - 1;
            b = dislikes[i][1] - 1;

            // 양방향으로 간선을 정보가 들어가기 때문에 만약 나를 싫어하는 사람이 많아서 내가 양쪽 그룹에
            // 모두 갈 수 없다면 내 간선 정보 안에 나 자신의 정보가 있을 것이다.
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // queue를 순회하며 그룹을 형성
        for(int i = 0; i < n; i++) {
            if(group[i] != 0) {
                continue;
            }

            group[i] = 1;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            while(!queue.isEmpty()) {
                int person = queue.poll();

                for(int edge : graph.get(person)) {
                    if(group[edge] == group[person]) {
                        // 만약 내가 싫어하는 사람이 나와 같은 그룹에 있을 수 밖에 없다면 false 처리한다
                        return false;
                    }
                    if(group[edge] == 0) {
                        // 싫어하는 사람은 다른 그룹으로 넣기 위해 음수 처리
                        group[edge] = -group[person];
                        queue.add(edge);
                    }
                }
            }
        }

        return true;
    }
}
