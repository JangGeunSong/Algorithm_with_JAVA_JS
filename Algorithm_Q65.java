import java.util.*;

public class Algorithm_Q65 {

    // 나의 풀이 -> 계산량이 많아 시간 초과가 남
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> neighbors = null;
        int[] answer = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            neighbors = new ArrayList<>();
            graph.put(i, neighbors);
        }

        for(int i = 0; i < edges.length; i++) {
            List<Integer> a = graph.get(edges[i][0]);
            a.add(edges[i][1]);
            List<Integer> b = graph.get(edges[i][1]);
            b.add(edges[i][0]);
            graph.put(edges[i][0], a);
            graph.put(edges[i][1], b);
        }

        for(int i = 0; i < n; i++) {
            neighbors = graph.get(i);
            int depth = 1;
            int depthLength = 0;
            int nextDepthLength = 0;
            Queue<Integer> queue = new LinkedList<>();
            Arrays.fill(visited, false);
            visited[i] = true;

            for(int node : neighbors) {
                queue.offer(node);
                depthLength += 1;
            }

            while(!queue.isEmpty()) {
                int node = queue.poll();
                visited[node] = true;
                List<Integer> list = graph.get(node);

                for(int k = 0; k < list.size(); k++) {
                    if(list.get(k) == node || visited[list.get(k)]) {
                        continue;
                    }
                    queue.offer(list.get(k));
                    nextDepthLength += 1;
                }

                if(depthLength == 0) {
                    depth += 1;
                    depthLength = nextDepthLength;
                    nextDepthLength = 0;
                }

                depthLength -= 1;
                answer[i] = answer[i] + depth;
            }
        }

        return answer;
    }


    // 시간 초과 없는 정답 코드
    public int[] sumOfDistancesInTree_solve(int n, int[][] edges) {
        // build graph and declare results
        final List<List<Integer>> graph = new ArrayList<>();
        final int[] count = new int[n];
        Arrays.fill(count, 1);
        final int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        postOrder(0, -1, graph, count, answer);
        // after postOrder, only answer[root] is correct, so do preOrder to update answer
        preOrder(0, -1, graph, count, answer, n);

        return answer;
    }

    // set count et subTreeSum, here use answer[]
    private void postOrder(int node, int parent, List<List<Integer>> graph, int[] count, int[] answer) {
        for (int child : graph.get(node)) {
            if (child != parent) {
                postOrder(child, node, graph, count, answer);
                count[node] += count[child];
                answer[node] += answer[child] + count[child];
            }
        }
    }

    private void preOrder(int node, int parent, List<List<Integer>> graph, int[] count, int[] answer, int n) {
        for (int child : graph.get(node)) {
            if (child != parent) {
                answer[child] = answer[node] + (n - count[child]) - count[child];
                preOrder(child, node, graph, count, answer, n);
            }
        }
    }
}
