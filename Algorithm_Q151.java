import java.util.*;

public class Algorithm_Q151 {
    /*
     * 이 문제는 힙(우선순위 큐)과 HashSet을 활용한 문제다.
     * 풀이 자체는 어렵지 않지만, 숫자 하나를 더 놓아야 한다는 점을 놓쳐서 구현에 햇갈리는 부분이 있었다.
     * 잘 확인해 보도록 하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/smallest-number-in-infinite-set/
     * 풀이를 확인해보자.
     */

    class SmallestInfiniteSet {

        // set의 경우 pop을 해서 없어진 숫자들의 모임이다. 즉, add할때 다시 추가 해야하는 숫자들의 모임이다.
        private Set<Integer> set;
        // pq의 경우 맨 위에 추가하여 뺄 수 있는 숫자들의 모임이 된다.
        private PriorityQueue<Integer> pq;
        // 현재 숫자를 나타내는 변수 --> 즉, 현재 쭉 작은 순서대로 빼면서 이번에 빼야할 가장 큰 숫자
        private Integer currentNumber;
    
        // 초기화
        public SmallestInfiniteSet() {
            set = new HashSet<>();
            pq = new PriorityQueue<>();
            currentNumber = 1;
        }
        
        public int popSmallest() {
            int ans = 0;
    
            // 만약 pq가 비어있지 않다면, 현재 뺀 가장 큰 숫자보다 작은 숫자가 이미 있다는 뜻이므로, 이를 poll 하고
            // set에서 정리한다.
            if(!pq.isEmpty()) {
                ans = pq.poll();
                set.remove(ans);
            } else {
                // 만약 큐가 비어있다면, 현재 지정한 최근에 뺀 가장 큰 값보다 더 큰게 필요하다는 뜻이므로 해당 숫자를 답으로
                // 반환 처리하면서 이 숫자는 1을 더한다.
                ans = currentNumber;
                currentNumber += 1;
            }
    
            return ans;
        }
        
        public void addBack(int num) {
            // 해당 함수에는 이미 존재하지 않는 숫자일 때 그리고 현재 숫자보다 작은 숫자일 때 없어진 숫자들이게 되므로
            // 이를 추가하며 pq에도 추가하여 뺄 수 있는 숫자로 표시한다.
            // 그렇지 않다면 이미 없에버린 숫자가 아니므로 set에 넣지 말아야 한다.
            if(currentNumber <= num || set.contains(num)) {
                return;
            }
    
            set.add(num);
            pq.offer(num);
        }
    }
}
