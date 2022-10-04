public class Algorithm_Q2 {
    /*
     * 해당 문제는 아래 정의된 자료구조를 사용해서 푸는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/add-two-numbers/description/
     * 해당 문제는 새벽에 자기 전 막 코딩으로 푼 버전과 (맨 첫번째 함수)
     * 이전에 잘 코드를 정리해서 하나의 while문을 통해 풀이한 버전(두번째 함수)이 함께 존재한다.
     */ 
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers_rough_version (ListNode l1, ListNode l2) {
        ListNode answer = null;
        int rest_value = 0;

        rest_value = l1.val + l2.val;
        
        l1 = l1.next;
        l2 = l2.next;

        if(l1 == null && l2 == null) {
            if((rest_value / 10) == 0) {
                answer = new ListNode(rest_value % 10, null);
            } else {
                answer = new ListNode(rest_value % 10, new ListNode(rest_value / 10, null));
            }
        } else {
            answer = new ListNode(rest_value % 10, new ListNode());
        }

        ListNode ptr = answer.next;
        rest_value = rest_value / 10;

        // If 2 list is not null
        while(l1 != null && l2 != null) {
            rest_value = rest_value + l1.val + l2.val;
            ptr.val = rest_value % 10;
            rest_value = rest_value / 10;
            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null && l2 == null) {
                if(rest_value == 0) {
                    ptr.next = null;
                } else {
                    ptr.next = new ListNode(rest_value, null);
                }
            } else {
                ptr.next = new ListNode();
                ptr = ptr.next;
            }
        }
        
        // If first list has more values
        while(l1 != null) {
            rest_value = rest_value + l1.val;
            ptr.val = rest_value % 10;
            rest_value = rest_value / 10;
            l1 = l1.next;

            if(l1 == null) {
                if(rest_value == 0) {
                    ptr.next = null;
                } else {
                    ptr.next = new ListNode(rest_value, null);
                }
            } else {
                ptr.next = new ListNode();
                ptr = ptr.next;
            }
        }

        // If second list has more values
        while(l2 != null) {
            rest_value = rest_value + l2.val;
            ptr.val = rest_value % 10;
            rest_value = rest_value / 10;
            l2 = l2.next;
            if(l2 == null) {
                if(rest_value == 0) {
                    ptr.next = null;
                } else {
                    ptr.next = new ListNode(rest_value, null);
                }
            } else {
                ptr.next = new ListNode();
                ptr = ptr.next;
            }
        }

        return answer;
    }

    public ListNode addTwoNumbers_aligned_version(ListNode l1, ListNode l2) {
        
        int upperNumber = 0;
        
        ListNode answer = null;
        ListNode pointer = null;
        
        upperNumber = l1.val + l2.val;
        
        l1 = l1.next;
        l2 = l2.next;
        
        answer = new ListNode(upperNumber % 10);
        upperNumber /= 10;
        
        pointer = answer;
        
        // Just one loop for solving the problem!
        while(l1 != null || l2 != null) {
            if(l1 != null) {
                upperNumber += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                upperNumber += l2.val;
                l2 = l2.next;
            }
            
            pointer.next = new ListNode(upperNumber % 10);
            pointer = pointer.next;
            
            upperNumber /= 10;
        }
        
        if(upperNumber != 0) {
            pointer.next = new ListNode(upperNumber);
        }
        
        return answer;
    }
}
