public class Algorithm_Q192 {
    /*
     * 배열 array {1,2,3,4} 에서 
     * subarray : {},{1},{2},{3},{4},{1,2},{2,3},{3,4},{1,2,3,4}
     * subsequences : {},{1},{2},{3},{4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4},{1,2,3},{1,2,4},{1,3,4},{2,3,4},{1,2,3,4}
     * 임을 항상 기억하자.
     * 이 문제는 subarray에서 원하는 조건을 구하는 문제로 크거나 같은이라는 조건이었다. 나는 같은 으로 문제를 안읽고 풀어서 이상한 곳에서 해매었다.
     * 급하게 풀어도 문제는 꼭 잘 읽고 풀도록 하자.
     * https://leetcode.com/problems/minimum-size-subarray-sum/
     * 풀이를 확인해보자.
     */

    public int minSubArrayLen(int target, int[] nums) {
        // 문제에서 "greater than" 이라고 헀다. 꼭 문제를 잘 보도록 하자;;;
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int currValue = 0;

        // 두 점을 활용해서 subarray를 구하고 그 합을 잡아 본 후 계산을 진행
        for(right = 0; right < nums.length; right++) {
            currValue += nums[right];

            // 문제에서는 "현재 값이 target 보다 크거나 같은 경우"에 하위 배열의 길이를 구하라고 했으므로,
            // 이런 경우를 만족하는 순간 답이 있기 때문에 여기서 부터는 하나씩 빼가며 기준에 만족하면 답을 구한다.
            while(currValue >= target) {
                answer = Math.min(answer, right - left + 1);
                currValue -= nums[left];
                left += 1;
            }
        }

        // 만약 그런 경우가 없다면 answer가 정수 최대 값일 것이기 때문에 이때는 0으로 변환
        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        // 답을 리턴한다.
        return answer;
    }
}
