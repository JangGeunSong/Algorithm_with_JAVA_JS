import java.util.*;

public class Algorithm_Q25 {
    /*
     * 해당 문제는 백트래킹 문제이다.
     * 백트래킹 문제는 모든 경우의 수를 전부 고려하는 알고리즘 상태공간을 트리로 나타낼 수 있을 때 적합한 방식이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/permutations/
     * 이 문제는 전형적인 DFS문제로 백트래킹 문제로 구분되어 진행된다.
     * 풀이를 확인해보자.
     */ 

     /*
     * 전형적인 DFS 문제로 재귀를 통해 구현하면 쉽게 해결된다.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        // 인접 리스트의 방문여부를 체크
        boolean[] visited = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();

        Arrays.fill(visited, false);

        // 재귀를 통해 문제를 해결한다.
        permutation(nums, visited, 0, list, answer);

        return answer;
    }

    public void permutation(int[] nums, boolean[] visited, int cnt, List<Integer> list, List<List<Integer>> answer) {
        if(cnt == nums.length) {
            // 파라미터로 받은 ArrayList의 경우 함수가 지속되면서 값이 계속 바뀌기 때문에 정답에 넣는
            // 값은 new를 해서 메모리에 새로 초기화 하여 저장해야 기존의 값이 남는다.
            // 해당 파트는 cnt 대신 list의 사이즈를 체크해서 값을 추가해도 상관없다.
            answer.add(new ArrayList<>(list));
        }

        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            permutation(nums, visited, cnt + 1, list, answer);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
