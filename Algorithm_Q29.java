public class Algorithm_Q29 {
    /*
     * 해당 문제는 정렬된 배열을 이진 검색 트리로 변환하는 방법을 구현하는 문제다
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
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

    // 해당 문제는 재귀를 통해 풀이하면 쉽게 풀 수 있다.
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums, 0, nums.length - 1);
    }

    // 이진 트리의 특성을 생각해보면 가운데 값이 root가 되고, 이후 중앙값보다 작으면 왼쪽 노드
    // 중앙값 보다 크면 오른쪽 노드에 들어가게 되는 점을 감안하여 처리하면 된다.
    public TreeNode createBST(int[] nums, int left, int right) {
        if(left > right) {
            return null;
        }

        int mid = (left + right) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        node.left = createBST(nums, left, mid - 1);

        node.right = createBST(nums, mid + 1, right);

        return node;
    }
}
