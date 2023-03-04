public class Algorithm_Q117 {
    /*
     * 이 문제는 3개의 pointer를 활용한 문제다.
     * sliding window를 처리하기 위해 3개의 포인터를 통해 계산을 하는 문제로 아래 풀이에 대한 설명을 확인하여 보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    // https://leetcode.com/problems/count-subarrays-with-fixed-bounds/solutions/3254003/day-63-two-pointer-o-n-time-and-o-1-space-easiest-beginner-friendly-sol/
    // 위 링크를 참고하여 풀이
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long answer = 0L;
        int minPos = -1;
        int maxPos = -1;
        int leftBound = -1;
        
        // 해당 문제는 요구 조건의 최소값이 등장한 위치, 최대값이 등장한 위치를 갱신해서 받고
        // 여기에 더해 현재 left 끝 지점의 위치를 받은 후 (연속으로 이어진 배열 시퀀스 상 가장 왼쪽 끝점)
        // left bound 와 position 값 중 가장 왼쪽에 위치한 포지션의 값의 차와 0 중 최대값을 얻어 이를 더한다
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= minK && nums[i] <= maxK) {
                minPos = (nums[i] == minK) ? i : minPos;
                maxPos = (nums[i] == maxK) ? i : maxPos;
                answer += Math.max(0, Math.min(minPos, maxPos) - leftBound);
            } else {
                // 만약 범위 안에 드는 숫자가 아니라면, left 끝점을 i 로 위치 값을 다 -1로 갱신한다.
                leftBound = i;
                minPos = -1;
                maxPos = -1;
            }
        }

        return answer;
    }

    // TLE 유발 코드
    // 10^5 만큼의 데이터가 들어오기 때문에 문제의 요구사항은 맞출 수 있지만, 이중 루프로 인해 계산이 느려진다.
    public long countSubarrays_cause_tle(int[] nums, int minK, int maxK) {
        long answer = 0L;
        int left = 0;
        int currMin = Integer.MAX_VALUE;
        int currMax = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            // 순회 데이터 초기화
            left = i;
            currMin = Integer.MAX_VALUE;
            currMax = Integer.MIN_VALUE;

            while(left < nums.length) {
                // 위의 현재 방문 노드에서 전부 다 방문 한다고 가정할 때
                // 만약 minK, maxK 범위에서 벗어난 값이 배열에 들어오면, 이는 조건에 맞지 않는
                // 연속 배열이 되므로 (최대, 최소 바운더리에서 벗어남)
                // 배열 순회를 멈춰 버리고 다음으로 넘어간다.
                if(nums[left] <= maxK) {
                    currMax = Math.max(nums[left], currMax);
                } else if(nums[left] > maxK) {
                    break;
                }

                if(nums[left] >= minK) {
                    currMin = Math.min(nums[left], currMin);
                } else if(nums[left] < minK) {
                    break;
                }

                // 현재 갱신되어 온 min, max 값이 바운더리 값과 일치한다면, answer 에 1을 더해서 계산
                if(isValid(currMin, currMax, minK, maxK)) {
                    answer += 1L;
                }

                // 배열 index 1 이동
                left += 1;
            }
        }

        return answer;
    }

    public boolean isValid(int currMin, int currMax, int minK, int maxK) {
        if(currMin == minK && currMax == maxK) {
            return true;
        }

        return false;
    }
}
