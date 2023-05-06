import java.util.*;

public class Algorithm_Q160 {
    /*
     * 이 문제는 two pointer, sorting, binary search, subsequence 의 갯수 계산하기 지식을 활용한 문제다.
     * sorting, two pointer의 사용은 가능했지만, sub sequence의 갯수 계산에 대한 이해가 어려웠다. 풀이를 확인해보자.
     * https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
     * 풀이를 확인해보자.
     */

    public int numSubseq(int[] nums, int target) {
        // two pointer, sorting, binary search, subsequence 의 갯수 계산하기 지식이 필요한 문제다.
        // 값이 커질것을 감안하여 modulo를 선언
        int mod = (int) 1000000007;
        int left = 0;
        int right = nums.length - 1;
        int answer = 0;
        // 배열을 크기 순으로 정렬한다. 하위 배열은 순서 상관 없이 배열하는것을 말하기 때문이다.
        Arrays.sort(nums);

        // 해당 index가 배열의 길이일 때 하위 배열의 갯수를 저장할 exponents 배열 선언
        int[] exponents = new int[nums.length];

        // 원소가 1개라면 하위 배열은 자기를 포함할 때 단 1개 뿐이다.
        exponents[0] = 1;
        
        // 이후 나머지 모든 원소를 다 구하면
        for(int i = 1; i < nums.length; i++) {
            // 해당 인덱스의 하위 배열은 자기가 포함되냐 안되냐가 선택 사항이며 이는 이전 갯수에 2배에 해당하는 경우가
            // 발생한다. 이 값에 mod를 나머지 계산하여 int 형으로 보관할 수 있게 만든다.
            exponents[i] = (exponents[i - 1] * 2) % mod; 
        }

        // two pointer 및 binary search 를 활용하여 답을 구한다.
        while(left <= right) {
            // 두 끝점에서 문제 조건에 맞는다면
            if(nums[left] + nums[right] <= target) {
                // 현재 배열의 원소 갯수는 right index - left index 만큼의 갯수가 존재하는 것이므로
                // 이 때의 하위 배열 갯수를 answer에 담는다. 이후 숫자가 커질 수 있으므로, mod로 나머지 계산을 진행
                answer = (answer + exponents[right - left]) % mod;
                // left 를 1 증가 시킨다.
                left += 1;
            } else {
                // target보다 커진다면 크기순 배열을 했으므로, 가장 큰 값이 있는 right index를 1 낮추고 다시
                // 로직을 진행한다.
                right -= 1;
            }
        }

        // 얻은 결과를 리턴한다.
        return answer;
    }
}
