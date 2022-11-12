import java.util.*;

public class Algorithm_Q36 {
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

    // 전날 풀었던 논리에서 추가하여 계산하면 빠르게 해결할 수 있는 문제이다.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return answer;
        }
        findAllPath(root, targetSum, targetSum, list, answer);
        return answer;
    }

    public void findAllPath(TreeNode node, int targetSum, int rest_value, List<Integer> list, List<List<Integer>> answer) {
        if(node == null) {
            return;
        }

        // 현재 방문한 노드 정보를 입력
        list.add(node.val);

        // 만약 문제 조건에 맞는 path인 경우 answer에 삽입
        if(node.left == null && node.right == null && rest_value == node.val) {
            answer.add(new ArrayList<>(list));
        }

        // 왼쪽 노드 방문
        findAllPath(node.left, targetSum, rest_value - node.val, list, answer);
        // 오른쪽 노드 방문
        findAllPath(node.right, targetSum, rest_value - node.val, list, answer);
        
        /*
         * 양쪽 노드에 모두 지금 현재 방문했던 node 정보가 필요하기 때문에 
         * 왼쪽, 오른쪽 자식으로 모두 진행이 끝나면 현재 방문했던 노드 정보를 삭제한다.
         * 이렇게 되면, 예시 1 처럼 7번 노드를 방문 했으면, 이 정보가 이제 11번 노드 방문의
         * 왼쪽 노드까지 방문 했을때, 결과로 list에 남아있게 되므로, 없어져야 한다.
         * 따라서, 이것을 위해 list에서 7번 노드 방문 정보를 삭제한다.
         * 이렇게 되는 이유는 객체 List에 대한 참조를 함수의 파라미터로 받기 때문에,
         * add 같은 method로 이 객체 list에 내부 변화가 일어나기 때문이다.
         * 자바는 pass by value를 하는데, 함수에 객체를 전달하면, 그것은 객체의 주소를 값으로 넘기기 때문에,
         * 그 주소에 있는 개체에 method를 통해 속성이 바뀌면 그게 진짜 바뀐다.
         * 이 점을 꼭 기억해야 한다.
         */
        list.remove(list.size() - 1);
    }
}
