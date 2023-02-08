public class Algorithm_Q9 {
    /*
     * 해당 문제는 그리디 알고리즘을 활용해서 풀어야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/jump-game-ii/
     * 해당 그리디 알고리즘은 현재 상태에서 최선의 값 그러니까 원하는 조건 아래에서 최대 효과를 누릴 수 있는 선택지를 찾아서 그 선택지에 맞춰 논리가 전개 되어야 한다.
     * 위 문제도 생각을 잘못하면 함정에 빠져 풀이가 제대로 나오지 않을 수 있기 때문에 항상 풀이할 때에는 그리디로 가야하는 조건을 어떻게 가져올 수 있는지 고민할 필요가 있다.
     */ 

    public int jump(int[] nums) {
        // 이 문제는 greedy 문제로 greedy는 상황 안에서 최적의 결과값을 얻어내는 과정을 찾아내는 것이다.
        // 해당 문제에서 greedy는 현재 위치 + 점프 가능 거리의 합이 가장 큰게 최적해이다.
        // 처음 index 일 때에는 0번 인덱스 + 0번 인덱스에서 최대 점프할 수 있는거리 가 일단 가장 큰 값이므로,
        // 이를 일단 position으로 잡아두고 여기까지 index가 올라갈 때, 최대값은 점프 위치 + 점프 거리 값이 최대인게
        // 최적해이다.

        int answer = 0;
        int position = 0;
        int step = 0;

        // index가 nums.length - 2 까지만 고려되는 이유는 nums.length - 1이 목적지이기 때문에 여기에서 점프할 필요가 없기 때문이다.
        for(int index = 0; index < nums.length - 1; index++) {
            // 현재 index 가 진행되는 동안 최대 값을 step에 담아 둔다. 이떄 값은 현재 index 에서 nums[index]를 더한게
            // 이 index에서 점프했을때 최대 이동 거리이다.
            step = Math.max(step, index + nums[index]);
            if(index == position) {
                // 만약 index가 position만큼 도달했으면, 현재 position 까지 가는 동안 nums[index] 가 가질 수 있는 최대값이
                // 최대로 점프할 수 있는 구간이니까, 여기까지 점프 했다고 처리한다. 이를 step으로 한다.
                answer += 1;
                position = step;
            }
        }

        return answer;
    }

    // 101 번째 풀이 코드는 이 문제를 다시 풀어보는 것으로 결정했다.
    // 다시 풀었을 때에도 접근이 어느정도는 가능 했으나, DP에 매몰되어 솔루션을 찾지 못했다.
    // 아래 풀이를 꼭 기억 하도록 하자.
    public int jump_2(int[] nums) {
        int n = nums.length;
        // 정답
        int answer = 0;
        // 현재 위치
        int currPoint = 0;
        // 최대 점프 가능 거리
        int maxJumpStep = 0;

        // n - 1 까지 도달하는 거리 이므로, n - 1 이전까지만 생각한다.
        for(int i = 0; i < n - 1; i++) {
            
            // 최대 점프 가능 거리는 현재 index + 점프 가능 거리 vs 현재 최대 점프 가능 거리 중 큰 값을 선택
            maxJumpStep = Math.max(maxJumpStep, i + nums[i]);

            // 현재 위치가 index 와 같다면
            if(i == currPoint) {
                // 현재 위치를 최대 점프 가능 거리로 갱신 즉, 1 index 에서 3을 뛰는게 최대면 4가 신규 위치다.
                currPoint = maxJumpStep;
                // 점프를 한번 하게 되는 것이므로 answer + 1 을한다.
                answer += 1;
            }

        }

        // 결과를 리턴
        return answer;
    }

}
