import java.util.*;

public class Algorithm_Q32 {
    /*
     * 해당 문제는 중복 조합을 활용한 풀이를 해야 하는 문제이다.
     * 어떻게 중복 조합을 표현하면 좋은지 내용을 파악해보자. 특히, 조합이나 순열은 재귀를 통한 DFS를 통해 쉽게 해결할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/combination-sum/
     * 풀이를 확인해보자.
     */  


    /*
     * 중복 조합을 활용한 풀이
     * list를 만들어서 return 하도록 처리하는데, 이때 중요한 것은 다시 첫 번째 index부터 진행하면 안된다.
     * 따라서, 현재 index를 파라미터로 넘겨서 거기서 부터 처리할 수 있도록 해야 문제의 조건에 맞게 풀 수 있다.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list = null;
        Arrays.sort(candidates);

        combination(candidates, target, 0, 0, list, answer);

        return answer;
    }

    // DFS를 활용한 조합 구하기의 구현
    public void combination(int[] candidates, int target, int num, int index, List<Integer> list, List<List<Integer>> answer) {
        if(num == target) {
            answer.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            if(target - num < candidates[i]) {
                continue;
            }
            if(list == null) {
                list = new ArrayList<>();
            }
            list.add(candidates[i]);
            combination(candidates, target, num + candidates[i], i, list, answer);
            list.remove(list.size() - 1);
        }
    }
}
