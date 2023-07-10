import java.util.LinkedList;
import java.util.Queue;

public class Algorithm_Q193 {
    /*
     * DFS, BFS를 이용한 풀이를 작성하면 된다. DFS의 경우 조건을 잘못 잡으면 계속 답이 안나오는 무한 반복이 될 수 있으니 아래 풀이를 잘 살펴 보자.
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     * 풀이를 확인해보자.
     */

    // BFS 풀이
    public int minDepth_BFS(TreeNode root) {
        // 종료 조건 1. root가 null 이면 0으로 리턴
        if(root == null) {
            return 0;
        }
        
        // BFS를 위해 큐를 생성
        Queue<TreeNode> queue = new LinkedList<>();
        // 종료 조건 1에 해당하지 않으므로, 깊이는 무조건 최소 1 이상이다. 따라서 answer에 값을 1로 초기화
        int answer = 1;
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            // 현재 level 에서 접근 가능한 모든 노드의 수를 구하고 그들의 인접 노드를 모두 순회
            while(size > 0) {
                // 큐 사이즈를 1 줄이고 노드를 받는다.
                size -= 1;
                TreeNode node = queue.poll();

                // 노드가 null이면 그냥 pass
                if(node == null) {
                    continue;
                }

                if(node.left == null && node.right == null) {
                    // 만약 지금 node가 leaf 노드라면 현재의 depth를 저장하고 있는 answer를 리턴한다.
                    return answer;
                }

                // 만약 그렇지 않다면 양쪽 자식을 모두 큐에 삽입한다.
                queue.add(node.left);
                queue.add(node.right);
            }

            // 현재 depth에서 leaf 노드를 못 찾았으므로, depth를 1 올린다.
            answer += 1;
        }

        // 어떠한 결론이 나오지 않는 경우는 없지만, 그냥 answer로 처리
        return answer;
    }

    // DFS 풀이
    public int minDepth_DFS(TreeNode root) {
        int answer = dfs(root);

        return answer;
    }

    public int dfs(TreeNode node) {
        // 종료조건 1. 자기 자신이 null 일때 0으로 리턴
        if(node == null) {
            return 0;
        }

        // 양쪽 자식중 하나라도 null 이면 다른쪽 자식을 활용해서 깊이를 얻는다.
        if(node.left == null) {
            return 1 + dfs(node.right);
        } else if(node.right == null) {
            return 1 + dfs(node.left);
        }

        // 양쪽 모두 자식이 존재할 때에는 그중 최소 깊이인 것만 dfs로 선택한 후 1 을 더해서 깊이를 얻는다.
        return 1 + Math.min(dfs(node.left), dfs(node.right));
    }
    
    
    
    
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
