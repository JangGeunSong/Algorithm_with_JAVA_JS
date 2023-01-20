import java.util.*;

public class Algorithm_Q88 {
    /*
     * 이 문제는 HashSet과 백트래킹을 사용하는 문제다.
     * 두 요소 모두 풀어본 적이 많으므로, 이를 적절히 활용하면 해결할 수 있다. 나의 경우 거의 정답에 근접했고, 약간의 도움을 받아 해결했다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/non-decreasing-subsequences/
     * 풀이를 확인해보자.
     */

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> answer = null;
        List<Integer> list = new ArrayList<>();
        // 순회를 돌면 겹치는 리스트가 존재할 수 있으므로, 중복을 없에는 데이터 구조인 HashSet을 사용
        Set<List<Integer>> set = new HashSet<>();

        findSubArrayLists(nums, list, set, 0);

        // 재귀를 돌고 난 후 answer에 set을 통해 arrayList를 생성
        answer = new ArrayList<>(set);

        return answer;
    }

    public void findSubArrayLists(int[] nums, List<Integer> list, Set<List<Integer>> set, int curr) {
        // 종료 조건 1 현재 index의 값이 배열의 끝 값보다 크거나 같을 때
        if(curr >= nums.length) {
            // 만약 종료 조건1 에 도달했을때, 만들어진 결과 list가 size가 1보다 크다면 문제 조건에 맞으므로
            if(list.size() > 1) {
                // hash set에 리스트를 담는다.
                // 이때 주의할 점은 중복되는 리스트가 존재할 수 있으므로, HashSet에 담는다.
                set.add(new ArrayList<>(list));
            }
            return;
        }

        // 만약 list의 사이즈가 0 이거나 list의 끝 값이 현재 nums 배열의 현재 요소보다 작거나 같으면
        if(list.size() == 0 || list.get(list.size() - 1) <= nums[curr]) {
            // 리스트에 요소를 삽입
            list.add(nums[curr]);
            // 재귀 실행
            findSubArrayLists(nums, list, set, curr + 1);
            // 방문을 하지 않은 처리를 위해 끝 요소를 삭제
            list.remove(list.size() - 1);
        }

        // 재귀를 실행하여 계산
        findSubArrayLists(nums, list, set, curr + 1);
    }
}
