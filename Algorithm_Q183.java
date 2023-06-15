import java.util.ArrayList;
import java.util.List;

public class Algorithm_Q183 {
    /*
     * 이 문제는 트리 순회를 통해 풀이하는 문제이다.
     * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
     * 풀이를 확인해보자.
     */

    public int maxLevelSum(TreeNode root) {
        // level을 언급했으므로, level order 순회를 진행
        List<Integer> levelSum = new ArrayList<>();
        int answer = 0;
        int currVal = Integer.MIN_VALUE;
        traverseToNode(root, levelSum, 0);

        // 각 레벨별 결과값을 비교해서 최대 값인 지점에 인덱스를 구한다.
        for(int i = 0; i < levelSum.size(); i++) {
            if(currVal < levelSum.get(i)) {
                answer = i;
                currVal = levelSum.get(i);
            }
        }

        // root 가 level 1 이므로, 배열의 인덱스에 1 을 더한게 답
        return answer + 1;
    }

    // 레벨별 순회를 진행하면서 각 레벨의 합을 구해 갖고 온다.
    public void traverseToNode(TreeNode node, List<Integer> levelSum, int level) {
        if(node == null) {
            return;
        }

        if(levelSum.size() == 0 || levelSum.size() == level) {
            levelSum.add(node.val);
        } else {
            int val = levelSum.get(level);
            levelSum.set(level, val + node.val);
        }

        traverseToNode(node.left, levelSum, level + 1);
        traverseToNode(node.right, levelSum, level + 1);

        return;
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
