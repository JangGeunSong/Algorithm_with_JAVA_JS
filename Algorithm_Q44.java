public class Algorithm_Q44 {
    /*
     * 해당 문제는 LinkedList를 사용해서 풀이하는 문제이다.
     * 어렵지 않은 문제인데, 3개의 포인터가 필요하다.
     * 전전 노드, 전 노드, 현재 노드 이렇게 준비하여 풀이했다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/swap-nodes-in-pairs/
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
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 3개의 노드를 활용해 푸는 방법 => 현재 노드가 ptr 로 진행될 때, 처음 swap할 때에는 이전 노드만 표시
    // 이후에는 전전 노드까지 표시해서 전전 노드의 next가 전 노드가 될 수 있도록 조정해 주면 1회의 loop만으로 풀이할 수 있다.
    public ListNode swapPairs(ListNode head) {
        ListNode prevPrevPtr = null;
        ListNode prevPtr = null;
        ListNode ptr = null;
        ListNode tmp = null;
        boolean isFirstSwap = true;
        int count = 2;

        // Edge case 처리
        if(head == null) {
            return head;
        }

        if(head.next == null) {
            return head;
        }

        ptr = head.next;
        prevPtr = head;

        while(ptr != null) {
            // 짝이 이루어 졌을때 swap 한다고 했으므로, 그 count를 세서 2가 되면 swap 진행
            if(count == 2) {
                prevPtr.next = ptr.next;
                ptr.next = prevPtr;
                if(isFirstSwap) {
                    // 만약 head 의 교환이라면 head를 갱신
                    tmp = ptr;
                    ptr = prevPtr;
                    prevPtr = tmp;
                    head = prevPtr;
                    isFirstSwap = !isFirstSwap;
                } else {
                    // head의 교환이 아니라면 아래 전전 노드를 갱신 처리
                    tmp = ptr;
                    ptr = prevPtr;
                    prevPtr = tmp;
                    prevPrevPtr.next = prevPtr;
                }
                count = 0;
            } else {
                // 짝이 될 때 까지 그냥 진행 할 때에는 전전 노드, 전 노드, 현재 노드가 1칸씩 앞으로 이동한다.
                prevPrevPtr = prevPtr;
                prevPtr = prevPtr.next;
                ptr = ptr.next;
                count += 1;
            }
        }

        // 결과를 리턴
        return head;
    }

    // 똑같이 3개의 포인터를 사용 하지만, 과거 현재 미래 node로 이름을 변경했다.
    public ListNode swapPairs_other_solution(ListNode head) {
        // node1.next = node2.next
        // node2.next = node1
        if(head == null) {
            return head;
        }
        
        if(head.next == null) {
            return head;
        }
        
        ListNode curNode = head;
        ListNode fastNode = head.next;
        ListNode prevNode = null;
        int step = 0;
        
         while(fastNode != null) {
            if(step % 2 == 0) {
                if(prevNode == null) {
                    curNode.next = fastNode.next;
                    fastNode.next = curNode;
                } else {
                    prevNode.next = fastNode;
                    curNode.next = fastNode.next;
                    fastNode.next = curNode;
                }
                
                ListNode tmp = null;
                tmp = fastNode;
                fastNode = curNode;
                curNode = tmp;
                
                // 자취가 현재 0이면 head가 갱신 되어야 하는 연산이기 때문에 아래 내용을 처리한다.
                if(step == 0) {
                    head = curNode;
                    prevNode = fastNode;
                } else {
                    prevNode = fastNode;
                }
            }
            curNode = curNode.next;
            fastNode = fastNode.next;
            step += 1;
        }
        
        return head;
    }
}
