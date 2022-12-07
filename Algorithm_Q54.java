public class Algorithm_Q54 {
    /*
     * 해당 문제는 이진트리와 DFS를 통해 원하는 조건을 찾는 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/range-sum-of-bst/
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

    // 나의 풀이
    public int rangeSumBST_mine(TreeNode root, int low, int high) {
        int[] answer = {0};

        // 어떤 순회든 상관 없고 원하는 조건만 맞으면 된다.
        sumOfNodes(root, low, high, answer);

        return answer[0];
    }

    // 간단하게 전위순회로 문제를 해결
    public void sumOfNodes(TreeNode node, int low, int high, int[] sum) {
        if(node == null) {
            return;
        }

        if(low <= node.val && node.val <= high) {
            sum[0] += node.val;
        }

        sumOfNodes(node.left, low, high, sum);
        sumOfNodes(node.right, low, high, sum);

        return;
    }

    // 더 짧은 풀이 => 별도의 추가 함수를 더 만들지 않고 자체 함수로 풀이 처리
    public int rangeSumBST_shortVersion(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= low && root.val <= high) sum += root.val;
        if (root.val > low) sum += rangeSumBST_shortVersion(root.left, low, high);
        if (root.val < high) sum += rangeSumBST_shortVersion(root.right, low, high);

        return sum;
    }
}
