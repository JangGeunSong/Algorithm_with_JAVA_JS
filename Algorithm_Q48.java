import java.util.*;

public class Algorithm_Q48 {
    /*
     * 해당 문제는 linked list를 사용한 문제 풀이이다. 
     * arrayList 를 사용한 나의 풀이와, linked list만을 사용한 풀이를 모두 확인해 보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
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

    // 나의 풀이 -> array list 사용
    public boolean isPalindrome_use_array_list(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode ptr = head;
        boolean answer = true;
        while(ptr != null) {
            list.add(ptr.val);
            ptr = ptr.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while(left < right) {
            if(list.get(left) != list.get(right)) {
                answer = false;
                break;
            }
            left += 1;
            right -= 1;
        }

        return answer;
    }

    // linked list를 사용한 풀이
    public boolean isPalindrome(ListNode head) {
        
        ListNode left = head;
        ListNode right = null;
        ListNode pointer = head;
        ListNode temp = null;
        
        while(pointer != null) {
            if(pointer == head) {
                right = new ListNode(pointer.val, null);
                temp = right;
                pointer = pointer.next;
            } else {
                right = new ListNode(pointer.val, temp);
                temp = right;
                pointer = pointer.next;
            }
        }
        
        while(left != null) {
            if(left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        
        return true;
    }
}
