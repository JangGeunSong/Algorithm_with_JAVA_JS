import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Algorithm_Q189 {
    /*
     * 이 문제는 다익스트라 알고리즘 혹은 벨만 포드 알고리즘을 활용해 풀이하는 문제이다.
     * https://leetcode.com/problems/path-with-maximum-probability/
     * 풀이를 확인해보자.
     */

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();

        // 무방향 그래프 초기화 진행
        for(int i = 0; i < n; i++) {
            List<Pair<Integer, Double>> edge = new ArrayList<>();
            graph.put(i, edge);
        }

        // 각 그래프 노드별로 edge에 대해 현재 edge의 가중치 즉 확률을 짝으로 만들어 데이터를 저장한다.
        // 이때 해당 노드에 그런 간선이 여러개일 것이므로, list로 저장한다.
        // 또한 무방향이므로 양쪽 노드에 모두 해당 정보를 저장한다.
        for(int i = 0; i < edges.length; i++) {
            int p = edges[i][0];
            int q = edges[i][1];

            List<Pair<Integer, Double>> pEdge = graph.get(p);
            List<Pair<Integer, Double>> qEdge = graph.get(q);

            Pair<Integer, Double> pPair = new Pair<>(q, succProb[i]);
            Pair<Integer, Double> qPair = new Pair<>(p, succProb[i]);

            pEdge.add(pPair);
            qEdge.add(qPair);

            graph.put(p, pEdge);
            graph.put(q, qEdge);
        }

        // 다익스트라 알고리즘 실행
        // 각 노드별로 최대 확률을 저장
        double[] maxProb = new double[n];
        // 시작점은 1.0 으로 초기화 한 후 진행
        maxProb[start] = 1.0;

        // 우선순위 큐 선언, 원소는 Pair로 하되 그래프와는 달리 key를 현재 노드에서의 확률
        // 그리고 value를 현재 노드의 번호로 지정하고, key값을 통해 우선순위 정렬을 진행한다.
        PriorityQueue<Pair<Double, Integer>> pq = new PriorityQueue<>((a, b) -> -Double.compare((Double)a.getKey(), (Double)b.getKey()));
        pq.add(new Pair<>(1.0, start));

        // 해당 큐가 텅 빌때 까지 진행
        while(!pq.isEmpty()) {
            // 현재 도달 노드에서 확률과 노드 번호를 가진 짝을 뺴온다.
            // 이떄 해당 페어는 가장 큰 확률을 가진것 부터 뺀다.
            Pair<Double, Integer> cur = pq.poll();
            double curProb = (Double) cur.getKey();
            int curNode = (Integer) cur.getValue();

            // 지금 노드가 목적지라면 지금 현 시점의 확률을 리턴
            if(curNode == end) {
                return curProb;
            }

            // 그게 아니면 이웃한 모든 페어를 순회돌면서 데이터 처리
            for(Pair<Integer, Double> next : graph.get(curNode)) {
                int nextNode = (Integer) next.getKey();
                double pathProb = (Double) next.getValue();

                // 지금 노드 페어에서 얻은 확률 * 해당 간선의 가중치 확률 > 다음 도달 노드의 최대 확률
                // 위와 같다면 확률을 갱신하고 우선순위 큐에 데이터를 넣는다.
                if(curProb * pathProb > maxProb[nextNode]) {
                    maxProb[nextNode] = curProb * pathProb;
                    pq.add(new Pair<>(maxProb[nextNode], nextNode));
                }
            }
        }

        // 반복문에서 빠져 나왔다면 start에서 end까지 갈 방법이 없는 것이므로 0을 리턴
        return 0.0;
    }

    public class Pair<T1, T2> {
        private Object x;
        private Object y;

        Pair(Object x, Object y) {
            this.x = x;
            this.y = y;
        }

        public Object getKey(){
            return x;
        }

        public Object getValue(){
            return y;
        }

    }
}
