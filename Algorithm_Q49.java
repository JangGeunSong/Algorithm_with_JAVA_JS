public class Algorithm_Q49 {
    /*
     * 해당 문제는 two pointer를 사용한 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/move-zeroes/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public void moveZeroes_mine(int[] nums) {
        int tmp = 0;
        // 2개의 포인터 index와 pivot을 사용
        int pivot = 0;
        int index = 0;

        // 만약 index에 값이 0이라면 pivot 값은 더 이상 올라가지 않는다.
        // 따라서, 이렇게 고정된 pivot은 0의 위치가 되어 다음으로 진행할 때 0이 아닌 인덱스로 가면
        // 이거와 교체하면서 0이 한쪽으로 몰리게 만들 수 있다.
        for(index = 0; index < nums.length; index++) {
            if(nums[index] != 0) {
                tmp = nums[pivot];
                nums[pivot] = nums[index];
                nums[index] = tmp;
                pivot += 1;
            }
        }

        return;
    }

    
    // 다른 형태의 two pointer 풀이
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;        

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }        

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
