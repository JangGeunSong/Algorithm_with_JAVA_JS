public class Algorithm_Q10 {
    /*
     * 해당 문제는 DP를 통해 풀어야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-subarray/
     * 해당 DP 문제중에서도 배열의 부분합을 구하는 방법으로 이미 알려져 있는 방법인 Kadane’s Algorithm (카데인 알고리즘)을 통해 풀어야 한다.
     * 위 문제는 사실 정형화 되어 있는 풀이가 있으나, 이 부분을 어떻게 DP로 구현해 냈는지를 잘 확인하자.
     */ 

    // 카데인 알고리즘의 정의만 확인하고, 작성한 풀이
    public int maxSubArray_only_definition(int[] nums) {
        // 이 문제를 해결하기 위해 divide and conquer 전략을 사용해서 풀이해보자.
        // 첫번째 전략은 문제 풀이에서 brute force를 통해 풀이한 것으로 시간 초과가 일어난다.
        // 따라서, 우리는 kadane's 알고리즘을 사용한다.
        // 이는 부분합이 i번째 부분합의 최대값을 nums[i] = nums[i] + nums[i - 1] 로 표현할 수 있음을 활용해 얻는다.
        // 위 식의 max를 찾은 후 값을 구하면, 그게 최대 부분합이 된다는 원리이다.
        // 즉 nums[i] 가 음수이거나 0 이라면, 덧셈을 하지 않고, 양수일때만 덧셈을 하는 방법을 통해 최대 합을 구하는 것이다.
        // 이게 가능한 이유는 만약 현재 i번째 인덱스의 값이 이전에 쭉 더했던 값보다 더 크다면, 거기서 부터 시작하는
        // 부분합이 최대가 될 수 있다는 것을 활용한 것이다.
        int answer = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
        }

        for(int i = 0; i < nums.length; i++) {
            answer = Math.max(answer, nums[i]);
        }

        return answer;
    }

    // 카데인 알고리즘에 더해 answer까지도 한번에 찾아내는 풀이
    public int maxSubArray_best_practice(int[] nums) {
        int maximumValue = nums[0];
        int temp = 0;
        if(nums.length == 1) {
            return maximumValue;
        }
        
        // answer 값까지 한번에 찾아내도록 설계되었음.
        // Math 클래스 없이 찾는 방식을 사용함
        for(int i = 0; i < nums.length; i++) {
            if(temp + nums[i] < nums[i]) {
                temp = nums[i];
            } else {
                temp += nums[i];
            }
            
            if(maximumValue < temp) {
                maximumValue = temp;
            }
        }        
        
        return maximumValue;
    }
}
