import java.util.*;

public class Algorithm_Q105 {
    /*
     * 이 문제는 매우 간단한 트리 순회 문제이다. 트리의 순회 방법만 알면 곧바로 풀 수 있는 문제이므로, 개념에 집중하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     * 풀이를 확인해보자.
     */

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

    // 풀이 코드 => DFS 모델
    public int maxDepth(TreeNode root) {
        // 최종 depth를 얻을 수 있는 answer를 선언
        int[] answer = {0};

        // 재귀로 전위순회를 돌아서 depth를 찾는다.
        goDepthOfTree(root, 0, answer);

        // answer를 리턴 한다.
        return answer[0];
    }

    public void goDepthOfTree(TreeNode node, int curr, int[] answer) {
        // 종료조건 : 현재 방문한 node가 null 이면 아래 로직을 진행하지 않는다.
        if(node == null) {
            return;
        }

        // 현재 depth를 얻기위해 parameter 에 1을 더한다.
        curr += 1;

        // 최종 depth와 비교해서 큰 값으로 교체
        answer[0] = Math.max(curr, answer[0]);

        // 위 과정이 방문 처리이므로, 전위 순회로서 이제 왼쪽, 오른쪽 자식 노드를 순회 진행한다.
        goDepthOfTree(node.left, curr, answer);
        goDepthOfTree(node.right, curr, answer);

        return;
    }

    // 풀이 코드 => BFS 모델
    public int maxDepth_BFS(TreeNode root) {
        // BFS 모델 예시
        int depth = 0;

        // root 노드가 null 이면 무조건 depth는 0
        if(root == null) {
            return depth;
        }

        // BFS를 만들기 위해 queue를 선언
        Queue<TreeNode> queue = new LinkedList<>();

        // root 노드 넣고
        queue.offer(root);

        // queue가 모두 없어질때 까지
        while(!queue.isEmpty()) {
            // 현재 큐에 사이즈를 체크
            int size = queue.size();
            // 이 사이즈만큼의 순회를 돌 때에는 depth가 동일
            depth += 1;
            // 현재 큐 사이즈 만큼 for문을 돌면서 큐를 다 방문하고(인접 노드 방문 처리)
            // 이떄 얻은 자식 노드들은 모두 큐에 새로 담는다. 다만, null인 자식 노드는 담지 않는다.
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // 최종 depth를 리턴
        return depth;
    }
}
