public class Algorithm_Q108 {
    /*
     * 이 문제는 배열만을 활용한 이진 탐색 문제이다.
     * 조건이 나름 어렵고, edge case를 잘 다루어야 하기 때문에 난이도가 있다.
     * 나의 경우 답에 근접하긴 했으나, 이후 구현을 너무 어렵게 하여 틀렸고, 정답을 통해 내용을 체크 하였다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/single-element-in-a-sorted-array/
     * 풀이를 확인해보자.
     */

    public int singleNonDuplicate(int[] nums) {
        // edge case : 배열의 길이가 1이면 그냥 해당 index의 값을 리턴
        if(nums.length == 1) {
            return nums[0];
        }

        // 배열을 반으로 나누었을때, 양 옆에 갯수가 짝수개와 홀수개일때 계산이 달라진다.
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;

        // 만약 끝 쪽에 답이 존재한다면, 이를 한번 체크해준다.
        // 양 끝에 있는 값들은 바로옆에 같은 값이 없다면, 곧바로 정답이 되므로, 이를 리턴해주는 것을 처음에 넣는다.
        if(nums[left] != nums[left + 1]) {
            return nums[left];
        }

        if(nums[right] != nums[right - 1]) {
            return nums[right];
        }

        // 1. 만약 반으로 나눈 부분 배열의 길이가 pivot 포함 홀수이면 pivot과 같은게 있는 쪽으로 이동
        // 2. 만약 반으로 나눈 부분 배열의 길이가 pivot 포함 짝수이면 pivot과 다른게 있는 쪽으로 이동
        // pivot은 부분 배열의 길이와 같다는 점을 잊으면 안된다.
        // 유니크한 원소의 수가 1개만 있으므로, 무조건 배열의 길이는 홀수이다.
        // 즉, pivot은 무조건 배열의 한 가운데가 된다.

        while(left < right) {
            pivot = (left + right) / 2;

            if(nums[pivot] != nums[pivot + 1] && nums[pivot] != nums[pivot - 1]) {
                return nums[pivot];
            } else if(
                (nums[pivot] == nums[pivot + 1] && pivot % 2 == 0) || 
                (nums[pivot] == nums[pivot - 1] && pivot % 2 != 0)) {
                    // 만약 pivot과 오른쪽이 같은데 pivot이 짝수면, 답은 오른쪽에 있다.
                    // 혹은 pivot이 왼쪽과 같은데 pivot이 홀수면 답은 오른쪽에 있다.
                    left = pivot + 1;
            } else {
                // 이 외에는 왼쪽에 답이 존재한다.
                right = pivot - 1;
            }
        }

        // 아무리 찾아도 안나오는 경우 left 에 있는 값이 답이 된다.
        return nums[left];
    }
}
