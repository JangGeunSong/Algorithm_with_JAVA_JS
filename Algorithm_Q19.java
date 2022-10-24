public class Algorithm_Q19 {
    /*
     * 해당 문제는 2개의 포인터를 활용하여, target 값이 배열의 어느 위치에 있어야 하는지를 묻는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/search-insert-position/
     * 풀이를 확인해보자.
     */ 

    public int searchInsert(int[] nums, int target) {
        int pivot = 0;
        int left = 0;
        int right = nums.length;

        while(left < right) {
            // 중앙값을 설정
            pivot = (left + right - 1) / 2;

            if(nums[pivot] > target) {
                // 만약 pivot 위치에 값이 target 보다 크다면 그 오른쪽에 있는 숫자들은 모두 target 보다 크므로, right 값을 pivot 으로 설정
                right = pivot;
            } else if(nums[pivot] < target) {
                // 만약 pivot 위치에 값이 target 보다 작다면 그 왼쪽에 있는 숫자들은 모두 target 보다 작으므로(pivot 위치 숫자 포함), left 값을 pivot + 1 으로 설정
                left = pivot + 1;
            } else {
                // target과 일치하는 index가 있다면, 그 index인 pivot을 바로 return 한다.
                return pivot;
            }
        }
        
        // 만약 위 while 문을 돌았는데, return 조건으로 들어가지 않는다면, 우선 해당 배열에는 target이 없다. 따라서, 이때 target이 insert 될 수 있는 위치는
        // left 즉, left - 1 까지가 target 보다는 무조건 작은 index 이므로, 이 위치 + 1인 left 가 return 되어야 한다.
        return left;
    }
}
