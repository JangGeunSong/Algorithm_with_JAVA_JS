public class Algorithm_Q38 {
    /*
     * 해당 문제는 이진 탐색을 구현해서 원하는 결과를 찾으면 되는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     * 풀이를 확인해보자.
     */  

    // 나의 풀이
    public int findMin_mine(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        int answer = 0;

        if(nums[left] < nums[right]) {
            return nums[left];
        }


        while(left < right) {
            // 중앙값이 왼쪽 끝에 있는 값 보다 작다면, 왼쪽 끝 ~ 중앙 사이에 최소값 있음
            // 중앙값이 오른쪽 끝에 있는 값보다 크다면, 중앙 ~ 오른쪽 끝 사이에 최소값 있음
            mid = (left + right) / 2;

            // 만약 중앙값이 왼쪽 혹은 오른쪽과 똑같아 진다면, 이제 남은것은 2개의 값 뿐이다.
            // 여기서 최소값을 얻을 수 있다.
            if(mid == left || mid == right) {
                break;
            }

            if(nums[mid] < nums[left]) {
                right = mid;
            } else if(nums[mid] > nums[right]) {
                left = mid;
            }
        }
        
        answer = nums[right];

        return answer;
    }

    // 가장 많은 upvote를 받은 풀이
    public int findMin_most_vote_solution(int[] nums) {
        // Edge case 처리
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, end = nums.length - 1;
        
        // 나의 풀이와 마찬가지로 이진 탐색을 위해 왼쪽과 오른쪽을 설정하고 진행
        while (start < end) {
            int mid = (start + end) / 2;
            // 만약 중앙값 이전에 요소가 중앙값보다 크다면 크기순으로 정렬되어 있는 현 시점에서 중앙값이 가장 작은 값이 된다. 
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                // 따라서 중앙값을 return
                return nums[mid];
            }
            // 이 외에는 이진 탐색을 진행
            if (nums[start] <= nums[mid] && nums[mid] > nums[end]) {
                // 왼쪽 값이 중앙값 보다 작거나 같고, 중앙값이 오른쪽 값보다 크다면
                // 중앙값 ~ 오른쪽값 사이에 가장 작은 값이 존재한다.
                // 왜냐하면 최대값이 중앙값 ~ 오른쪽 값 사이에 존재할 것이기 때문이다.
                // 따라서
                start = mid + 1;
            } else {
                // 위와 반대 경우라면 왼쪽값 ~ 중앙값 사이에 원하는 결과가 존재하므로
                end = mid - 1;
            }
        }
        // 이후 최종적으로 왼쪽 인덱스에 값을 리턴한다.
        return nums[start];
    }
}
