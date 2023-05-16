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

    // 재귀를 통한 문제 풀이를 작성했다. 2023-05-16
    // https://leetcode.com/problems/swap-nodes-in-pairs/solutions/3528782/java-recursion-beats-100-12-lines/ 참고
    public ListNode swapPairs_recursion(ListNode head) {
        // 리스트를 끝까지 진행한 후 거꾸로 돌아오면서 짝수번째 데이터에서 서로를 교환하는 작업을 진행.
        return swapPariNodes(head, 0);
    }

    public ListNode swapPariNodes(ListNode node, int cnt) {
        // 받은 node가 null 이면 null 로 리턴
        if (node == null) return null;

        // 현재 파라미터의 cnt에서 1을 추가
        cnt += 1;
        // 이 함수는 현재 노드에서 next를 주면 순회가 끝나고 지금 노드의 next가 어떻게 되는지를 ret로 받아온다.
        ListNode ret = swapPariNodes(node.next, cnt);

        if (cnt % 2 == 1 && ret != null) {
            // 현재 지나가는 노드가 홀수 번째이고, ret 노드가 null이 아니면 
            // 현재 노드의 ret 노드는 서로 위치 교환이 일어나야 한다.
            // 즉, node의 next가 ret의 next로 바뀌고 ret의 next가 지금의 node가 된다.
            node.next = ret.next;
            ret.next = node;
        } else {
            // 지금 노드가 짝수번째 노드라면 바로 뒤의 노드가 서로를 교환 하면서 바뀌었기 때문에
            // 이 작업이 끝난 ret를 지금 node의 next로 바꾸고 ret를 현재 노드로 해서 리턴한다.
            node.next = ret;
            ret = node;
        }

        // 위에서 순회가 끝나고 받아야 하는 현재 노드의 next 값을 리턴해준다.
        return ret;
    }
}
