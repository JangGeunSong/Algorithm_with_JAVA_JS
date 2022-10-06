public class Algorithm_Q4 {
    /*
     * 해당 문제는 Linked List의 정의와 더불어 이 리스트를 상수개의 메모리만 가지고 어떻게 뒤집을 수 있는지를 고민해야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/reverse-nodes-in-k-group/
     * 해당 문제의 풀이는 내가 직접 고안했으며, 참고한 내용은 없다. 직접 리스트에 변수들을 하나씩 다음 단계로 넘어가는 과정을 고민하고, 아래 로직을 고안해 냈다.
     * 해당 로직을 통해 Linked List의 뒤집기를 할 수 있고, 이 내용을 바탕으로 어떻게 문제를 해결했는지 확인하자!
     */ 

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        /*
         * 해당 문제를 해결하기 위해 구현할 내용은 3가지이다.
         * 1. k 개 범위만큼만 list에서 reverse를 진행한다.
         * 2. 이후 k를 reset 해서 다시 k 범위만큼 reverse를 돌린다.
         * 3. 이후 남은 list의 node가 k범위만큼이 되지 않는다면, reverse를 하지 않는다.
         * 위 1, 2, 3 조건을 고려했을 때, 우선 먼저 진행해야 하는것은 list가 k 범위까지 개수가 있는지 1차 검증
         * 검증이 완료되면 시작지점 부터 k 범위 까지만 reverse 진행하기. 이렇게 얻어진 head를 return 으로 진행한다.
         */

        int numberOfRestElements = 0;
        ListNode ptr = head;
        ListNode tail = null;
        while(ptr != null) {
            numberOfRestElements += 1;
            if(numberOfRestElements == k) {
                tail = ptr.next;
                break;
            } else {
                ptr = ptr.next;
            }
        }

        if(numberOfRestElements >= k && numberOfRestElements > 1) {
            // 만약 현재 남은 node의 개수가 k 보다 크거나 같다면, reverse 대상이므로 현재 if문 로직을 진행한다.
            // 교환을 위해 3개의 추가 변수 필요 head는 그대로 사용
            // 포인터
            ptr = head.next;
            // 교환시 사용할 dummy
            ListNode tmp = null;
            // 최종 결과물의 꼬리
            ListNode rvTail = ptr.next;
            for(int i = 0; i < k - 1; i++) {
                tmp = head;
                head = ptr;
                ptr = ptr.next;
                head.next = tmp;
                if(i == 0) {
                    rvTail = tmp;
                }                
            }
            rvTail.next = reverseKGroup(tail, k);
        } else {
            // 남은 현재 node 개수가 k 보다 작다면, reverse 하지말고, 그냥 return해서 넘긴다.
            // -> 재귀의 종료 시점
            return head;
        }

        return head;
    }

}
