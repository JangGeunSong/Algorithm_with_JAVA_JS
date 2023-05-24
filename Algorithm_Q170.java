import java.util.*;

public class Algorithm_Q170 {
    /*
     * 이 문제는 우선순위 큐를 활용해 풀이하는 문제이다.
     * 구현애 난이도가 꽤 있는 문제로 풀이를 잘 읽어보고 구현에 대해 고민해보자.
     * https://leetcode.com/problems/maximum-subsequence-score/
     * 풀이를 확인해보자.
     */

    public long maxScore(int[] nums1, int[] nums2, int k) {
        // 풀이 코드 예시

        // Sort pair (nums1[i], nums2[i]) by nums2[i] in decreasing order.
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; ++i) {
            pairs[i] = new int[]{nums1[i], nums2[i]};
        }
        // 배열을 짝으로 정렬하기
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        
        // Use a min-heap to maintain the top k elements.
        // 일단 K 개 만큼 합산하고, 이들을 우선순위 큐에 삽입
        PriorityQueue<Integer> topKHeap = new PriorityQueue<>(k, (a, b) -> a - b);
        long topKSum = 0;
        for (int i = 0; i < k; ++i) {
            topKSum += pairs[i][0];
            topKHeap.add(pairs[i][0]);
        }
        
        // 이제 답을 먼저 잡아 주고
        // The score of the first k pairs.
        long answer = topKSum * pairs[k - 1][1];
        
        // K부터 시작해서 하나씩 들어갈 때, 우선순위 큐에서 하나를 poll 하면 그 값은 가장 작은 값이 빠지고,
        // 이때 현재 짝에서 데이터를 삽입한다.
        // Iterate over every nums2[i] as minimum from nums2.
        for (int i = k; i < n; ++i) {
            // Remove the smallest integer from the previous top k elements
            // then ddd nums1[i] to the top k elements.
            topKSum += pairs[i][0] - topKHeap.poll();
            topKHeap.add(pairs[i][0]);
            
            // 이렇게 얻은 합산에 지금 시점에 곱셈 인자를 곱해주고, 여기서 최대값을 얻어준다.
            // Update answer as the maximum score.
            answer = Math.max(answer, topKSum * pairs[i][1]);
        }
        return answer;
    }
}