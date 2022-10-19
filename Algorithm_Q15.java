public class Algorithm_Q15 {
    /*
     * 해당 문제는 지난 번 풀었었던 jump 게임 문제와 비슷한 형태이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/jump-game/
     * 역시 DP와 그리디가 같이 존재하는 풀이가 있어야 하기 때문에 논리의 전개를 잘 살펴보고 기억해두자.
     */ 
    public boolean canJump(int[] nums) {
        boolean answer = false;
        int position = 0;

        /* 
         * jump 후 위치는 이전 연산에서 결국 점프해서 도달 한 위치 vs 현재 위치 (i) + 이 위치에서 최대 점프 가능 거리(nums[i])
         * 를 비교한 후 둘 중 최대값을 현재 점프한 후의 위치인 position에 담는다. 이후 이게 조건에 맞는지 검증만 하면 된다.
         */
        for(int i = 0; i <= position; i++) {
            // i => 지금 위치
            // position => 이전 단계에서 점프 여부를 체크 한 후 결과적으로 점프한 거리
            position = Math.max(position, i + nums[i]);
            if(position >= nums.length - 1) {
                // 해당 조건에 들어오면, 무조건 도달 가능한 것이므로, true로 return 한다.
                return true;
            }
        }

        return answer;
    }
}
