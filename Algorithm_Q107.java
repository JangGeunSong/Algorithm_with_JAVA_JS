public class Algorithm_Q107 {
    /*
     * 이 문제는 트리 순회 문제의 기본 문제이다.
     * 21년도에 이 문제를 풀 때에는 길게 구현 했었는데, 오늘(23년 2월 18일)은 아주 짧고도 직관적으로 풀이가 나오게 되었다.
     * 그 만큼 성장 했다는 것으로 받아들일 수 있는 부분이라 생각한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/invert-binary-tree/
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

    // 23년 2월 18일 새로 풀었을 때의 답안
    public TreeNode invertTree(TreeNode root) {
        // DFS 모델 & 전위 순회 모델
        // 종료조건 root가 null 이면 null을 return 하며 종료
        if(root == null) {
            return root;
        }

        // 정답인 answer를 만드는 invert Tree 함수를 선언
        // 정답 왼쪽 노드는 원래 트리의 오른쪽 값이, 오른쪽 노드에는 원래 트리의 왼쪽 값이 들어가야 함을 활용해
        // 오브젝트를 만든다.
        TreeNode answer = new TreeNode(root.val, invertTree(root.right), invertTree(root.left));

        // 만들어진 결과를 리턴한다.
        return answer;
    }

    // 21년도의 나의 풀이
    public TreeNode invertTree_old_version(TreeNode root) {
        TreeNode answer = null;
        
        if(root == null) {
            return null;
        }
        
        answer = new TreeNode(root.val, root.right, root.left);
        
        createNewInvertTree(answer.left);
        createNewInvertTree(answer.right);
        
        return answer;
    }
    
    public void createNewInvertTree(TreeNode node) {
        if(node == null) {
            return;
        }
        
        TreeNode leftTmpTreeNode = node.left;
        TreeNode rightTmpTreeNode = node.right;
        
        if(rightTmpTreeNode != null) {
            node.left = new TreeNode(rightTmpTreeNode.val, rightTmpTreeNode.left, rightTmpTreeNode.right);
        } else {
            node.left = null;
        }
        
        if(leftTmpTreeNode != null) {
            node.right = new TreeNode(leftTmpTreeNode.val, leftTmpTreeNode.left, leftTmpTreeNode.right);
        } else {
            node.right = null;
        }

        
        createNewInvertTree(node.left);
        createNewInvertTree(node.right);
        
    }
}
