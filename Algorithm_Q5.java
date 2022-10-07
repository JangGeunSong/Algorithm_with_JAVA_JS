import java.util.*;

public class Algorithm_Q5 {
    /*
     * 해당 문제는 기존의 two sum 문제를 응용하여 풀이해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/3sum/
     * 해당 문제의 풀이는 내가 풀이한 버전과 과거에 풀이를 확인하고 삽입했던 버전이 존재한다.
     * 각각에 대한 내용을 통해 더 나은 풀이에 대해 고민해보자.
     */ 

     // 두개의 풀이는 생각하는 방식은 같으나, 중복되는 조합을 넘어가는 방식이 상이하다.
     // 이에 따라 나의 풀이인 mine의 경우 속도와 메모리 둘다 best solution에 비해 더 안좋은 성능을 보여주었다.
    public List<List<Integer>> threeSum_mine(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        int left = 0;
        int right = 0;

        int left_val = Integer.MIN_VALUE;
        int right_val = Integer.MAX_VALUE;

        // 해당 문제를 해결하기 위해서는 하나의 점을 잡고 이후 2개의 점을 하나의 점의 바로 + 1인 지점과 가장 오른쪽
        // 끝에 있는 지점에서 시작해서 만약 0보다 크다면 오른쪽 값이 왼쪽으로, 0보다 작다면 왼쪽값이 오른쪽으로,
        // 0이 된다면, 해당 i, left, right 셋으로 list를 만들어 answer에 삽입하고, i 값을 1 증가시킨다.
        // 이때, 만약 i 번째 nums의 값이 이전값과 같다면 그냥 continue 해서 넘어간다.
        
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            if(i != 0) {
                if(nums[i - 1] == nums[i]) continue;
            }
            left = i + 1;
            right = nums.length - 1;
            left_val = Integer.MIN_VALUE;
            right_val = Integer.MAX_VALUE;
            while(left < right) {
                // 중복 조합에 대한 처리를 위해 left, right를 반복을 계속 돌면서 중복 연산하는 부분이 아래 best solution 보다는 많다.
                if(left_val == nums[left]) {
                    left += 1;
                    continue;
                }
                if(right_val == nums[right]) {
                    right -= 1;
                    continue;
                }
                if((nums[i] + nums[left] + nums[right]) == 0) {
                    List<Integer> elem = new ArrayList<>();
                    left_val = nums[left];
                    right_val = nums[right];
                    elem.add(Integer.valueOf(nums[i]));
                    elem.add(Integer.valueOf(nums[left]));
                    elem.add(Integer.valueOf(nums[right]));
                    answer.add(elem);
                    left += 1;
                    right -= 1;
                } else if((nums[i] + nums[left] + nums[right]) > 0) {
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }

        return answer;
    }

    public List<List<Integer>> threeSum_best_solution(int[] nums) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        
        if(nums.length < 3) {
            return answer;
        }
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++) {
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = 0 - nums[i];
                int j = i + 1;
                int k = nums.length - 1;
                while(j < k) {
                    if(nums[j] + nums[k] == target) {
                        answer.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        // 위의 answer 조합과 같은 조합이 나오는 경우는 과감히 패스하기 위해 2번의 반복문을 통해 left와 right 값을 재 설정한다.
                        while(j < k && nums[j] == nums[j + 1]) {
                            j++;
                        }
                        while(j < k && nums[k] == nums[k - 1]) {
                            k--;
                        }
                        j++;
                        k--;
                    } else if(nums[j] + nums[k] < target) {
                       j++;
                    } else {
                       k--;
                    }
                }
            }
        }
        
        return answer;
    }
}
