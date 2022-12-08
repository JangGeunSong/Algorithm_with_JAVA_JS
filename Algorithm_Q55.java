import java.util.*;

public class Algorithm_Q55 {
    /*
     * 해당 문제는 이진트리와 DFS를 통해 원하는 조건을 찾는 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/leaf-similar-trees/
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

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 2개의 leaf node list를 만들어서 각각 leaf node의 값들을 저장한다
        List<Integer> treeValue1 = new ArrayList<>();
        List<Integer> treeValue2 = new ArrayList<>();

        // 이때 저장하는 leaf node 값의 순서는 같은 순회를 통해 돌아야만 정확하게 일치하는지 판별할 수 있다.
        traverseToleaf(root1, treeValue1);
        traverseToleaf(root2, treeValue2);

        // 만약 두 리스트의 사이즈가 같지 않다면 어차피 이 tree는 Leaf-Similar 하지 않다. 
        if(treeValue1.size() != treeValue2.size()) {
            return false;
        }

        // 만약 전체 순회를 돌때 리스트의 요소 하나라도 값이 다르다면 곧바로 Leaf-Similar 하지 않다고 리턴
        for(int i = 0; i < treeValue1.size(); i++) {
            if(!treeValue1.get(i).equals(treeValue2.get(i))) {
                return false;
            }
        }

        // 2개의 판별이 모두 통과되면 Leaf-Similar 하다.
        return true;
    }

    // 순회를 돌면서 리스트에 값을 추가
    public void traverseToleaf(TreeNode node, List<Integer> list) {
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        traverseToleaf(node.left, list);
        traverseToleaf(node.right, list);
        return;
    }
}
