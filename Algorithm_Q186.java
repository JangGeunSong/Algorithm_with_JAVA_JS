import java.util.Arrays;

public class Algorithm_Q186 {
    /*
     * 이 문제는 조건에 맞는 길이의 scope를 대고 여기에 맞춰 정답을 도출해 내면 되는 sliding window 문제이다.
     * 끝 점에 대한 처리가 핵심이었던 문제로 로직을 잘 확인해보자.
     * https://leetcode.com/problems/k-radius-subarray-averages/
     * 풀이를 확인해보자.
     */

    public int[] getAverages(int[] nums, int k) {
        double radius = k * 2 + 1;
        int n = nums.length;
        int[] answer = new int[n];
        // 입력값이 10^5의 숫자를 10^5 만큼 더할 수도 있으므로 double로 선언한다.
        double cumulate = 0;

        // 만약 배열의 길이가 radius보다 작으면 무조건 모두 -1 이므로 아래와 같이 처리
        if(n < radius) {
            Arrays.fill(answer, -1);
            return answer;
        }

        // 현재 누적 값 (반지름을 활용한)을 구한다.
        for(int i = 0; i < radius; i++) {
            cumulate += nums[i];
        }

        for(int i = 0; i < n; i++) {
            if(i - k < 0 || i + k >= n) {
                answer[i] = -1;
            } else {
                answer[i] = (int) (cumulate / radius);
                // 항상 현재 시점의 정답을 구하고 나면 왼쪽 끝에 있던 값은 뺴 줘야 한다.
                cumulate -= nums[i - k];
                // edge를 잘 계산 해야 한다. 끝점에 대해서 배열의 맨 마지막 인덱스가 넘어가지 않는경우에만
                // 끝자락 값을 더하고 아닌 경우 즉 맨 마지막 window일 떄에는 덧셈을 패스한다.
                if(i + k < n - 1) {
                    cumulate += nums[i + k + 1];
                }
            }
        }

        // 정답을 리턴한다.
        return answer;
    }
}
