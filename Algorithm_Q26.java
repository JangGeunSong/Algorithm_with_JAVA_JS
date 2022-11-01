import java.util.*;

public class Algorithm_Q26 {
    /*
     * 해당 문제는 힙을 사용한 문제다.
     * 어떻게 배열을 분리하고, 문제를 풀어 나가는지 잘 확인해보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-median-from-data-stream/
     * 이 문제는 클래스를 만들고 실행해야 하는 문제라서 2개의 클래스가 만들어 진다.
     * 풀이를 확인해보자.
     */ 

    /*
     * 나의 풀이 
     * median을 요구할 때 정렬을 진행한 후 값을 전달
     */
    class MedianFinder_mine {

        private List<Integer> list;
    
        public MedianFinder_mine() {
            list = new ArrayList<>();
        }
        
        public void addNum(int num) {
            list.add(num);
        }
        
        public double findMedian() {
            double median = 0.0;
            double numerator = 0.0;
            Collections.sort(list);
            if(list.size() % 2 == 0) {
                // If list size is even
                numerator = (double) list.get(list.size() / 2) + (double) list.get((list.size() / 2) - 1);
                if(numerator == 0) {
                    return median;
                } else {
                    median = numerator / 2.0;
                }
            } else {
                // If list size is odd
                median = list.get(list.size() / 2);
            }
    
            return median;
        }
    }

    /*
     * 최적화 솔루션
     * 삽입할 때 median을 구할 수 있도록 배열이 정렬
     * 우선순위 큐 2개를 활용해 heap으로 배열의 중앙값을 기점으로 분리한다.
     * 그리고 각 큐에서 값을 하나씩 뺴서 median으로 구할 수 있게 처리한다.
     * 이때 even이 아닌 홀수개라면, 둘 중 하나에서 중앙값이 나오게 조정하고, return할 수 있게 하면 된다.
     * 나의 경우 minHalfHeap에서 나오도록 했다. 이 heap은 배열을 오름차순 정렬한 후 작은 값들인 초반부 요소들을 모아둔 우선순위 큐이다.
     * 바로 이 heap에서 가장 큰 값이 우선순위 큐에서 나오기 때문에, 이게 중앙값이 되도록 처리했다.
     */
    class MedianFinder_solution {

        private PriorityQueue<Integer> minHalfHeap;
        private PriorityQueue<Integer> maxHalfHeap;
    
        private boolean isEven;
    
        public MedianFinder_solution() {
            minHalfHeap = new PriorityQueue<>(Collections.reverseOrder());
            maxHalfHeap = new PriorityQueue<>();
    
            isEven = true;
        }
        
        public void addNum(int num) {
            if(isEven) {
                maxHalfHeap.offer(num);
                minHalfHeap.offer(maxHalfHeap.poll());
            } else {
                minHalfHeap.offer(num);
                maxHalfHeap.offer(minHalfHeap.poll());
            }
    
            isEven = !isEven;
        }
        
        public double findMedian() {
            double median = 0.0;
            double numerator = 0.0;
    
            if(minHalfHeap.peek() == null && maxHalfHeap.peek() == null) {
                numerator = 0.0;
            } else if(minHalfHeap.peek() == null) {
                numerator = (double) maxHalfHeap.peek();
            } else if(maxHalfHeap.peek() == null) {
                numerator = (double) minHalfHeap.peek();
            } else {
                numerator = (double) minHalfHeap.peek() + (double) maxHalfHeap.peek();
            }
    
            if(numerator == 0) {
                return median;
            }
    
            if(isEven) median = numerator / 2.0;
            else median = minHalfHeap.peek();
    
            return median;
        }
    }
    
    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
