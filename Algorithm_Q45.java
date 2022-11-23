import java.util.*;

public class Algorithm_Q45 {
    /*
     * 해당 문제는 트리 순회 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/symmetric-tree/
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

    // ArrayList를 사용한 풀이 => 재귀 풀이
    public boolean isSymmetric_recursion(TreeNode root) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        leftBinaryTreeTraverse(root.left, left);
        rightBinaryTreeTraverse(root.right, right);

        if(left.size() != right.size()) {
            return false;
        }

        for(int i = 0; i < left.size(); i++) {
            if(left.get(i) != right.get(i)) {
                return false;
            }
        }

        return true;
    }

    public void leftBinaryTreeTraverse(TreeNode node, List<Integer> list) {
        if(node == null) {
            list.add(101);
            return;
        }

        list.add(node.val);
        leftBinaryTreeTraverse(node.left, list);
        leftBinaryTreeTraverse(node.right, list);
        return;
    }
    
    public void rightBinaryTreeTraverse(TreeNode node, List<Integer> list) {
        if(node == null) {
            list.add(101);
            return;
        }

        list.add(node.val);
        rightBinaryTreeTraverse(node.right, list);
        rightBinaryTreeTraverse(node.left, list);
        return;
    }

    // stack을 이용한 풀이 => 반복문 사용
    public boolean isSymmetric_stack(TreeNode root) {
        // This solution is create to one loop condition code
        // This question is more effective using the solve the recursion solution
        
        TreeNode leftRoot = root.left;
        TreeNode rightRoot = root.right;
        
        if(leftRoot == null && rightRoot == null) {
            return true;
        }
        
        if(leftRoot == null || rightRoot == null) {
            return false;
        }
        
        Stack<TreeNode> leftTree = new Stack<TreeNode>();
        Stack<TreeNode> rightTree = new Stack<TreeNode>();
        
        leftTree.push(leftRoot);
        rightTree.push(rightRoot);
        
        // Inorder traverse to left Tree / reverse inorder traverse to right Tree
        while(!leftTree.isEmpty() && !rightTree.isEmpty()) {
            if(leftRoot.left != null && rightRoot.right != null) {
                leftTree.push(leftRoot.left);
                leftRoot = leftRoot.left;
                
                rightTree.push(rightRoot.right);
                rightRoot = rightRoot.right;
            } else {
                if(leftRoot.left != null && rightRoot.right == null) {
                    return false;
                } else if(leftRoot.left == null && rightRoot.right != null) {
                    return false;
                } else {
                    if(leftTree.peek().val == rightTree.peek().val) {
                        leftRoot = leftTree.pop();
                        rightRoot = rightTree.pop();
                    } else {
                        return false;
                    }
                    
                    if(leftRoot.right != null && rightRoot.left != null) {
                        leftTree.push(leftRoot.right);
                        rightTree.push(rightRoot.left);
                        
                        leftRoot = leftRoot.right;
                        rightRoot = rightRoot.left;
                    } else if(leftRoot.right != null && rightRoot.left == null) {
                        return false;
                    } else if(leftRoot.right == null && rightRoot.left != null) {
                        return false;
                    }
                }
            }
        }
        
        return true;
        
    }
}
