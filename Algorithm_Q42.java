public class Algorithm_Q42 {
    /*
     * 해당 문제는 중위 순회를 활용해 풀이하면 되는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
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

    /*
     * 여기서는 2가지 방법의 풀이를 진행 했다.
     * 첫 번째는 class 변수를 두고 원하는 답을 찾는 방법
     * 두 번째는 함수 안에서 만든 변수를 통해 답을 찾는 방법
     * 이 2가지를 모두 기억해 두자.
     * 풀이에 대한 내용은 주석으로 달아 두었다.
     */
    private int count = 0;
    private int answer = 0;

    public int kthSmallest_mine(TreeNode root, int k) {
        // 답은 클래스의 private 변수를 통해 return 하도록 처리
        findKthElement(root, k);
        return answer;
    }

    public void findKthElement(TreeNode node, int k) {
        // 만약 node가 null이면 연산 필요 없으므로, pass
        if(node == null) {
            return;
        }

        // 중위 순회를 진행한다.
        // 중위 순회 -> left => node => right 순으로 순회 하는 것
        findKthElement(node.left, k);
        // 현재 node에 도달했고 이게 몇번째 크기인지 체크하기 위해 count에 1을 더한다.
        count += 1;
        // 0부터 시작해서 k번째 도착한 노드가 정답 노드가 되므로, 이때 그 값을 answer에 삽입한다.
        if(count == k) {
            answer = node.val;
            return;
        }
        findKthElement(node.right, k);
    }

    // 전역 변수를 두지 않고, 오직 함수안 내부의 변수만을 사용하여 답을 얻는 방법
    public int kthSmallest_no_class_param(TreeNode root, int k) {
        // Java에서 array는 passing a reference로 움직이기 때문에
        // 함수의 parameter로 삽입되어 변형이 일어나면, 그 값이 그대로 반영 된다.
        // primitive 타입인 int가 인자로 넘어가면 당연히 변화가 없지만, 배열은 반영 되므로, 이를 사용해
        // class 내부의 변수로 처리하지 않고 해결할 수 있다.
        int[] finder = {0, -1};
        // 답은 클래스의 private 변수를 통해 return 하도록 처리
        findKthElement(root, k, finder);
        return finder[1];
    }

    public void findKthElement(TreeNode node, int k, int[] finder) {
        // 만약 node가 null이면 연산 필요 없으므로, pass
        if(node == null) {
            return;
        }

        // 중위 순회를 진행한다.
        // 중위 순회 -> left => node => right 순으로 순회 하는 것
        findKthElement(node.left, k, finder);
        // 현재 node에 도달했고 이게 몇번째 크기인지 체크하기 위해 count에 1을 더한다.
        finder[0] += 1;
        // 0부터 시작해서 k번째 도착한 노드가 정답 노드가 되므로, 이때 그 값을 answer에 삽입한다.
        if(finder[0] == k) {
            finder[1] = node.val;
            return;
        }
        findKthElement(node.right, k, finder);
        return;
    }
}
