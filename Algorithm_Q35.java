public class Algorithm_Q35 {
    /*
     * 해당 문제는 트리의 전위순회를 통해 풀 수 있는 문제이다.
     * 다만 path에 도달 조건을 만족하면서, 동시에 결과가 원하는 형태로 나오는가를 반드시 체크해야 하므로, 이 조건에 대해 고민할 필요가 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/path-sum/
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

    // 아래 함수들을 진행하게 되면 call by value에 의해 파라미터로 넘어간 변수는 별도의 메모리 공간에
    // 따로 저장되어, 함수가 실행될 때 파라미터로 넘어온 값을 변경해도 원레 main 함수 내부에서는 값이
    // 변하지 않는다. 특히. primitive 변수들은 무조건 value가 넘어가게 되어 있어, 이에 주의한다.
    // 따라서, class 내부의 private 변수로 두어, 특정 메모리 주소에 매핑된 answer 변수를 계속 사용하도록 만든다.
    private boolean answer = false;
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        int rest_value = targetSum;

        traverseBinaryTreeForSum(root, targetSum, rest_value);

        return answer;
    }

    public void traverseBinaryTreeForSum(TreeNode node, int targetSum, int rest_value) {
        // 만약 아래 로직을 진행하는 중 혹은 Root 노드가 null 이 된다면, 아래 함수를 실행할 수 없기 때문에
        // 바로 함수 종료 시킨다.
        if(node == null) {
            return;
        }
        
        // leaf에 도달해서 더 이상 갈 길이 없다는 것은 왼쪽과 오른쪽 자식 노드가 null이라는 것을 의미한다.
        // 위 조건에 모두 충족할 때, 동시에 남은 값 이 현재 노드의 값과 같다면, 이 경로가 targetSum을 구할 수
        // 있는 경로가 된다는 것이다.
        if(node.left == null && node.right == null && node.val == rest_value) {
            answer = true;
        }

        // 만약 위 if문을 만족하지 않는다면, 전위 순회를 돌아야 하므로, 왼쪽 자식 노드와 오른쪽 자식 노드로
        // 이동하는 재귀 함수를 실행한다. 이때, rest_value는 현재 rest_value - 현재 노드의 값을 해서 해당 노드에
        // 도달해서 작업을 했다는 것을 확인 한다.
        traverseBinaryTreeForSum(node.left, targetSum, rest_value - node.val);
        traverseBinaryTreeForSum(node.right, targetSum, rest_value - node.val);
    }
}
