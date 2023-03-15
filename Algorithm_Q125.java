import java.util.*;

public class Algorithm_Q125 {
    /*
     * 이 문제는 트리를 활용하여 결과를 얻어내는 문제이다.
     * 조건에 맞는 상황들에 대해 체크하며 풀어야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
     * 풀이를 확인해보자.
     */

    // 나의 풀이 -> 기본적으로 조건들을 하나씩 체크하며 풀이했기 때문에 복잡하게 구현 되어 있다.
    // 이보다 쉬운 구현도 존재한다.
    public boolean isCompleteTree_mine(TreeNode root) {
        // 종료조건 root 노드 하나만 들어 있을때, 완전 노드 이므로, true를 리턴
        if(root.left == null && root.right == null) {
            return true;
        }

        // BFS를 진행하기 위해 queue 하나를 선언
        Queue<TreeNode> queue = new LinkedList<>();
        // depth를 저장하는 변수, 각 depth 별 node의 갯수를 세기 위한 변수,
        // 현재 depth에서의 노드가 꽉 차 있는지 여부 확인 변수 선언
        int depth = 0;
        int depthCount = 1;
        boolean isFull = true;

        // root는 queue에 무조건 삽입한다.
        queue.offer(root);

        // BFS 순회 진행
        while(!queue.isEmpty()) {
            // node하나를 큐에서 빼고, 현재 depth에서 node 수를 저장한 depthCount에 값을 1 줄인다.
            TreeNode node = queue.poll();
            depthCount -= 1;

            // poll 한 node가 null 일 때
            if(node == null) {
                // 만약 현재 depth count 즉 아직 남아 있는 현재 depth의 node 갯수가 더 남아 있다면
                // complete 한게 아니므로, false를 리턴한다.
                if(depthCount > 0) {
                    return false;
                }

                // 만약 depthCount가 0 인데, queue의 사이즈가 0 보다 크다면, complete 하지 않으므로, false 처리한다.
                if(depthCount == 0 && queue.size() > 0) {
                    return false;
                }
            } else {
                // 만약 현재 depth 에서의 노드수가 full이 아닌데 (4 depth 인데 16이 아님) 현재 노드에서 자식이 있으면
                // complete 하지 않으므로, false 처리
                if(isFull == false && (node.left != null || node.right != null)) {
                    return false;
                }

                // 위 조건이 아니라면 아래 로직을 실행
                // 자식들 중 left가 null 인데 right 가 null 이 아니라면 이는 complete가 아니므로 return false
                if(node.left == null && node.right != null) {
                    return false;
                } else if(node.left == null && node.right == null) {
                    // left가 null, right가 null이라면 이 상황은 leaf 노드에 온 것이다.
                    // 만약 이때 depthCount가 0이 아니고, 해당 queue의 peek에서 자식이 존재하면 이때에는 
                    // complete가 아니므로, return false 처리를 해야 한다. 물론 이떄 peek()는 null이 아니어야 한다.
                    if(depthCount > 0) {
                        if(queue.peek() != null) {
                            if(queue.peek().left != null || queue.peek().right != null) {
                                return false;
                            }
                        }
                    }
                } else {
                    // 해당 상황은 최소한 left가 null 이 아닌 상황으로, 이 때에는 양쪽 자식을 모두 넣는다.
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            // 만약 현재 depth에서의 모든 노드를 다 빼서 확인 했다면
            if(depthCount == 0) {
                // 신규 depth의 node 갯수로 갱신하고 depth를 1 증가시킨다.
                depthCount = queue.size();
                depth += 1;
                // 만약 현재 queue의 노드 수와 지금 depth의 최대 노드 수가 같다면 full 여부 인자를 true로 아니면
                // false 처리한다.
                if(depthCount == Math.pow(2, depth)) {
                    isFull = true;
                } else {
                    isFull = false;
                }
            }
        }

        return true;
    }

    // 같은 BFS 이며, 더 짧은 풀이
    public boolean isCompleteTree_short_solution(TreeNode root) {
        // 현재 tree가 종료되었는지 여부를 체크하는 변수 선언
        boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // BFS 진행
        while(!queue.isEmpty()) {
            // 큐에서 node를 하나 꺼낸다.
            TreeNode currentNode = queue.poll();
            // 만약 현재 노드가 null 이라면, 이 트리는 이제 끝이라고 체크
            if(currentNode == null) {
                end = true;
            } else {
                // 만약 node가 null 이 아니라면 끝인지 여부 확인해서 끝이면, 자식이 더 있는게
                // complete와는 맞지 않으므로, false 처리
                if(end) {
                    return false;
                }
                // 그게 아니라면, queue에 자식을 모두 offer
                queue.offer(currentNode.left);
                queue.offer(currentNode.right);
            }
        }
        return true;
    }

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
