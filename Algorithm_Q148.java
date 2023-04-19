public class Algorithm_Q148 {
    /*
     * 이 문제는 이진 탐색을 통한 길이 확인 문제다.
     * 다만 아무 노드나 선택한다는 조건을 만족시키기 까다롭게 설계되어 있어 해당 논리를 잘 확인해 봐야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
     * 풀이를 확인해보자.
     */

    private int answer = 0;

    public int longestZigZag(TreeNode root) {
        if(root == null) {
            return 0;
        }

        // 지그재그를 왼쪽부터 시작할 때 길이와 오른쪽 부터 시작할 때의 길이를 모두 구한다.
        // 이는 지그재그의 시작이 왼쪽부터 움직이냐, 오른쪽 부터 움직이냐를 체크해서 로직을 만들어야 하기 때문이다.
        // 문제에서 첫 번째 노드는 0으로 시작한다고 했으므로, 0으로 depth를 초기화 후 진행
        getZigZagPathLength(root, false, 0);
        getZigZagPathLength(root, true, 0);

        // 얻게된 최대 길이를 받는다.
        return answer;
    }

    public void getZigZagPathLength(TreeNode node, boolean isLeft, int depth) {
        // 종료 조건 : 만약 현재 node가 null 이라면 현재 depth를 리턴
        if(node == null) {
            return;
        }

        // 현재 depth에서 최대값을 구한다.
        this.answer = Math.max(this.answer, depth);

        // isLeft는 지그재그 성립을 위해 다음에 갈 노드가 left 자식 인지 여부를 묻는 것으로
        // true 일 때는 node.left 이동하면서 false 처리를 하고(오른쪽 자식이 지그재그 성립에 필요하다는 뜻)
        // depth를 1 더한다 반대의 경우에는 depth 추가를 반대로 진행한다.
        // 만약 지그재그 성립을 하지 않는 케이스로 이동할 때에는 첫 노드에서 다음으로 이동한 것으로 간주해 depth를 1로
        // 처리한다.
        if(isLeft) {
            getZigZagPathLength(node.left, false, depth + 1);
            getZigZagPathLength(node.right, true, 1);
        } else {
            // false 라면 left로 이동
            getZigZagPathLength(node.left, false, 1);
            getZigZagPathLength(node.right, true, depth + 1);
        }

        return;
    }


    // 자료구조 정의
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
    
}
