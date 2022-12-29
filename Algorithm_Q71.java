import java.util.*;

public class Algorithm_Q71 {
    /*
     * 해당 문제는 우선순위큐를 활용한 문제이다. OS의 Shortest Job First (SJF)를 구현하는 문제로 잘 기억해 두자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/single-threaded-cpu/
     * 풀이를 확인해보자.
     */

    // 풀이 => 핵심은 sort와 pq의 정렬 방식에 대한 정의를 어떻게 하는가에 있다.
    // 추가로 문제 조건에 맞게 insert 하는 과정도 중요하다.
    public int[] getOrder(int[][] tasks) {
        // 만약 같은 처리시간을 가지면 인덱스가 작은 순으로 처리해야 하므로, 2차원 배열에 col을 3으로 처리한다.
        int size = tasks.length;
        int[][] processMap = new int[size][3];
        int[] answer = new int[size];
        int currentTime = 0;
        int idx = 0;
        int i = 0;

        // 시작 시간, 처리 시간, 프로세스 인덱스를 각각 저장한다.
        for(int k = 0; k < size; k++) {
            processMap[k][0] = tasks[k][0];
            processMap[k][1] = tasks[k][1];
            processMap[k][2] = k;
        }

        // 시작 시간 순으로 배열한다.
        Arrays.sort(processMap, (a, b) -> a[0] - b[0]);
        // priority queue를 만들 때, 2가지 조건을 걸어서 만든다.
        // 1. 만약 처리시간이 같다면 인덱스가 작은거 부터 넣어라
        // 2. 만약 처리시간이 같지 않다면 처리시간이 작은거 부터 넣어라
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);

        // 위 조건에 맞춰 pq에 정보를 삽입한다.
        while(i < size) {
            // 만약 현재 인덱스에 시작 시간이 현재 시간보다 작거나 같다면
            while(i < size && processMap[i][0] <= currentTime) {
                // pq에 이 값을 넣는다.
                pq.offer(processMap[i]);
                i++;
            }

            // 만약 큐가 비어 있다면
            if(pq.isEmpty()) {
                // 현재 시간은 i번째 map이 시작 시간이다
                currentTime = processMap[i][0];
            } else {
                // 비어 있지 않다면 큐에서 하나 빼고 그 뺀 값에 인덱스가 정답의 순서가 된다.
                int[] arr = pq.poll();
                answer[idx] = arr[2];
                idx++;
                // 현재 시간은 프로세스가 처리되는데 걸리는 시간이다.
                currentTime += arr[1];
            }
        }

        // 이러고 나서 남은 pq가 존재하면 전부다 poll 한 후 얻은 index 값이 순서대로 들어간다.
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            answer[idx] = arr[2];
            idx++;
        }
        
        return answer;
    }
}
