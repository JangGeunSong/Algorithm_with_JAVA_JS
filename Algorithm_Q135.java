public class Algorithm_Q135 {
    /*
     * 이 문제는 이진 탐색의 기본을 배열을 활용하여 구현하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/binary-search/
     * 풀이를 확인해보자.
     */

    public int search(int[] nums, int target) {
        // 이진 탐색을 위한 3개의 변수 left, right, 그리고 중앙값 pivot인 mid
        int left = 0;
        int right = nums.length;
        int mid = 0;

        // left 와 right가 교차하지 않는 순간까지 진행
        while(left < right) {
            // 중앙값은 양 끝 값의 반 -> -1이 붙는 이유는 right가 배열의 크기가 되었기 때문에 index 상으로는
            // -1이 추가 되어야 하기 때문
            mid = (left + right - 1) / 2;

            // 만약 현재 pivot이 target 보다 크다면 target은 left to mid 사이에 있을 가능성이 있으므로
            // right는 mid로 변경
            if(nums[mid] > target) {
                right = mid;
            } else if(nums[mid] < target) {
                // 만약 현재 pivot이 target 보다 작다면 target은 mid to right 사이에 있을 가능성이 있으므로
                // left는 mid로 변경
                left = mid + 1;
            } else {
                // 두 if문에 해당이 안된다면 이는 mid 위치의 값이 target과 같다는 것이므로, 이 mid 값을 리턴
                return mid;
            }
        }
        
        // 위 반복문을 도는 동안 mid 가 return 되지 않았다면 답이 없다는 이야기 이므로 -1을 리턴
        return -1;
    }
}
