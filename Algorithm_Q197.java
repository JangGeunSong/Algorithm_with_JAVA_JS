import java.util.HashMap;
import java.util.Map;

public class Algorithm_Q197 {
    /*
     * DFS를 통한 완전 탐색을 했을 떄 이 문제는 곧바로 시간 초과가 떨어지며 풀이를 실패 했다.
     * 다시 확인해보니 DP를 통해 푸는 문제였다. 해당 문제에서는 조건에 맞는 시점에 최대 길이를 미리 저장해서 얻어야 하며, 이를 구하는 방식이 아래 풀이에 있다.
     * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
     * 풀이를 확인해보자.
     */

    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int answer = 0;
        
        // DP를 이용한 풀이
        for(int i = 0; i < arr.length; i++) {
            int a = arr[i];
            // DP 현재 숫자 - 차이를 key로 해서 이미 dp로 하위 배열을 갖는 숫자가 존재하면
            // 그 값을 가져온 후 자신을 포함해서 현재 숫자를 key 값으로 dp에 현시점의 최대 하위 배열로 저장한다.
            // 이렇게 하면 완전 탐색 없이 곧바로 현재 숫자에서 차이를 뺏을 때 즉 직전의 숫자에서 최대 하위 배열을
            // 찾을 수 있다. 이런 과정은 리스트가 아니라 key로 저장해야 하므로 hashmap을 사용한다.
            if(dp.get(a - difference) == null) {
                dp.put(a, 1);                
            } else {
                dp.put(a, dp.get(a - difference) + 1);
            }
        }

        // 이제 hashmap을 탐색하면서 가장 값이 큰 위치를 찾아 리턴한다.
        for(Integer i : dp.keySet()) {
            answer = Math.max(answer, dp.get(i));
        }

        return answer;
    }
}
