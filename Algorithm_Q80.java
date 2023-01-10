public class Algorithm_Q80 {
    /*
     * 기본적인 이진트리 순회 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/same-tree/
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 재귀를 통해 순회를 진행하여 판별을 진행한다.
        boolean[] answer = new boolean[1];

        answer[0] = true;

        checkSameTree(p, q, answer);

        return answer[0];
    }

    public void checkSameTree(TreeNode node1, TreeNode node2, boolean[] answer) {
        // 종료 조건 1 : 두 노드가 모두 null
        if(node1 == null && node2 == null) {
            return;
        }

        // 종료조건 2 노드 중 1개가 null => 무조건 두 트리는 같지 않다. 왜냐하면 같은 순회를 돌고 있기 때문이다.
        if(node1 == null && node2 != null) {
            answer[0] = false;
            return;
        }

        if(node1 != null && node2 == null) {
            answer[0] = false;
            return;
        }

        // 종료조건 3 : 두 노드의 값이 다를때
        if(node1.val != node2.val) {
            answer[0] = false;
            return;
        }

        // 위 조건 중 하나도 만족하지 않으면 왼쪽, 오른쪽 순회 진행
        checkSameTree(node1.left, node2.left, answer);
        checkSameTree(node1.right, node2.right, answer);
    }
}
