public class Algorithm_Q123 {
    /*
     * 이 문제는 연결리스트를 이진 탐색 트리로 바꾸는 문제이다.
     * 과거에 풀었던 배열을 이진 탐색 트리로 바꾸는 문제와 푸는 방법은 완전히 같으며, 중앙 지점을 연결 리스트에서 어떻게 찾을지가 관건인 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public TreeNode sortedListToBST(ListNode head) {
        // Linked List를 이진 탐색 트리로 변경하는 함수를 작성해보자.
        // 종료 조건 1 : head가 null일때
        // 어떠한 node도 만들 수 없으므로, null을 반환
        if(head == null) {
            return null;
        }
        // 종료조건 2 : head 하나만 값이 있을 때
        // 아래 로직을 진행시키면 무한히 반복문이 돌기 때문에 head 자체를 node로 변환해 리턴
        if(head.next == null) {
            return new TreeNode(head.val);
        }

        // 이 외에는 최소 2개 이상의 node가 존재하므로 로직을 실행한다.
        ListNode left = head;
        ListNode right = head;
        ListNode curr = head;

        // left는 탐색 노드의 curr 노드가 진행될 때 이전에 curr 가 있던 위치를 저장한다
        // 그 이유는 연결 리스트를 자르기 위해서 curr는 node로서 루트에 존재할 예정이므로, 이 위치를 없에기 위해서다.
        // 또한 right는 curr 노드가 중간 위치로 가게 만들기 위해 1칸 씩 curr가 이동할 때 2칸씩 이동시켜
        // right가 null이거나 혹은 right.next가 null 이 떨어지면 이때 curr의 위치가 중간 지점임을 알 수 있도록 처리한다.
        while(right != null) {
            if(right.next == null) {
                break;
            }
            left = curr;
            curr = curr.next;
            right = right.next.next;
        }

        // 이렇게 얻은 curr는 서브 트리 혹은 메인 트리의 루트 노드로 사용한다
        // BST의 경우 중앙값이 root 노드이기 때문이다.
        TreeNode node = new TreeNode(curr.val);
        // 그리고 사용한 curr 노드는 더 이상 사용하지 않기 위해 curr 직전 노드인 left에서 left.next를 null로 끊어준다.
        left.next = null;
        // left는 head로 다시 돌아오고
        left = head;
        // node.left는 curr보다 작은 값들이 모인 서브 트리로 head를 사용해 left를 재귀로 호출해 만든다.
        node.left = sortedListToBST(left);
        // node.right는 curr 보다 큰 값들이 모인 서브 트리로 curr의 바로 다음 노드를 head로서 재귀로 호출해 만든다.
        node.right = sortedListToBST(curr.next);
        
        // 이렇게 얻은 node가 이 이진 탐색 트리의 root이므로 리턴해준다.
        return node;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
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
    
    // 문제 풀이에 필요한 자료 구조 생성
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
