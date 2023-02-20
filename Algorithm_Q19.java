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

    // 2023-02-20 다시 풀어보기
    // 해당 문제를 다시 한번 풀어 보았다. 역시 한번에 풀어 냈으며, 위 풀이와는 다른 생각을 갖고 풀이했다. 하지만 아이디어는 같다.
    // 풀이를 확인해보자.
    public int searchInsert_v2_solution(int[] nums, int target) {
        // 이진 탐색을 위한 left, right 그리고 pivot을 준비
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;

        // left 가 right 보다 작을 때 까지 순회를 진행
        while(left < right) {
            // pivot은 left와 right의 합의 반
            pivot = (left + right) / 2;
            if(nums[pivot] > target) {
                // 만약 pivot에서 숫자배열의 값이 target 보다 크다면 right에 pivot을 준다.
                // 그 이유는 이 값이 최소한 right 위치에 놓이고 right 위치에 있던 숫자는 오른쪽으로 한칸 갈 것이기 때문이다.
                right = pivot;
            } else if(nums[pivot] < target) {
                // 만약 pivot에서 숫자배열의 값이 target 보다 작다면 left + 1에 pivot을 준다.
                // 그 이유는 해당 값은 pivot 위치보다 반드시 바로 오른쪽 한칸 옆에 있을 것이기 때문이다 (크니까)
                left = pivot + 1;
            } else {
                // 위 2가지 경우가 아니라면 target을 찾은 것이므로, pivot이 원하는 답이므로 리턴한다.
                return pivot;
            }
        }

        // 위 반복문으로 정답을 얻지 못한 경우 left에서 답을 판별한다.
        if(nums[left] < target) {
            // left 위치보다 target이 크다면, left 보다 반드시 오른쪽에 위치하므로, left + 1을 한다.
            return left + 1;
        } else {
            // 이와는 반대로 left 위치보다 target이 작다면 이 target이 left에 놓이고
            // 원레 left 값은 target 오른쪽에 있게 된다. 따라서 left를 리턴한다.
            return left;
        }
    }

}
