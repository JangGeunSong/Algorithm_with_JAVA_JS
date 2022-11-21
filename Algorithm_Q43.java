public class Algorithm_Q43 {
    /*
     * 해당 문제는 two 포인터를 사용해서 풀이하는 문제이다.
     * 어렵지 않은 문제인데, 풀이가 생각나지 않아 해답을 참고 했다. 이 논리를 꼭 기억해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/sort-colors/
     * 풀이를 확인해보자.
     */

    public void sortColors(int[] nums) {
        // 이 문제는 해결하기 위해 우선 왼쪽 부터 시작해서 two pointer로 비교해 푼다.
        int left = 0;
        int right = nums.length - 1;
        int tmp = 0;

        // 반복문은 아래와 같이 사용할 수도 있다.
        // 중요한 것은 종료 조건이다.
        for(int i = left; i <= right;) {
            // 만약 i 가 0이라면 left로 보내야 하므로, left로 보낸 후 left 와 i를 증가시킨다.
            if(nums[i] == 0) {
                tmp = nums[i];
                nums[i] = nums[left];
                nums[left] = tmp;
                i += 1;
                left += 1;
            } else if(nums[i] == 2) {
                // 만약 i 가 2 라면 right로 보내야 하므로, right로 보낸 후 right를 1 뺸다.
                tmp = nums[i];
                nums[i] = nums[right];
                nums[right] = tmp;
                right -= 1;
            } else {
                // 만약 i가 1 이라면 그냥 넘어간다.
                // 이렇게 되면 만약 0이 나오면 0과 left 위치는 1이 되는 것이기 때문에
                // 1과 0을 교환하는 것이 되어 올바르게 정렬된다.
                i += 1;
            }
        }

        return;
    }
}
