import java.util.*;

public class Algorithm_Q165 {
    /*
     * 이 문제는 연결리스트의 순회를 도는 방법을 이해한 상태에서의 풀이를 묻는 문제다.
     * 별로 어렵지는 않은 문제이니 잘 확인해 보도록 하자.
     * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
     * 풀이를 확인해보자.
     */
    
     public int pairSum(ListNode head) {
        // optimal 하게 풀지 말고 우선 먼저 생각나는 대로 구현부터 진행한다.
        // 이 문제를 풀기 위해 별도의 공간을 추가해서 푼다고 생각하면, 우선 head부터 순회해서 전체 사이즈를
        // 얻은 후 그 절반의 크기를 가진 list를 생성한다. 그리고 그 리스트에 head부터 n/2 - 1 까지 진행해서
        // 순서대로 데이터를 쌓아준 후 다시 n/2 - 1 번째 부터 역으로 list를 진행하며 리스트의 끝까지 진행한다.
        // 이렇게 짝의 합을 구하고, 이 결과 중 가장 큰 값을 구하면 된다.
        // 이렇게 하면 O(n + n + n / 2) = O(n) 이 가능하다.
        int answer = Integer.MIN_VALUE;
        ListNode currNode = head;
        int size = 0;
        int currPtrIndex = 0;
        int tailIndex = 0;
        List<Integer> sumList = new ArrayList<>();

        // linked list의 사이즈를 구한다.
        while(currNode != null) {
            size += 1;
            currNode = currNode.next;
        }

        // 꼬리 index(후반기 index)는 전체 사이즈 / 2 - 1로 정의
        currNode = head;
        tailIndex = (size / 2) - 1;

        // 전체 노드를 순회 하면서
        while(currNode != null) {
            if(currPtrIndex <= ((size / 2) - 1)) {
                // 연결 리스트의 전반기는 그냥 값을 순차적으로 add 처리
                sumList.add(currNode.val);
            } else {
                // 후반기는 tailIndex를 활용해 역순으로 데이터를 가져오는데, 최대값만 구하면 되기 때문에
                // 여기서 받은 tailIndex에 값과 현재 노드의 값을 더한 결과 중 최대값을 구한다.
                answer = Math.max(answer, sumList.get(tailIndex) + currNode.val);
                tailIndex -= 1;
            }
            currPtrIndex += 1;
            currNode = currNode.next;
        }

        // 얻어낸 answer를 리턴한다.
        return answer;
    }

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
