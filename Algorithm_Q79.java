import java.util.*;

public class Algorithm_Q79 {
    /*
     * 기본적인 이진트리 순회 문제이다. 반복문으로 구현하는 방식을 잘 살펴보자
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
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

    // 나의 풀이 1
    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        // 재귀를 통한 풀이
        List<Integer> answer = new ArrayList<>();
        traverse(root, answer);
        return answer;
    }

    public void traverse(TreeNode node, List<Integer> list) {
        if(node == null) {
            return;
        }

        // preorder는 자기 자신을 방문하고, 왼쪽, 오른쪽 순으로 방문을 진행한다
        list.add(node.val);
        traverse(node.left, list);
        traverse(node.right, list);
    }

    // 나의 풀이 2
    public List<Integer> preorderTraversal_iteratively(TreeNode root) {
        // 반복문을 이용한 풀이
        List<Integer> answer = new ArrayList<>();
        TreeNode tmp = null;
        // stack을 통해 부모 노드 및 방문이 필요한 노드를 저장한다.
        Stack<TreeNode> stack = new Stack<>();

        // 만약 root가 null 이라면 그냥 현재 만들어진 텅 빈 리스트를 리턴한다.
        if(root == null) {
            return answer;
        }

        // root가 null 이 아니라면, root 값을 일단 answer 리스트에 담고
        answer.add(root.val);
        // 왼쪽, 오른쪽 순으로 방문 하므로, 우선 stack의 성질을 활용해 오른쪽 노드, 왼쪽 노드 순으로 push 한다.
        stack.push(root.right);
        stack.push(root.left);
        // 모든 스택의 엘리먼트가 없어질 때 까지
        while(!stack.isEmpty()) {
            // 가장 위의 stack 엘리먼트를 pop 한다
            tmp = stack.pop();
            if(tmp != null) {
                // 이 pop 한 값이 null이 아니라면 현재 node 값을 list에 담고
                // 현재 node의 오른쪽, 왼쪽 순으로 담는다 (stack의 성질과 preorder의 개념에 의해)
                answer.add(tmp.val);
                stack.push(tmp.right);
                stack.push(tmp.left);
            }
        }

        return answer;
    }
}
