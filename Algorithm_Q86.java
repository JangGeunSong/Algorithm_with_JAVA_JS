public class Algorithm_Q86 {
    /*
     * DP 문제이다.
     * 이 문제는 배열의 부분합을 통해 원하는 결과를 얻는 문제로 kadane 알고리즘에 대해 이해하고 있다면 이를 응용해 풀 수 있는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-sum-circular-subarray/
     * 풀이를 확인해보자.
     */

    // 풀이코드
    public int maxSubarraySumCircular(int[] nums) {
        // 최대 부분합, 최소 부분합, 전체 합을 활용해서 풀어야 하는 문제
        // https://medium.com/@vdongbin/kadanes-algorithm-%EC%B9%B4%EB%8D%B0%EC%9D%B8-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-acbc8c279f29
        // 위 카데인 알고리즘을 참고할 것.
        int answer = 0;
        int max_kadane = Integer.MIN_VALUE;
        int min_kadane = Integer.MAX_VALUE;
        int total_sum = 0;
        int n = nums.length;

        int[] max_nums = new int[n];
        int[] min_nums = new int[n];

        // 답을 얻기위해 kadane 알고리즘을 사용한다.
        for(int i = 0; i < n; i++) {
            max_nums[i] = nums[i];
            min_nums[i] = nums[i];
        }

        for(int i = 1; i < n; i++) {
            max_nums[i] = Math.max(max_nums[i], max_nums[i] + max_nums[i - 1]);
            min_nums[i] = Math.min(min_nums[i], min_nums[i] + min_nums[i - 1]);
        }

        for(int i = 0; i < n; i++) {
            total_sum += nums[i];
            max_kadane = Math.max(max_nums[i], max_kadane);
            min_kadane = Math.min(min_nums[i], min_kadane);
        }

        // 만약 최대 부분합이 음수라면, 모든 숫자가 음수이므로, 이때, 가장 큰 값은 최대 부분합이다.
        if(max_kadane < 0) {
            answer = max_kadane;
        } else {
            // 이 외의 경우에는 최대 부분합 vs 전체합 - 최소 부분합 중 하나가 가장 큰 값이다.
            // 그 이유는 원형으로 계산이 되기 때문에 양 끝의 값끼리 더한게 최대라면
            // 그것은 중앙에서의 최대값만을 얻는 최대 부분합 보다 더 큰 값이 얻어지는 경우가 생기므로
            // 이를 체크하기 위해 전체 합 - 최소 부분합 으로 이를 얻어내는 것이다.
            // 이는 최소 부분합도 역시 양 끝에 값을 통해 얻는게 아니기 때문에 알 수 있게 되는 값이다.
            answer = Math.max(max_kadane, total_sum - min_kadane);
        }

        return answer;
    }
}
