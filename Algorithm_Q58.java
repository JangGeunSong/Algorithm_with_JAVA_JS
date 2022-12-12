import java.util.*;

public class Algorithm_Q58 {
    /*
     * 해당 문제는 이진 트리에서 특별한 조건을 주고 답을 구하는 문제이다
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/binary-tree-right-side-view/
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

    // 나의 풀이 -> 답안에도 비슷한 답이 있었음
    public List<Integer> rightSideView(TreeNode root) {
        // 오른쪽에 서 있을 때 바라볼 수 있는 트리의 값을 체크한다.
        List<Integer> list = new ArrayList<>();
        
        traverseTree(root, list, 0);

        // 결과 리스트를 반환한다.
        return list;
    }

    public void traverseTree(TreeNode node, List<Integer> list, int depth) {
        if(node == null) {
            return;
        }
        // 만약 list의 사이즈가 depth와 같다면 노드의 값을 리스트에 넣는다.
        if(list.size() == depth) {
            list.add(node.val);
        }
        // 전위 순회를 돌아야 하는데, 오른쪽에 서서 본다고 헀으므로, 오른쪽 노드를 먼저 순회 한다.
        traverseTree(node.right, list, depth + 1);
        // 만약 오른쪽 노드가 있다면, 해당 depth 안에서 이미 list에 값이 있으므로, list.get(depth) != null
        // 아마 if 문을 돌지 않을 것이며, 아니라면 if문을 타고 값을 더하게 된다.
        traverseTree(node.left, list, depth + 1);
        
        return;
    }

    // 큐를 사용한 풀이
    public List<Integer> rightSideView_use_queue(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        
        if (root == null)
            return list;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
    
        while (!queue.isEmpty()) {
            int size = queue.size(); // store the number of nodes of current layer
            TreeNode node = null;
            while (size > 0) {
                node = queue.poll();
                if (node.left != null) // add next layer's node
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                size--;
            }
            list.add(node.val); // add the val of last node
        }
        
        return list;
    }
}
