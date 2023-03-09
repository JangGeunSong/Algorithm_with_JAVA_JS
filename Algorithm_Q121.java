import java.util.*;

public class Algorithm_Q121 {
    /*
     * 이 문제는 연결리스트의 순회 문제다.
     * 풀이를 위해 hashmap or hashset이 필요하다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/koko-eating-bananas/
     * 풀이를 확인해보자.
     */
    
    public ListNode detectCycle(ListNode head) {
        // 현재 노드의 정보를 저장할 set을 선언
        HashSet<ListNode> set = new HashSet<>();

        // linked list를 순회할 curr node와 순회를 돌다가 rotation 이 발생하는 위치 지점을 저장할 pos 선언
        ListNode curr = head;
        ListNode pos = null;

        // 리스트를 순회한다. (curr 가 null 이 되면 순회 불가능이므로, 이때만 while을 종료)
        while(curr != null) {
            // 만약 set에 현재 node 정보가 있다면 이 curr node가 circle의 시작점이므로 pos에 저장하며
            // while문 종료
            if(set.contains(curr)) {
                pos = curr;
                break;
            } else {
                // 그게 아니라면 set에 curr node를 저장한다.
                set.add(curr);
            }
            // linked list의 next로 이동
            curr = curr.next;
        }

        // 이렇게 얻은 pos를 리턴한다. (null로 초기화 했으므로, 없다면 null 아니면 pos 지점이 리턴된다.)
        return pos;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
