import java.util.*;

public class Algorithm_Q45 {
    /*
     * 해당 문제는 트리 순회 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/symmetric-tree/
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

    // 2023년 3월 13일 BFS 모델을 사용한 풀이
    public boolean isSymmetric_bfs_model(TreeNode root) {
        // 이번에는 BFS 모델로 풀이했다.
        // 종료조건 root의 left와 right가 모두 null 일때 -> 노드가 1개 이므로, true로 리턴
        if(root.left == null && root.right == null) {
            return true;
        }

        // BFS 모델이므로, queue를 만들어 인접 노드들을 넣을 수 있게 선언
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        // queue가 인접 노드를 접근 할 때, List에 숫자를 삽입하도록 처리
        List<Integer> elemList = new ArrayList<>();
        // depth를 구분하기 위해 선언
        int countChildNodes = 1;
        // 현재 depth에서 자식 노드들이 대칭으로 나타나는지 체크하기 위한 left & right 선언
        int left = 0;
        int right = 0;

        // root를 queue에 삽입
        treeNodeQueue.offer(root);

        // queue가 비어 있지 않다면 반복문을 계속 처리
        while(!treeNodeQueue.isEmpty()) {
            // queue 에서 노드 하나를 poll
            TreeNode node = treeNodeQueue.poll();

            // 각 자식 노드별로 비어 있지 않다면, 노드를 큐에 넣고 해당 노드의 값을 리스트에 삽입
            if(node.left != null) {
                treeNodeQueue.offer(node.left);
                elemList.add(node.left.val);
            } else {
                // 만약 자식 노드가 널이면 범위에 없는 숫자인 -101을 삽입
                elemList.add(-101);
            }

            // left 자식과 마찬 가지로 처리
            if(node.right != null) {
                treeNodeQueue.offer(node.right);
                elemList.add(node.right.val);
            } else {
                elemList.add(-101);
            }
            
            // depth == 현재 queue의 사이즈 이므로, 사이즈 1을 줄여서 depth가 끝나는 것을 표시한다.
            countChildNodes -= 1;
            // countChildNodes 가 0 이면 현재 depth가 종료 된 것이므로, 다음 depth를 위해 큐의 size를 받아 depth
            // 표시자로 처리하고 만들어진 list를 순회하여 대칭인지 체크한다.
            if(countChildNodes == 0) {
                countChildNodes = treeNodeQueue.size();
                left = 0;
                right = elemList.size() - 1;
                // 대칭은 가장 처음 값과 가장 마지막 값이 같으며, 각각 1칸씩 가까워 질때에도 계속 값이 같아야 한다.
                // 만약 이 과정 중 하나라도 값이 다르다면, 바로 false를 리턴하여 함수 종료
                while(left < right) {
                    if(elemList.get(left) != elemList.get(right)) {
                        return false;
                    }
                    left += 1;
                    right -= 1;
                }
                // 이번 depth에서 확인한 list를 clear 해서 값을 모두 삭제한다.
                elemList.clear();
            }
        }

        // 위 반복문을 모두 돌아도 false가 나오지 않는다면 대칭이므로, true를 리턴
        return true;
    }

    // ArrayList를 사용한 풀이 => 재귀 풀이
    public boolean isSymmetric_recursion(TreeNode root) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        leftBinaryTreeTraverse(root.left, left);
        rightBinaryTreeTraverse(root.right, right);

        if(left.size() != right.size()) {
            return false;
        }

        for(int i = 0; i < left.size(); i++) {
            if(left.get(i) != right.get(i)) {
                return false;
            }
        }

        return true;
    }

    public void leftBinaryTreeTraverse(TreeNode node, List<Integer> list) {
        if(node == null) {
            list.add(101);
            return;
        }

        list.add(node.val);
        leftBinaryTreeTraverse(node.left, list);
        leftBinaryTreeTraverse(node.right, list);
        return;
    }
    
    public void rightBinaryTreeTraverse(TreeNode node, List<Integer> list) {
        if(node == null) {
            list.add(101);
            return;
        }

        list.add(node.val);
        rightBinaryTreeTraverse(node.right, list);
        rightBinaryTreeTraverse(node.left, list);
        return;
    }

    // stack을 이용한 풀이 => 반복문 사용
    public boolean isSymmetric_stack(TreeNode root) {
        // This solution is create to one loop condition code
        // This question is more effective using the solve the recursion solution
        
        TreeNode leftRoot = root.left;
        TreeNode rightRoot = root.right;
        
        if(leftRoot == null && rightRoot == null) {
            return true;
        }
        
        if(leftRoot == null || rightRoot == null) {
            return false;
        }
        
        Stack<TreeNode> leftTree = new Stack<TreeNode>();
        Stack<TreeNode> rightTree = new Stack<TreeNode>();
        
        leftTree.push(leftRoot);
        rightTree.push(rightRoot);
        
        // Inorder traverse to left Tree / reverse inorder traverse to right Tree
        while(!leftTree.isEmpty() && !rightTree.isEmpty()) {
            if(leftRoot.left != null && rightRoot.right != null) {
                leftTree.push(leftRoot.left);
                leftRoot = leftRoot.left;
                
                rightTree.push(rightRoot.right);
                rightRoot = rightRoot.right;
            } else {
                if(leftRoot.left != null && rightRoot.right == null) {
                    return false;
                } else if(leftRoot.left == null && rightRoot.right != null) {
                    return false;
                } else {
                    if(leftTree.peek().val == rightTree.peek().val) {
                        leftRoot = leftTree.pop();
                        rightRoot = rightTree.pop();
                    } else {
                        return false;
                    }
                    
                    if(leftRoot.right != null && rightRoot.left != null) {
                        leftTree.push(leftRoot.right);
                        rightTree.push(rightRoot.left);
                        
                        leftRoot = leftRoot.right;
                        rightRoot = rightRoot.left;
                    } else if(leftRoot.right != null && rightRoot.left == null) {
                        return false;
                    } else if(leftRoot.right == null && rightRoot.left != null) {
                        return false;
                    }
                }
            }
        }
        
        return true;
        
    }
}
