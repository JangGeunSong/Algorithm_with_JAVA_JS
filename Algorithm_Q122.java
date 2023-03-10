public class Algorithm_Q122 {
    /*
     * 이 문제는 연결리스트의 순회 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/linked-list-random-node/
     * 풀이를 확인해보자.
     */


    // 정답은 클래스를 만드는 문제이므로, 아래 클래스를 작성했다.
    class Solution {
 
         // 생성자에서 Linked List의 사이즈와 head를 가져와야 하므로, 이를 선언
         private int size;
         private ListNode head;
 
         // 생성자에서 head와 Linked List의 사이즈를 구한다.
         public Solution(ListNode head) {
             ListNode curr = head;
             this.head = head;
 
             while(curr != null) {
                 size += 1;
                 curr = curr.next;
             }
         }
         
         // 랜덤한 숫자를 얻기위해 random을 통해 몇 번째 노드의 위치를 가져올 건지 선택하고 그 만큼 순회를 돌려 답을 구한다.
         public int getRandom() {
             ListNode curr = this.head;
             int randNodePos = (int) (Math.random() * size) + 1;
 
             for(int now = 1; now < randNodePos; now++) {
                 curr = curr.next;
             }        
 
             return curr.val;
         }
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
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
