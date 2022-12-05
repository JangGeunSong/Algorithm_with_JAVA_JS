public class Algorithm_Q52 {
    /*
     * 해당 문제는 연결리스트와 2개의 포인터를 사용한 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/middle-of-the-linked-list/
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

    // 나의 풀이
    public ListNode middleNode(ListNode head) {
        // 한번의 loop만을 사용해서 정답을 구하는 방법
        // 2개의 포인터를 사용해서 하나는 2칸씩 나머지 하나는 한칸씩 가면
        // 1 2 3 4 / 2 4 6 8 로 가면서 결국 한칸씩 가는 포인터가 n/2 의 지점으로 떨어지게 된다.
        // 이를 활용하여 문제를 풀이하면 한번의 루프만으로 문제를 해결할 수 있다.
        ListNode ptr = head;
        ListNode fastPtr = head;

        while(fastPtr != null) {
            if(fastPtr.next == null) {
                fastPtr = fastPtr.next;
                if(fastPtr == null) {
                    break;
                }
            } else {
                fastPtr = fastPtr.next.next;
            }
            ptr = ptr.next;
        }

        return ptr;
    }

    // 더 짧은 구현
    public ListNode middleNode_more_short(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
