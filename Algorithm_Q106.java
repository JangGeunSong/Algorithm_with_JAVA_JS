public class Algorithm_Q106 {
    /*
     * 이 문제는 트리 순회 문제의 기본 문제이다. 이 문제는 "중위순회" 를 통해 문제를 풀이하면 된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
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

    public int minDiffInBST(TreeNode root) {
        // 0 index => 가장 작은 수, 1 index 이전 값
        int[] answer = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        // 가장 작은 결과값을 찾는 함수 실행
        findTwoMinNode(root, answer);

        // 결과를 리턴
        return answer[0];
    }

    // 중위 순회를 통해 테스트 진행
    public void findTwoMinNode(TreeNode node, int[] answer) {
        // 종료조건 node가 null 이면 아래 로직 진행 X
        if(node == null) {
            return;
        }

        // 중위순회 왼쪽 방문, 자기 자신, 오른쪽 노드 방문
        findTwoMinNode(node.left, answer);
        
        // 만약 이전 노드 값이 overflow 조건이 아닌 경우에 
        // 현재값 - 이전 방문 노드 값(이진 검색 트리이므로, 이전 방문 노드가 조금 더 큰 값)
        // 을 구해준다.
        if(answer[1] != Integer.MIN_VALUE) {
            answer[0] = Math.min(answer[0], node.val - answer[1]);
        }

        answer[1] = node.val;

        findTwoMinNode(node.right, answer);

        return;
    }

}
