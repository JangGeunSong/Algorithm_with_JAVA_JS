import java.util.*;

public class Algorithm_Q91 {
    /*
     * 이 문제는 그래프와 BFS를 통해 원하는 조건을 찾아 풀이하는 문제이다.
     * 나는 DFS로 풀어서 맞추기는 했으나 채점 과정에서 TLE가 나와 BFS 코드를 참고하여 답을 얻었다.
     * 관련한 내용을 확인하도록 하자. 특히, 해당 문제는 다익스트라 알고리즘을 활용해도 된다고 하니 이도 확인하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/
     * 풀이를 확인해보자.
     */

    // BFS 정답 코드
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // BFS를 활용한 풀이를 진행한다.
        // BFS는 queue를 통해 현재 노드를 방문하면 이웃 노드 전체를 queue에 삽입해 순회하는 방법이다.
        // 해당 순회를 위해서는 queue가 비어있지 않다면 계속 진행한다는 점을 기반으로 생각해야 한다.
        // 특히, 무한 루프를 돌지 않도록 하기 위해 어떻게 방어할지를 고민하는게 핵심이다.
        int answer = -1;
        List<List<int[]>> flightsInfo = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        // 비행기 정보를 담기 위한 리스트 선언
        for(int i = 0; i < n; i++) {
            flightsInfo.add(new ArrayList<>());
        }

        // 비행기 정보 리스트 안에 실제 내용을 담는다
        for(int i = 0; i < flights.length; i++) {
            flightsInfo.get(flights[i][0]).add(new int[] {flights[i][1], flights[i][2]});
        }

        // 각 지점에서의 최소값을 저장하는 배열 선언
        int[] minCost = new int[n];

        // 최소값을 구하기 위해 모든 원소에 정수 최대값으로 초기화
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // queue에 시작 위치 , 0원 으로 시작
        queue.offer(new int[] {src, 0});
        // 지나온 거점 0개로 시작
        int stops = 0;

        // BFS 진행
        while(!queue.isEmpty() && stops <= k) {
            // queue의 사이즈를 받아서
            int size = queue.size();
            // queue의 사이즈 가 0보다 큰 동안
            while(size-- > 0) {
                // queue에서 비행 정보를 가져온다
                int[] flightInfo = queue.poll();
                // 비행 정보를 순회 돌면서
                for(int i = 0; i < flightsInfo.get(flightInfo[0]).size(); i++) {
                    int[] ticketInfo = flightsInfo.get(flightInfo[0]).get(i);
                    int place = ticketInfo[0];
                    int price = ticketInfo[1];
                    // 각 위치별로 얻게되는 비행 비용을 구하고, 이의 최소값을 저장한다.
                    if(flightInfo[1] + price >= minCost[place]) {
                        continue;
                    }
                    minCost[place] = flightInfo[1] + price;
                    // 최소값이 나온 지점에서 BFS를 돌 수 있도록 queue에 추가
                    queue.offer(new int[] {place, minCost[place]});
                }
            }
            // 거점 하나를 지나왔으므로, stops에 1을 더한다
            stops += 1;
        }

        // 모든 BFS가 종료되었을 때 목적지에 최소 가격이 정수의 최대값이라면 이는 해당 경로로 오지 못했다는 것을 의미
        if(minCost[dst] == Integer.MAX_VALUE) {
            // 즉 -1을 정답에 담는다.
            answer = -1;
        } else {
            // 그게 아니라면 최소 값이 있으므로 이를 정답에 담는다.
            answer = minCost[dst];
        }

        // 정답 리턴
        return answer;
    }

    // TLE가 나오는 DFS 코드
    public int findCheapestPrice_wrong(int n, int[][] flights, int src, int dst, int k) {
        int answer = -1;
        // 방문 여부를 체크할 배열 선언
        boolean[] visited = new boolean[n];
        // row => from, col => to
        int[][] priceBoard = new int[n][n];
        // 간선 정보를 저장할 그래프 선언
        List<List<Integer>> graph = new ArrayList<>();

        // from to 가격표 초기화 -> 모든 표를 다 -1로 처리 향후 가격표를 봤을 때 -1이 보인다면
        // 이는 from 에서 to로 가는 간선이 없다는 의미가 된다.
        // 추가로 행선지를 기록하는 리스트를 각 도시마다 추가한다.
        for(int i = 0; i < n; i++) {
            Arrays.fill(priceBoard[i], -1);
            graph.add(new ArrayList<>());
        }

        // from to 가격표 설정 & 각 src에서 갈 수 있는 행선지 기록
        for(int i = 0; i < flights.length; i++) {
            priceBoard[flights[i][0]][flights[i][1]] = flights[i][2];
            graph.get(flights[i][0]).add(flights[i][1]);
        }

        // 모든 도시를 미방문 처리
        Arrays.fill(visited, false);

        // 최소 가격을 찾는 메서드 실행
        answer = findPrice(graph, visited, src, dst, k, priceBoard, 0, Integer.MAX_VALUE);

        return answer;
    }

    public int findPrice(List<List<Integer>> graph, boolean[] visited, int curr, int dst, int k, int[][] priceBoard, int price, int minPrice) {
        
        // 종료 조건 1 이미 방문한 경우 -1을 리턴 왜냐하면 src와 dst는 같을 수 없음
        if(visited[curr]) {
            return -1;
        }

        // 종료조건 2 만약 거점을 지나온 갯수가 -1보다 클 때 목적지에 도달하는 순간
        if(curr == dst) {
            if(k >= -1) {
                minPrice = Math.min(price, minPrice);
                return minPrice;
            }
        }

        // 현재 도시 방문 처리
        visited[curr] = true;

        for(int i = 0; i < graph.get(curr).size(); i++) {
            // 새로운 가격을 받아온다.
            // 이 가격은 현재 graph 안에서 curr 간선정보를 받아온 후
            // 그 간선정보를 순회하면서 가격을 받아와서 계산하여 재귀를 돌린다.
            // 이렇게 얻어진 최종 new price를 현재 min price와 비교해서 가장 작은 값을 반환한다.
            // 이때 만약 new price가 -1 이라면 해당 간선 정보를 타고 지나갔을 때 목적지에 도착 못하는 
            // 것이므로, 가격 정보를 받아올 필요가 없다.
            // 특히 해당 간선 정보를 통해 1개 도시를 지나가므로 k 에 1을 빼야 한다.
            int newPrice = findPrice(graph, visited, graph.get(curr).get(i), dst, k - 1, priceBoard, price + priceBoard[curr][graph.get(curr).get(i)], minPrice);
            if(newPrice == -1) {
                continue;
            } else {
                minPrice = Math.min(minPrice, newPrice);
            }
        }
        
        // 모든 간선 정보를 다 받아와서 돌아 봤으므로, 현재 도시를 방문하지 않은것으로 처리
        visited[curr] = false;

        // 만약 현재 간선 정보 리스트의 크기가 0 이라면
        if(graph.get(curr).size() == 0) {
            return -1;
        }

        // 최소 가격을 리턴
        return minPrice;
    }
}
