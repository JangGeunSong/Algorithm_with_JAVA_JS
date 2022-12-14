public class Algorithm_Q60 {
    /*
     * 해당 문제는 DP 문제이다.
     * 이 문제를 해결하기 위해서는 memozation을 활용하면 된다. 해당 기록을 어떻게 진행할지를 고민하는 부분이 조금 어려울 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/house-robber/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int rob(int[] nums) {
        // 현재 수익을 기록하기 위한 기록 배열
        int[] dp = new int[nums.length];
        int answer = 0;

        for(int i = 0; i < nums.length; i++)  {
            if(i <= 1) {
                // 만약 첫번째, 두번째 집이면 일단 최대 수익은 그 집을 털어야 하는 것이므로 이를 기록
                dp[i] = nums[i];
            } else {
                // 만약 세번째 집 이라면, 앞의 기록들 중 가장 큰 수익을 얻은 직후 + 현재 집에서 얻는 수익이어야 한다.
                // 여기서 주의할 점은 바로 이전 집을 방문한 수익 기록은 사용 할 수 없다는 점이다.
                int a = 0;
                for(int j = 0; j < i - 1; j++) {
                    a = Math.max(a, dp[j]);
                }
                dp[i] = a + nums[i];
            }
        }
        
        // 위 과정을 통해 얻은 수익 기록들 중에서 가장 큰 값이 무엇인지 1회 순회하며 찾아낸다.
        for(int i = 0; i < dp.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

        // 얻어낸 최대 수익을 리턴한다.
        return answer;
    }

    // 다른 풀이 => 풀이 기조는 비슷하다.
    public int rob_others(int[] nums) {  
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
    
        //Initialize an arrays to store the money
        int[] mark = new int[nums.length];
    
        //We can infer the formula from problem:mark[i]=max(num[i]+mark[i-2],mark[i-1])
        //so initialize two nums at first.
        mark[0] = nums[0];
        mark[1] = Math.max(nums[0], nums[1]);
    
        //Using Dynamic Programming to mark the max money in loop.
        for(int i=2;i<nums.length;i++){
            mark[i] = Math.max(nums[i]+mark[i-2], mark[i-1]);
        }
        return mark[nums.length-1];
    }
}