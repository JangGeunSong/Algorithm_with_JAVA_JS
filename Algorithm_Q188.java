import java.util.PriorityQueue;

public class Algorithm_Q188 {
    /*
     * 이 문제는 우선순위 큐와 양 끝점에서 원하는 정답을 찾기 위해 탐색을 진행하는 문제이다.
     * https://leetcode.com/problems/total-cost-to-hire-k-workers/
     * 풀이를 확인해보자.
     */

    public long totalCost(int[] costs, int k, int candidates) {
        long answer = 0L;
        int left = candidates;
        int right = costs.length - 1 - candidates;
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>();
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();

        // 후보자를 0번 인덱스 부터 후보자 숫자 만큼 추가
        for(int i = 0; i < candidates; i++) {
            leftQueue.add(costs[i]);
        }

        // 후보자를 끝에서부터 찾아야 하므로, 오른쪽 끝이 배열의 끝인데 거기서 시작할 점이 후보자의 수 부터인지 아니면
        // 해당 배열이 큰 배열이라서 후보자의 수를 배열의 전체 요소 수에서 뺐을때 값이 더 큰지 선택하고 거기서 배열 끝까지 진행
        for(int i = Math.max(candidates, costs.length - candidates); i < costs.length; i++) {
            rightQueue.add(costs[i]);
        }

        // k 명을 뽑을때 까지 진행
        for(int i = 0; i < k; i++) {
            if(rightQueue.isEmpty() || !leftQueue.isEmpty() && leftQueue.peek() <= rightQueue.peek()) {
                // 만약 왼쪽 큐가 비어있지 않으면서 오른쪽 큐의 가장 작은 값이 왼쪽의 peek 보다 크거나 같을 때
                // 혹은 오른쪽 큐가 비어 있을 때에는 왼쪽 큐에 가장 작은 값을 정답에 처리한다.
                answer += leftQueue.poll();

                if(left <= right) {
                    // 만약 왼쪽과 오른쪽의 index를 비교할 때 아직 교차하지 않았다면 왼쪽 큐에 데이터를 추가 가능
                    leftQueue.add(costs[left]);
                    left += 1;
                }
            } else {
                // 만약 위와 달리 오른쪽 큐가 작은 값이라면, 오른쪽 큐에서 정답을 추가
                answer += rightQueue.poll();

                // 아직 두 인덱스가 교차하지 않았다면
                if(left <= right) {
                    // 오른쪽에서 직원을 뽑았으니 오른쪽 큐에 데이터를 삽입한다.
                    rightQueue.add(costs[right]);
                    right -= 1;
                }
            }
        }

        // 정답을 리턴한다.
        return answer;
    }
}
