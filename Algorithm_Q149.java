import java.util.*;

public class Algorithm_Q149 {
    /*
     * 이 문제는 이진 탐색을 통한 너비 확인 문제다.
     * 다만 null인 노드를 포함해서 각 level 별로 어느 위치에 실제 노드가 존재할지 알 수 없으므로, 이에 대한 위치값을 지정할 수 있도록 해야 하는게 핵심이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-width-of-binary-tree/
     * 풀이를 확인해보자.
     */

    public int widthOfBinaryTree(TreeNode root) {
        // root 가 null 이라면 0 으로 리턴
        if(root == null) {
            return 0;
        }

        // 신규 자료구조인 Node가 담길 queue를 생성
        Queue<Node> queue = new LinkedList<>();
        // 루트를 노드로 new 하여 만든 후 삽입
        queue.offer(new Node(0, root));
        int answer = 0;

        while(!queue.isEmpty()) {
            // 큐의 사이즈 == 돌아야 할 리스트의 크기로 체크
            int size = queue.size();
            // 이 depth의 길이는 end - start + 1 인데, 그때의 end와 start 값을 선언
            int start = 0, end = 0;

            for(int i = 0; i < size; i++) {
                // Node를 하나 뺸 후
                Node node = queue.poll();
                // 현재 위치를 구하고
                int position = node.position;

                // 지금이 첫번째 인덱스라면 이게 start 위치 값
                if(i == 0) {
                    start = position;
                }

                // 지금이 마지막 인덱스라면 이게 end 위치 값
                if(i == size - 1) {
                    end = position;
                }

                // 만약 지금 노드의 TreeNode의 left 자식이 null 이 아니면 Node를 만들고 큐에 삽입한다.
                // 이때 위치값은 왼쪽 자식의 경우 길이가 2의 거듭제곱만큼 늘어나기 때문에 2를 곱한 값으로 둔다.
                // 오른쪽은 여기에 + 1 위치에 있으므로, 위치 값을 현재 위치 X 2 한 후 + 1한 값을 넣는다.
                if(node.node.left != null) {
                    queue.offer(new Node(2 * node.position, node.node.left));
                }

                if(node.node.right != null) {
                    queue.offer(new Node(2 * node.position + 1, node.node.right));
                }
            }

            // 모든 리스트가 돌았을 때 위치값이 end와 start에 담겼으므로, 길이 계산 후 최대 값을 얻는다.
            answer = Math.max(answer, end - start + 1);
        }
        
        // 이렇게 얻은 결과를 리턴
        return answer;
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

    // 현재 위치 값을 알기 위한 Node 클래스를 신규로 선언
    public class Node {
        public int position;
        public TreeNode node;
        Node(int position, TreeNode node) {
            this.position = position;
            this.node = node;
        }
    }
}
