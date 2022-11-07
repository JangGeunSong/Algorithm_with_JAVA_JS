public class Algorithm_Q31 {
    /*
     * 해당 문제는 연결 리스트의 node 교환 문제다.
     * 2개의 포인터를 활용하면 쉽게 해결되는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * 풀이를 확인해보자.
     */  

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
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd_mine(ListNode head, int n) {
        // 마지막 에서 n 번째를 찾는 방법은 일단 size를 먼저 재고,
        // 이후 해당 사이즈 - n 만큼 이동해서 prev_node와 ptr 을 구한 후
        // ptr이 삭제 대상 node 이므로, prev_node의 next를 ptr의 next로 변경하면 된다.
        ListNode prev_node = null;
        ListNode ptr = head;

        int size = 0;

        while(ptr != null) {
            size += 1;
            ptr = ptr.next;
        }

        // Edge case 처리
        // 만약 size가 1이라면, 하나를 지우고, null만 return 된다.
        if(size == 1) {
            return null;
        } else if(size - n == 0) {
            // 만약 size - n이 0 이라면, head node가 삭제 대상이므로,
            // head를 head.next로 대체한 후 return 한다.
            head = head.next;
            return head;
        }

        ptr = head;

        for(int idx = 0; idx < size - n; idx++) {
            prev_node = ptr;
            ptr = ptr.next;
        }

        if(ptr == null) {
            prev_node.next = null;
        } else {
            prev_node.next = ptr.next;
        }

        return head;
    }

    public ListNode removeNthFromEnd_other_solution(ListNode head, int n) {
        // 해당 풀이는 size - n을 fast node 포인터를 활용하여 일단 n 만큼 가게 만들어서 그걸 활용한다.
        ListNode fast = head;
        ListNode slow = head;        
        
        // fast 노드를 n 만큼 이동한다.
        for(int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // Edge case 만약 fase가 null 이라면 n == list의 사이즈 이므로,
        // size - n == 0 이라서 head 노드가 삭제되는 것이다. 따라서 head.next를 return 한다.
        if(fast == null) {
            return head.next;
        }
        // 그게 아니라면, fast node가 null이 될때까지 움직이는 것은
        // 현재 fast 노드가 하나의 리스트라면 이 리스트의 크기는
        // size - n 과 같다. 따라서, 이 size - n 만큼 이동한 후의 포인터를 이용해 타켓 이전노드가
        // 타겟 노드의 다음 노드와 연결되면 되므로, 이를 사용한다.
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        
        return head;
    }
}
