public class Algorithm_Q53 {
    /*
     * 해당 문제는 연결리스트의 연결을 활용해서 풀어야 하는 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/odd-even-linked-list/
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

    // 풀이를 참고해서 만든 코드
    public ListNode oddEvenList(ListNode head) {
        // Edge case 처리
        if(head == null) {
            return head;
        }

        if(head.next == null) {
            return head;
        }

        int count = 1;
        ListNode oddHead = null;
        ListNode oddTail = null;
        ListNode evenHead = null;
        ListNode evenTail = null;
        ListNode pointer = head;

        while(pointer != null) {
            // 짝수, 홀수의 경우로 포인터를 쪼개서 나중에는 홀수 리스트의 head를 리턴하는 방법
            if(count % 2 != 0) {
                // 홀수
                if(oddHead == null) {
                    oddHead = pointer;
                    oddTail = pointer;
                } else {
                    oddTail.next = pointer;
                    oddTail = oddTail.next;
                }
            } else {
                // 짝수
                if(evenHead == null) {
                    evenHead = pointer;
                    evenTail = pointer;
                } else {
                    evenTail.next = pointer;
                    evenTail = evenTail.next;
                }
            }
            pointer = pointer.next;
            count += 1;
        }

        // even Tail의 경우 null을 next에 하지 않으면 원형으로 cycle이 돌아버리기 때문에 주의
        evenTail.next = null;
        oddTail.next = evenHead;

        return oddHead;
    }

    // 더 짧은 구현
    // https://leetcode.com/problems/odd-even-linked-list/solutions/127831/odd-even-linked-list/?orderBy=most_votes 해당 링크 참고
    public ListNode oddEvenList_short(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
