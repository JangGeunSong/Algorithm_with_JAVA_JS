import java.util.*;

public class Algorithm_Q16 {
    /*
     * 해당 문제는 트리의 순회 문제이다. 그 중 중위순회를 다루고 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * 재귀의 경우 매우 쉽다. 솔직히 이론만 알면 바로 구현 된다.
     * 하지만, 반복문은 다르다. 반복문을 어떻게 구현해 놨는지를 유심히 살펴보자.
     */ 

    /**
     * Definition for a binary tree node.
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

    /*
     * 재귀를 통한 구현
     */
    List<Integer> answer = new ArrayList<>();

    public List<Integer> inorderTraversal_Recursive(TreeNode root) {
        if(root == null) {
            return answer;
        }

        inorderTraversal_Recursive(root.left);
        answer.add(root.val);
        inorderTraversal_Recursive(root.right);

        return answer;
    }

    /*
     * 반복문을 통한 구현
     */
    public List<Integer> inorderTraversal_Iteratively(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Stack<TreeNode> ptr = new Stack<>();
        if(root == null) {
            return answer;
        }

        while(root != null || !ptr.isEmpty()) {
            while(root != null) {
                ptr.push(root);
                root = root.left;
            }
            root = ptr.pop();
            answer.add(root.val);
            root = root.right;
        }

        return answer;
    }
}
