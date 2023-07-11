import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Algorithm_Q194 {
    /*
     * DFS, BFS를 이용한 풀이를 작성하면 된다. DFS의 경우 이진 트리의 무방향 간선 그래프 생성에 활용하고, BFS는 특정 거리 K만큼 떨어진 곳의 노드를 찾는데 활용하다.
     * 기초적인 구현 개념만 잘 이해하면 어렵지 않게 구현 가능한 문제이지만, 해결책을 알기 위해서는 고민이 많이 필요한 문제이다. 풀이를 잘 확인해 보자.
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
     * 풀이를 확인해보자.
     */

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> answer = new ArrayList<>();
        Map<Integer, List<TreeNode>> graph = new HashMap<>();
        Map<Integer, Boolean> visitMap = new HashMap<>();

        // DFS를 통해 이진 트리를 무방향 간선 그래프로 생성
        dfs(graph, root, visitMap);

        // BFS를 통해 target 에서 시작해서 거리가 k 만큼 떨어진 곳의 노드들만 선택해 answer에 담는다.
        Queue<Integer> queue = new LinkedList<>();

        queue.add(target.val);
        int size = queue.size();
        int depth = 0;

        while(!queue.isEmpty()) {

            if(depth == k) {
                answer.add(queue.poll());
            } else {
                int node = queue.poll();

                List<TreeNode> edge = graph.get(node);
                visitMap.put(node, true);

                for(int i = 0; i < edge.size(); i++) {
                    int p = edge.get(i).val;
                    if(visitMap.get(p)) {
                        continue;
                    } else {
                        queue.add(p);
                    }
                }
                
                size -= 1;

                if(size == 0) {
                    size = queue.size();
                    depth += 1;    
                }
            }
        }

        return answer;
    }

    // 무방향 간선 그래프를 만들기 위한 dfs 처리
    public void dfs(Map<Integer, List<TreeNode>> graph, TreeNode node, Map<Integer, Boolean> visitMap) {
        if(node == null) {
            return;
        }

        List<TreeNode> a = graph.get(node.val);
        visitMap.put(node.val, false);

        if(a == null) {
            a = new ArrayList<>();
        }
        
        if(node.left != null) {
            List<TreeNode> b = graph.get(node.left.val);
            if(b == null) {
                b = new ArrayList<>();
            }
            a.add(node.left);
            b.add(node);
            graph.put(node.left.val, b);
            dfs(graph, node.left, visitMap);
        }

        if(node.right != null) {
            List<TreeNode> c = graph.get(node.right.val);
            if(c == null) {
                c = new ArrayList<>();
            }
            a.add(node.right);
            c.add(node);
            graph.put(node.right.val, c);
            dfs(graph, node.right, visitMap);
        }

        graph.put(node.val, a);

        return;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
