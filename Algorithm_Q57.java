public class Algorithm_Q57 {
    /*
     * 해당 문제는 이진트리와 DFS를 통해 원하는 조건을 찾는 문제이다.
     * 이진 트리 문제는 많이 풀었다고 생각 했는데 여전히 어렵게 풀리는 문제가 있다.
     * 해당 문제는 힌트들을 참고해서 풀이했고, 풀이도 참고해서 함께 풀이했다 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
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

    // 풀이 코드
    public int maxProduct(TreeNode root) {
        // index 0 is whole summation value of nodes
        // index 10 is maximum product value of trees
        long[] answer = {0, 0};
        traverseWholeValueForNode(root, answer);
        findMaximumProduct(root, answer);
        return (int) (answer[1] % (1e9 + 7));
    }

    public void traverseWholeValueForNode(TreeNode node, long[] answer) {
        // 트리 전체의 합을 구한다.
        if(node == null) {
            return;
        }
        answer[0] += node.val;
        traverseWholeValueForNode(node.left, answer);
        traverseWholeValueForNode(node.right, answer);
        return;
    }

    public long findMaximumProduct(TreeNode node, long[] answer) {
        // 후위 순회 (port-order traverse)를 진행
        if(node == null) {
            return 0;
        }
        // 만약 자식이 있는 노드라면 자식까지 포함해서 서브 트리가 구성되는 것이기 때문에 
        // 자식까지 포함한 값이 일단 left 혹은 right에 들어 있어야 한다.
        long left = findMaximumProduct(node.left, answer);
        long right = findMaximumProduct(node.right, answer);
        // 현재 서브트리가 구성된 상태에서 최선의 최대값을 구한다.
        // answer[1] => 현재까지의 최대 곱
        // left + right + node.val 현재 서브트리의 값
        // answer[1] - 현재 서브트리의 값 => 전체 값 - 현재 서브트리의 값 == 보수
        answer[1] = Math.max(answer[1], (left + right + node.val) * (answer[0] - left - right - node.val));

        // left 혹은 right의 서브트리 값이 떨어진다.
        return left + right + node.val;
    }
}
