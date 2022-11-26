public class Algorithm_Q47 {
    /*
     * 해당 문제는 이진 탐색 문제이다. 
     * 이진 탐색의 대상인 목록들이 0 ~ 끝 까지 순서대로 정렬되어 있는데 그게 회전해서 순서가 조금 변경된 상태일떄의 풀이를 생각해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     * 풀이를 확인해보자.
     */

    // 일반적인 이진 탐색을 사용한 풀이
    public int search_typical_binary_search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int answer = -1;
        
        if(nums.length == 1) {
            if(nums[0] == target) {
                return 0;
            } else {
                return answer;
            }
        }

        while(left < right) {
            mid = (left + right) / 2;

            // 타겟이 현재 mid 에서의 값과 같으면 즉시 종료
            if(target == nums[mid]) {
                answer = mid;
                break;
            }
            
            if(nums[left] <= nums[mid]) {
                // 만약 left 와 mid가 left <= mid 관계를 유지한다면, 이는 해당 구간에서 크기순 정렬이 되어 있음을 의미
                if(nums[left] <= target && target < nums[mid]) {
                    // left <= target < mid
                    right = mid - 1;
                } else {
                    // target < mid but left > target so left = mid + 1 왜냐하면 mid도 일단 target은 아니니까
                    left = mid + 1;
                }
            } else {
                // 만약 mid 값이 target 보다 작으면 mid 보다는 오른쪽에 있음
                if(nums[mid] < target && target <= nums[right]) {
                    // mid < target =< right
                    left = mid + 1;
                } else {
                    // mid < target but target > right so right = mid - 1 왜냐하면 mid도 일단 target은 아니니까
                    right = mid - 1;
                }
            }
        }

        if(nums[left] == target) {
            answer = left;
        }

        return answer;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/14435/clever-idea-making-it-simple/ 참고
    public int search_clever_method(int[] nums, int target) {
        int mid = nums.length / 2;
        int left = 0;
        int right = nums.length - 1;
        
        int num = 0;
        
        if(nums.length == 1) {
            if(nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        
        while(left < right) {
            // System.out.println("left : " + left);
            // System.out.println("right : " + right);
            mid = (left + right) / 2;

            num = (nums[mid] < nums[0]) == (target < nums[0])
                ? nums[mid]
                : target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            
            if(num < target) {
                left = mid + 1;
            } else if(num > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        
        if(nums[left] == target) {
            return left;
        }
        
        return -1;
    }
}
