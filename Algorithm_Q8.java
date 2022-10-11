import java.util.*;

public class Algorithm_Q8 {
    /*
     * 해당 문제는 우선순위 큐 혹은 병합 정렬을 이용한 풀이를 해야하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * 해당 문제의 풀이는 2가지가 존재하는데 내가 푼 버전은 우선순위 큐를 사용한 방법이고, 해당 풀이가 통과하고 나서 병합 정렬에 대한 풀이를 추가로 확인해 삽입하였다.
     * 각각에 대한 내용은 둘다 좋은 풀이법인데, 어떤것이 더 좋은지는 상황에 따라 판단하자.
     */ 

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 우선순위 큐를 사용한 풀이
    public ListNode mergeKLists_priority_queue(ListNode[] lists) {
        ListNode answer = null;

        // edge case 애 대한 처리
        if(lists.length == 0) return answer;
        
        if(lists.length == 1) return lists[0];

        // 모든 list의 값을 우선순위 큐 안에 삽입하면, 기본적으로 오름차순 정렬이 되므로, 이를 활용한다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            while(node != null) {
                pq.add(node.val);
                node = node.next;
            }
        }

        ListNode ptr = null;

        // 자동으로 오름차순으로 정렬된 우선순위 큐 내부에서 값을 하나씩 제외한 후 이를 통해 정답 list를 만들어준다.
        while(!pq.isEmpty()) {
            // 2중으로 poll 하지 않도록 주의 => 만약 2중으로 poll 하게 되면 null 값을 불러와서 null pointer exception이 발생함
            if(answer == null) {
                answer = new ListNode(pq.poll().intValue());
                ptr = answer;
                if(pq.peek() == null) {
                    ptr.next = null;
                } else {
                    ptr.next = new ListNode();
                    ptr = answer.next;
                }
                ptr = answer.next;
            } else {
                ptr.val = pq.poll().intValue();
                if(pq.peek() == null) {
                    ptr.next = null;
                    break;
                } else {
                    ptr.next = new ListNode();
                    ptr = ptr.next;
                }
            }
        }

        // 결과값을 return 한다.
        return answer;
    }

    // 병합 정렬을 이용한 풀이 (재귀 사용)
    public ListNode mergeKLists_merge_sort(ListNode[] lists) {
        ListNode answer = null;

        // edge case 애 대한 처리
        if(lists.length == 0) return answer;
        
        if(lists.length == 1) return lists[0];

        // Divide and Conquer 전략을 취해주는 로직을 구현
        // 아래와 같은 로직일 때는 일단 2개씩 옆에 있는것 끼리 정렬
        // 이후에는 옆의 2개씩 이후에는 옆에 4개씩 점점 interval을 늘린다.
        // 이는 병합되는 애들이 처음 2개 이후에는 처음 4개가 각 0, 4, 8 등에 합쳐지는 것을 고려해서 구현한다.
        int interval = 1;
        
        while(interval < lists.length) {
            for(int i = 0; i + interval < lists.length; i = i + (interval * 2)) {
                lists[i] = mergeSortTwoLists(lists[i], lists[i + interval]);
            }
            interval = interval * 2;
        }

        answer = lists[0];

        // 결과값을 return 한다.
        return answer;
    }

    public ListNode mergeSortTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode ptr = result;

        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                ptr.next = new ListNode(list1.val);
                ptr = ptr.next;
                list1 = list1.next;
            } else {
                ptr.next = new ListNode(list2.val);
                ptr = ptr.next;
                list2 = list2.next;
            }
        }

        if(list1 != null) {
            ptr.next = list1;
        }

        if(list2 != null) {
            ptr.next = list2;
        }

        return result.next;
    }
}
