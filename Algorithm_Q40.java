public class Algorithm_Q40 {
    /*
     * 해당 문제는 이진 트리의 DFS를 통해 원하는 결과를 찾아야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/
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
     * 나의 풀이
     */
    private int MaxPathValue = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // Edge case 만약 root만 존재한다면, 그냥 root의 값을 리턴한다.
        if(root.left == null && root.right == null) {
            return root.val;
        }
        // 아래 로직을 실행하여 최대 값을 찾아나간다.
        searchPathSum(root);
        // 결과값을 리턴한다.
        return MaxPathValue;
    }

    public int searchPathSum(TreeNode node) {
        int pathValue = 0;

        // 종료조건 1 만약 현재 노드가 null이다. => 문제 범위에 존재하지 않는 값을 return
        if(node == null) {
            return 1001;
        }

        // 종료조건 2 만약 왼쪽 오른쪽 자식 노드가 모두 null 이다.
        // 즉 leaf 노드이다 이럴때는 자기 자신의 값만 return
        if(node.left == null && node.right == null) {
            return node.val;
        }

        // 위 종료조건 1, 2 모두 만족을 하지 않는다면, 왼쪽 자식의 일자 경로 최대값, 오른쪽 자식의 일자 경로 최대값을 받는다.
        int leftMax = searchPathSum(node.left);
        int rightMax = searchPathSum(node.right);

        // 아래 연산은 현재 노드가 root노드라는 가정하에 전체 경로상 최대값을 구하는 로직이다.
        // 여기서 얻은 최대값은 일단 class의 private 값으로 따로 분리해 저장만 한다.
        // 아래 6가지 숫자에 대해 최대값을 찾는다.
        // 1. 자기자신의 값 + 왼쪽 자식 경로의 최대값 + 오른쪽 자식 경로의 최대값
        // 2. 자기자신
        // 3. 왼쪽 자식 경로의 최대값
        // 4. 오른쪽 자식 경로의 최대값
        // 5. 왼쪽 자식 경로 + 자기자신
        // 6. 오른쪽 자식 경로 + 자기자신
        // 여기서 주의할 점은 만약 null일 대에는 범위내에 존재하지 않는 값일때를 고려해서 비교 로직이 들어간다.
        if(leftMax == 1001 && rightMax == 1001) {
            // 만약 leftMax와 rightMax 모두 범위 밖의 값일때는 최대값 연산에서 제외한다.
            MaxPathValue = Math.max(MaxPathValue, node.val);
        } else if(rightMax == 1001) {
            // 만약 rightMax가 범위 밖의 값일때는 최대값 연산에서 제외한다.
            MaxPathValue = Math.max(MaxPathValue, Math.max(node.val + leftMax, Math.max(node.val, leftMax)));
        } else if(leftMax == 1001) {
            // 만약 leftMax가 범위 밖의 값일때는 최대값 연산에서 제외한다.
            MaxPathValue = Math.max(MaxPathValue, Math.max(node.val + rightMax, Math.max(node.val, rightMax)));
        } else {
            // 이 외의 케이스일 때에는 leftMax와 rightMax가 모두 범위 안의 값이므로, 연산에 반영한다.
            MaxPathValue = Math.max(MaxPathValue, Math.max(leftMax, rightMax));
            MaxPathValue = Math.max(MaxPathValue, node.val);
            MaxPathValue = Math.max(MaxPathValue, node.val + leftMax);
            MaxPathValue = Math.max(MaxPathValue, node.val + rightMax);
            MaxPathValue = Math.max(MaxPathValue, node.val + leftMax + rightMax);
        }

        // 일자 경로의 최대값은 leftMax + node.val vs rightMax + node.val 의 값이 나온 후 return 한다.
        // 이떄 범위에서 벗어나는 값이 나온다면 이건 패스하고 처리한다.
        if(leftMax == 1001 && rightMax == 1001) {
            pathValue = node.val;
        } else if(rightMax == 1001) {
            pathValue = Math.max(node.val, node.val + leftMax);
        } else if(leftMax == 1001) {
            pathValue = Math.max(node.val, node.val + rightMax);
        } else {
            pathValue = Math.max(node.val, Math.max(node.val + leftMax, node.val + rightMax));
        }

        // 이렇게 얻은 경로의 최대값을 return 한다.
        return pathValue;
    }


    /*
     * 짧은 풀이
     */

    int max = Integer.MIN_VALUE;
    
    public int maxPathSum_short(TreeNode root) {
        helper(root);
        return max;
    }
    
    // helper returns the max branch 
    // plus current node's value
    int helper(TreeNode root) {
        if (root == null) return 0;
        
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);
        
        max = Math.max(max, root.val + left + right);
        
        return root.val + Math.max(left, right);
    }
}
