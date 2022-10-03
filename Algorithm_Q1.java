import java.util.*;
/*
 * 이 java 파일부터는 2022년 10월 부터 본격적으로 기존에 풀었던 알고리즘 문제에 대한 정리를 진행합니다.
 * 해당 풀이에 대해서 내 의견이 담긴 부분은 Notion에 정리해 두었으며, 이 문제에 대한 링크를 아래 남겨 둡니다.
 * 원본 문제 => https://leetcode.com/problems/two-sum/description/
 */
public class Algorithm_Q1 {
    public int[] twoSumforHashMapSolution(int[] nums, int target) {
        int[] answer = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        // key => value , value => index
        // target - nums[i] => key
        // if the key of the map is nums[i], this pair of the target. So, answer is i and value of map(nums[i])
        for(int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }

        // if i and value of map(nums[i]) is same, that is not match the problem's option
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                if(map.get(nums[i]) == i) {
                    continue;
                }
                answer[0] = i;
                answer[1] = map.get(nums[i]);
            }
        }
        return answer;
    }

    public int[] twoSumforArraySolution(int[] nums, int target) {
        int[] answer = new int[2];
        // 주어진 배열에 대해 2중 루프 진행
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums.length; j++) {
                // 만약 i와 j값이 같다면, 해당 경우에는 아래 로직을 진행하지 말고, 그냥 진행
                if(i == j) continue;
                // 조건에 맞다면 정답 배열에 i, j 값을 넣은 후 루프 종료
                if(nums[i] + nums[j] == target) {
                    answer[0] = i;
                    answer[1] = j;
                    break;
                }
            }
        }
        return answer;
    }
}
