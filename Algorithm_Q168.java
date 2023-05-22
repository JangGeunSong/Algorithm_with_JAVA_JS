import java.util.*;

public class Algorithm_Q168 {
    /*
     * 이 문제는 Map과 우선순위 큐를 사용해서 특정 순위의 빈도수를 랭킹을 구하는 문제다.
     * 여러개의 hashmap과 우선순위 큐를 활용하기 때문에 각 값을 가져오는데 여러번 햇갈리면서 많이 틀렸다.
     * 실수를 줄이기 위해 논리를 정확하게 밟아 나가며 풀이하도록 노력하자.
     * https://leetcode.com/problems/top-k-frequent-elements/
     * 풀이를 확인해보자.
     */

    public int[] topKFrequent(int[] nums, int k) {
        // K위 만큼 자주 나온 숫자를 찾는 문제로, 2개의 hashmap과 하나의 우선순위 큐를 사용해 풀이.
        int[] answer = new int[k];
        // 하나는 각 숫자가 어느 정도의 빈도수를 갖는지를 저장하는 map
        Map<Integer, Integer> frequentMap = new HashMap<>();
        // 또 하나는 현재 size 만큼 차지하는 숫자를 key로 value를 해당 숫자로 저장하는 map 
        Map<Integer, Queue<Integer>> sizeMap = new HashMap<>();
        
        // 가장 빈도수가 잦은 것 순서대로 사이즈를 빼기 위한 우선순위 큐 (우선순위 큐는 오름차순 정렬이라 역순을 취한다.)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 빈도수를 삽입
        for(int num : nums) {
            frequentMap.put(num, frequentMap.getOrDefault(num, 0) + 1);
        }

        // 모든 키를 순회 하면서
        Iterator<Integer> itr = frequentMap.keySet().iterator();

        // 우선순위 큐에 데이터를 삽입처리
        while(itr.hasNext()) {
            int key = itr.next();
            int size = frequentMap.get(key);

            if(sizeMap.get(size) == null) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(key);
                sizeMap.put(size, queue);
            } else {
                Queue<Integer> queue = sizeMap.get(size);
                queue.offer(key);
                sizeMap.put(size, queue);
            }
            
            pq.offer(frequentMap.get(key));
        }

        // k번째 까지 우선순위 큐를 poll 처리 하면서 어느 숫자인지 size 맵에서 데이터를 받아온다.
        for(int i = 0; i < k; i++) {
            int size = pq.poll();

            // sizemap에서 큐를 받고
            Queue<Integer> queue = sizeMap.get(size);

            // 그 값을 배열에 추가
            answer[i] = queue.poll();

            // poll() 된 큐를 다시 맵에 삽입
            sizeMap.put(size, queue);
        }

        // 정답을 리턴한다.
        return answer;
    }
}
