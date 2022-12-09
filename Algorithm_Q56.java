public class Algorithm_Q56 {
    /*
     * 해당 문제는 이진트리와 DFS를 통해 원하는 조건을 찾는 문제이다.
     * 지난 이틀동안 풀었던 문제보다 더 조건을 복잡하게 설정하였다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
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
    public int maxAncestorDiff_mine(TreeNode root) {
        // 0's index : maximum ancestor value
        // 1's index : minumum ancestor value
        // 2's index : maximum Diff value 
        int[] answer = {-1, -1, -1};

        answer[0] = root.val;
        answer[1] = root.val;

        findMaxDiff(root.left, answer);
        findMaxDiff(root.right, answer);

        return answer[2];
    }

    public void findMaxDiff(TreeNode node, int[] answer) {
        // 탈출 조건 node가 null 이면 아래 로직을 실행하지 못하도록 처리
        if(node == null) {
            return;
        }

        // 조상과 자식인 나의 차이를 계산
        int calcDiffMax = Math.abs(answer[0] - node.val);
        int calcDiffMin = Math.abs(answer[1] - node.val);
        int maxCalcDiff = 0;

        // 두 차이 중 가장 갭이 큰 값을 max Diff 값으로 선정해서 현재 정답으로 저장된 값과 비교하도록 진행
        if(calcDiffMax >= calcDiffMin) {
            maxCalcDiff = calcDiffMax;
        } else {
            maxCalcDiff = calcDiffMin;
        }

        // 만약 이렇게 계산된 값이 가장 큰 값이라면, answer 안에 값을 설정 아니라면 그냥 원레 answer 값으로 다시 저장
        if(answer[2] <= maxCalcDiff) {
            answer[2] = maxCalcDiff;
        }

        // 조상 중 가장 큰 값으로 일단 변경 후 자식으로 진행 해야 함.
        int prevLargistAncestor = -1;
        int prevSmalliestAncestor = -1;
        if(node.val > answer[0]) {
            // 만약 모든 함수가 끝나게 된다면 다시 answer[0] 에는 현재 내가 더 커서 갱신된 이 조상 값을 원복 해야 한다.
            // 따라서 이전 최대 조상값을 저장한다
            prevLargistAncestor = answer[0];
            answer[0] = node.val;
        }
        // 마찬가지로 최소값 조상이 갱신되어야 한다면 이도 반영한다
        if(node.val < answer[1]) {
            prevSmalliestAncestor = answer[1];
            answer[1] = node.val;
        }
        // 노드 전체 순회 진행 왼쪽노드 & 오른쪽 노드
        findMaxDiff(node.left, answer);
        findMaxDiff(node.right, answer);
        // 만약 내가 가장 큰 조상 노드가 되어서 answer에 반영 되어 있었다면 이제 조상으로 돌아가는 상황이므로
        // 최대 조상 값은 원복해야 한다
        if(prevLargistAncestor != -1) {
            answer[0] = prevLargistAncestor;
        }
        // 혹은 가장 작은 조상일 수도 있으므로, 이 역시 원복이 필요하면 진행한다
        if(prevSmalliestAncestor != -1) {
            answer[1] = prevSmalliestAncestor;
        }
    }

    // 짧게 처리할 수 있는 풀이 방법
    

    private int max = Integer.MIN_VALUE;

    public int maxAncestorDiff_short(TreeNode root) {
        helper(root, root.val, root.val);
        return max;
    }

    private void helper(TreeNode root, int ancestorMax, int ancestorMin){
        if(root == null) return;
        max = Math.max(max, Math.abs(root.val - ancestorMax));
        max = Math.max(max, Math.abs(root.val - ancestorMin));
        int maximumValueToPass = Math.max(root.val, ancestorMax);
        int minimumValueToPass = Math.min(root.val, ancestorMin);
        helper(root.left, maximumValueToPass, minimumValueToPass);
        helper(root.right, maximumValueToPass, minimumValueToPass);
    }

}
