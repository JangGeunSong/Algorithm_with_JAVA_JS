import java.util.*;

public class Algorithm_Q23 {
    /*
     * 해당 문제는 HashTable과 배열을 사용해 풀이하는 문제이다.
     * 시간은 반드시 O(N) 안으로 끝나야 하고, 메모리는 상수개의 공간만 차지해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/first-missing-positive/
     * 이 문제는 스스로 생각해 풀이한 부분과 다른 사람의 풀이를 함꼐 첨부한다.
     * 풀이를 확인해보자.
     */

    /*
     * 나의 풀이
     * 우선 속도를 고려할 때, O(N)으로 배열을 순회해 한번에 답을 얻기 위해서는 배열이 오름차순으로 정렬 되어야 한다.
     * 따라서, O(N) 보다 빠르게 정렬하는 알고리즘을 먼저 적용해야 하므로, O(logN)의 속도가 떨어질 수 있는 병합 정렬 혹은 퀵 정렬을 써야 한다.
     * 하지만, 병합 정렬은 메모리를 배열의 크기만큼 주어야 하므로, 이는 문제 조건에서 사용 불가능 하기 때문에 퀵 정렬을 써야 한다.
     * 자바 공식 문서에는 Arrays.sort 함수를 사용할 때 듀얼피봇 퀵정렬(Dual-Pivot QuickSort)을 통해 알고리즘이 구현되어 있다고 설명 되어 있으므로
     * 이 방법을 쓴다. (랜덤한 데이터에서 듀얼피봇 퀵정렬은 일반 퀵 정렬보다 좀더 빠른 것으로 알려져 있다.)
     * 또한 상수개의 메모리를 사용한다는 점에서 고민해 볼때, 가장 중요한 것은 이전의 값이 중복으로 나오면 pass를 하도록 previous value가 있어야 하고
     * answer는 배열의 값이 1인 index 부터 시작하도록 미리 while문을 전개하여, 1부터 시작할 때 현재 answer 값이 1씩 증가하는데 이 값이 배열에 존재하지 않는다면,
     * 이게 답이므로, 해당 값을 돌려주도록 한다.
     * 여기서 중요한 점은 바로 edge case를 잘 보아야 한다.
     */
    public static int firstMissingPositive_mine(int[] nums) {
        int answer = 1;
        int prevVal = 0;
        int idx = 0;

        // 배열의 정렬을 진행 (오름차순)
        Arrays.sort(nums);

        // 배열을 순회하여 배열 인덱스가 배열의 원소가 1인 곳 부터 시작하도록 처리
        // 이때, 배열이 음수, 0 으로만 이루어져 있는 경우 
        // 배열의 크기를 초과하는 만큼 반복이 돌 수 있으므로, 
        // 이런 상황이 발생한다면, 답은 어차피 1이니까 바로 1을 return
        while(nums[idx] <= 0) {
            idx += 1;
            if(idx == nums.length) {
                return answer;
            }
        }

        // 배열의 값이 1인 지점부터 진행
        for(int i = idx; i < nums.length; i++) {
            // previous value를 0으로 초기화 하면 배열은 1인 지점부터 시작하므로 맨 처음에는
            // 해당 if문은 진행 되지 않는다.
            if(prevVal == nums[i]) {
                continue;
            }
            // 만약 previous value 조건을 넘어서게 되었을 때 원하는 answer를 찾는 로직을 진행한다.
            if(nums[i] != answer) {
                // 만약 예상되는 answer값이 아닌 다른 값이 nums[i]에서 등장한다면 순회를 정지하고
                // answer가 반환 되도록 처리
                break;
            } else {
                // previous value와 answer를 갱신
                prevVal += 1;
                answer += 1;
            }
        }

        // break가 걸리지 않았다면 answer는 1씩 증가해 답이 될 것이고, 아니라면 배열을 통해 찾은 가장 작은 값을 돌려준다.
        return answer;
    }

    /*
     * O(N)속도 및 상수 공간을 쓰는 또 다른 방법
     * https://leetcode.com/problems/first-missing-positive/solutions/17083/o-1-space-java-solution/
     * 나의 풀이보다 더 빠른 시간을 차지하여 풀이된다.
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length){
            if(nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) {
                i++;
            }
            else if(nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
            else {
                i++;
            }
        }
        i = 0;
        while(i < nums.length && nums[i] == i+1){
            i++;
        } 
        return i+1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
