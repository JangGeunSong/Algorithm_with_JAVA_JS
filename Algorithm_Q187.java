import java.util.Arrays;

public class Algorithm_Q187 {
    /*
     * 이 문제는 prefix sum의 개념을 활용해서 현재 위치에서 나보다 작은 값과 큰 값들이 나로 도달하기 위해 필요한 총 코스트 값을 구하는 문제이다.
     * 풀이의 핵심은 가장 작은값 기준으로 나머지 값들이 가장 작은 원소에 대해 필요한 코스트 총량에서 시작해서 좀 더 큰 값으로 이동할 때 마다
     * 사전에 계산한 나보다 작은 값이 나로 도달하기 위해 1만큼의 차이가 날때의 필요 cost인 prefix sum을 사용하는 것이다.
     * 또한 나보다 큰 숫자에 해서도 이미 계산된 prefix sum에서 나를 제외한 나머지 숫자들의 prefix sum을 얻으면 그들이 1만큼 차이날 때 필요한 총 cost 값을 얻을 수 있다.
     * 이는 prefixSum[n - 1] - prefixSum[i - 1] 을 통해 구할 수 있다. 이를 suffix sum 이라고 지칭할 수 있다.
     * https://leetcode.com/problems/minimum-cost-to-make-array-equal/
     * 풀이를 확인해보자.
     */

    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] bundle = new int[n][2];
        long[] prefixSum = new long[n];
        long answer = 0;
        long totalCost = 0L;

        for(int i = 0; i < n; i++) {
            bundle[i][0] = nums[i];
            bundle[i][1] = cost[i];
        }

        // 첫번째 원소로 오름차순 정렬
        Arrays.sort(bundle, (a, b) -> a[0] - b[0]);

        // prefix 합산값은 첫번째 값은 가장 작은 숫자에 대해서 cost 값으로 받는다.
        // 이는 두 번째 index의 숫자부터 prefixSum을 구할때 이 prefixSum는 자신으로 도달하기 위해 1만큼의 증가로 
        // 인해 필요한 값이기 때문이다. (1 차이 날때의 값이 기준점)
        prefixSum[0] = bundle[0][1];
        for(int i = 1; i < n; i++) {
            // 현재 인덱스의 prefixSum은 현재 위치에서 나보다 작은 값들에 대해 사전의 합산값을 얻는 것이다.
            prefixSum[i] = bundle[i][1] + prefixSum[i - 1];
        }

        for(int i = 1; i < n; i++) {
            // 이제 bundle을 활용해서 가장 작은값 기준 총 코스트값을 계산해본다.
            // 총량은 현재 코스트 값 * 현재 위치에 값 - 가장 작은 값 ==> 이를 통해 가장 작은값 기준
            // 최대 코스트를 얻는다.
            totalCost += 1L * bundle[i][1] * (bundle[i][0] - bundle[0][0]);
        }

        // 우선 answer에 현재 가장 작은값 기준 총 코스트값을 담는다.
        answer = totalCost;

        // 이제 prefixSum을 사용해서 각 숫자에 대해서 totalCost에서 prefixSum(즉 해당 인덱스에 왔을 때 
        // 바로 옆의 작은 값과 gap을 구하고 그 gap에 대해서 도달하기 위해 왼쪽 즉 작은 값들에 대해 필요한 
        // 코스트 값이 prefixSum이니까 그 값을 구해서 더하고 오른쪽에 대해서는 뺀다.)
        for(int i = 1; i < n; i++) {
            int gap = bundle[i][0] - bundle[i - 1][0];
            totalCost += 1L * prefixSum[i - 1] * gap;
            // 아래처럼 나오는 이유는 현재 위치에서 자기보다 큰 값들의 코스트값의 총량은 최종 prefixSum의
            // 마지막 원소이다. 근데 이 값은 자기를 포함 작은 값들 전체를 포함하기 때문에 이를 빼기 위해
            // i - 1에 prefixSum을 빼서 값을 구한다.
            totalCost -= 1L * (prefixSum[n - 1] - prefixSum[i - 1]) * gap;
            answer = Math.min(answer, totalCost);
        }

        return answer;
    }
}
