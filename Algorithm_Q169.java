import java.util.*;

public class Algorithm_Q169 {
    /*
     * 이 문제는 우선순위 큐를 활용해 풀이하는 문제이다.
     * 문제의 조건을 따지기 위해 고민을 해볼 필요가 있으며, 잘못하면 원하는 답이 떨어지지 않는 경우가 많으므로 주의하자.
     * https://leetcode.com/problems/kth-largest-element-in-a-stream/
     * 풀이를 확인해보자.
     */

    class KthLargest {

        // 항상 남아 있을 class의 필드 변수들
        private PriorityQueue<Integer> queue;
        // k 번째 순서 값을 저장
        private int order;
    
        // 생성자
        public KthLargest(int k, int[] nums) {
            // 큐와 순서값을 세팅
            queue = new PriorityQueue<>();
            order = k;
    
            for(int num : nums) {
                queue.offer(num);
            }
    
            // k 번째 큰 숫자를 얻기 위해서 배열 길이 - K 까지 poll 하면 가장 위에 있는 값이 k 번째 큰 값이다.
            for(int i = 0; i < nums.length - order; i++) {
                queue.poll();
            }
        }
        
        // add 함수 큐에 값을 넣고, 딱 하나의 값만 poll 하면 지금 현재 queue가 K 번째 큰 수 부터 그 다음 큰 숫자를 모두
        // 갖고 있으므로, 어떤 값이든 추가한 후 이를 poll 하면 순서는 유지된 채로 지속 되게 된다. 이때 peek를 리턴
        public int add(int val) {
            queue.offer(val);
    
            // 만약 queue에 있는 데이터의 숫자가 k 번째를 유지하지 못할 정도로 작다면 그냥 해당 peek값을 리턴한다.
            if(queue.size() <= order) {
               return queue.peek(); 
            } else {
                queue.poll();
            }
    
            return queue.peek();
        }
    }
}
