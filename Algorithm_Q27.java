import java.util.*;

public class Algorithm_Q27 {
    /*
     * 해당 문제는 힙을 사용한 문제다.
     * 힙의 표현 방식을 priority queue 말고 다른 방법으로도 사용하는 방법이 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/sliding-window-maximum/
     * 풀이를 확인해보자.
     */ 

    /*
     * 나의 풀이
     * 나의 풀이는 priority queue를 사용해서 remove 처리해 문제를 풀었다.
     * 이렇게 풀이해도 답은 나오는데, 문제는 실행 속도가 너무 느리다는데 있다.
     * 최악의 경우 window의 길이 k * 배열의 길이 n 이 되어 O(KN) 의 시간 복잡도를 가져 Time Limit Exceeded 가 나온다.
     */
    public int[] maxSlidingWindow_mine(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        int answer_size = nums.length - k + 1;
        int[] answer = new int[answer_size];
        
        for(int i = 0; i < answer_size; i++) {
            if(i == 0) {
                for(int idx = i; idx < i + k; idx++) {
                    heap.offer(nums[idx]);
                }
            } else {
                heap.offer(nums[i + k - 1]);
            }
            answer[i] = heap.peek();
            heap.remove(nums[i]);
        }
        
        return answer;
    }

    /*
     * Deque를 사용한 빠른 풀이
     * 아래 풀이는 Deque를 사용해서 풀이하는 방법이다.
     * 기본적으로 Deque는 queue의 성질과 stack의 성질이 함께 존재하는 자료구조로, 먼저 들어온 값을 삭제하는 것과 나중에 들어온 값을 삭제하는 것 2개 모두 상수 시간이 소요된다.
     * 이 장점을 활용해서 응용하면, 해당 문제를 풀이할 수 있다.
     */
    public int[] maxSlidingWindow_deque_version(int[] nums, int k) {
        int answer_size = nums.length - k + 1;
        int[] answer = new int[answer_size];
        
        // Deque를 사용하여 풀이
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int idx = 0; idx < nums.length; idx++) {
            // out of bound 처리 조건이 first 값이 idx - k + 1 보다 작은 값은 전부 제거 해야 함
            while(deque.size() > 0 && deque.peekFirst() <= idx - k) {
                deque.pollFirst();
            }

            // deque에 저장된 index 중 가장 뒤에 있는 값을 index로 한 배열의 요소 값이 현재 배열 요소보다 작으면
            // 현재 deque에 저장된 last 값은 삭제한다. 
            while(deque.size() > 0 && nums[deque.peekLast()] < nums[idx]) {
                deque.pollLast();
            }

            // 현재 idx를 deque에 삽입
            deque.addLast(idx);

            // 현재 idx가 window 크기보다 큰 상태가 되면 이제 window 내부에서 최대값을 얻어 정답 array에 삽입
            if(idx >= k - 1) {
                // 가장 첫번째에 있는 값이, 이 윈도우에서 가장 큰 값이다.
                answer[idx - k + 1] = nums[deque.peekFirst()];
            }
        }

        // 결과를 return 한다.
        return answer;
    }

    // 2023-08-16 풀이
    // 오늘 이 문제의 완벽해 해법에 대해 이해 했다. 이전보다 더 나아진 이해도를 가지고 풀이한 아래 내용을 확인해보자.
    public int[] maxSlidingWindow_improved(int[] nums, int k) {
        // DEQUE를 활용해 푼다. 이 큐는 양쪽에서 빼고 더할 수 있다는 성질을 활용해서 
        // 현재 가장 왼쪽에 값이 최대 값이 되도록 유도 하여 풀이를 진행하면 된다.
        int answerSize = nums.length - k + 1;
        int[] answer = new int[answerSize];

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < nums.length; i++) {
            while(deque.size() > 0 && deque.peekFirst() <= i - k) {
                // 현재 index를 기준으로 k 만큼 차이나는 index 까지만 비교 대상으로 삼아야 한다.
                // 그 이유는 지금 index가 window의 가장 마지막이기 때문이다.
                // 따라서, 현재 인덱스 - k 값과 비교 해서 지금 처음에 있는 값이 작으면 사라져야 한다.
                deque.pollFirst();
            }

            while(deque.size() > 0 && nums[deque.peekLast()] <= nums[i]) {
                // 위에서 범위를 벗어나는 왼쪽에 큰 값들을 제외 시켰다면 (index 순서니까 그렇게 지웠다)
                // 이제는 남아 있는 원소들 중에서 지금 index 에 있는 값보다 nums 배열 안에 값이 작으면 지워 버려야 한다.
                // 이때는 오른쪽에 있는 값부터 지워야 하므로, 이를 이용해 현재 while문을 돈다.
                deque.pollLast();
            }

            // 이제 불필요한 원소는 다 지워 버렸으므로, 현재 인덱스를 추가
            deque.add(i);

            if(i - k + 1 >= 0) {
                // 현재 window 를 체크 했을 때 창의 길이보다 더 긴 상태에서 진행하고 있다면
                // 지금 index 에서 정답은 현재 deque 에서의 왼쪽에 있는 값이다.
                answer[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        // 답을 리턴한다.
        return answer;
    }
}
