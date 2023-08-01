import java.util.ArrayList;
import java.util.List;

public class Algorithm_Q202 {
    /*
     * 재귀를 사용한 백트래킹 문제이다. 모든 경우의 수를 찾는 방식을 확인해보자.
     * https://leetcode.com/problems/combinations/
     * 풀이를 확인해보자.
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        // 모든 경우를 찾기 위해 backtracking 처리함
        dfs(n, k, answer, 1, 0, temp);

        // 정답을 리턴
        return answer;
    }

    public void dfs(int n, int k, List<List<Integer>> list, int start, int num, List<Integer> temp) {
        // 종료조건 1 : 현재 숫자가 최대 숫자를 넘어서는 경우
        if(start == n + 1) {
            // 만약 지금 list의 사이즈가 K 라면 원하는 조합이므로, 정답에 추가함
            if(temp.size() == k) {
                list.add(new ArrayList<>(temp));
                return;
            } else {
                // 아니라면 종료
                return;
            }
        }
        
        // 만약 temp 리스트 즉 조합이 원하는 원소의 수로 맞춰 졌을 때
        if(temp.size() == k) {
            // 정답에 추가한다. 그리고 함수 종료
            list.add(new ArrayList<>(temp));
            return;
        }

        // temp 리스트에 start 숫자를 삽입
        temp.add(start);
        // DFS를 진행 이때 시작점 + 1, 조합은 해당 리스트의 길이로
        dfs(n, k, list, start + 1, temp.size(), temp);
        // DFS를 재 진행 할 떄는 해당 start 숫자가 없을 때를 기준으로 진행
        temp.remove(temp.size() - 1);
        dfs(n, k, list, start + 1, temp.size(), temp);

        return;
    }
}
