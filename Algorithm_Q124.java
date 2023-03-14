public class Algorithm_Q124 {
    /*
     * 이 문제는 트리를 활용하여 결과를 얻어내는 문제이다.
     * leaf node가 아니라면 값을 얻지 말아야 한다는 조건에 유의하면서 풀이를 살펴보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int sumNumbers(TreeNode root) {
        // 가장 끝까지 도달 했을때 만들어지는 숫자들의 합을 구한다.
        return getSumOfNumbers(root, 0);
    }

    public int getSumOfNumbers(TreeNode node, int value) {
        // 종료 조건 1 node 자체가 null 일때
        if(node == null) {
            // value를 리턴
            return value;
        }

        // 종료 조건 2 현재 노드의 자식 노드가 모두 null
        if(node.left == null && node.right == null) {
            // 지금 value는 10이 곱해진 상태로 현재 방문한 노드의 value를 더해서 결과를 리턴
            return value + node.val;
        }

        // 이 외에는 아직 끝 값이 아니므로, value 에 node.val을 더한 후 아래 2개를 진행한다.
        value = value + node.val;

        // 왼쪽 자식노드들의 끝값 끼리의 합 + 오른쪽 자식 노드들의 끝값 끼리의 합을 구한다.
        int leftSum = 0;
        int rightSum = 0;

        if(node.left == null) {
            // 만약 왼쪽 자식이 null 이면 오른쪽 자식만 재귀실행
            rightSum = getSumOfNumbers(node.right, value * 10);
        } else if(node.right == null) {
            // 오른쪽 자식이 null 이면 왼쪽 자식만 재귀실행
            leftSum = getSumOfNumbers(node.left, value * 10);
        } else {
            // 둘 다 null 이 아니면 둘 다 재귀실행
            leftSum = getSumOfNumbers(node.left, value * 10);
            rightSum = getSumOfNumbers(node.right, value * 10);
        }

        // 2개의 자식 합을 더해서 결과를 리턴
        return leftSum + rightSum;
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
