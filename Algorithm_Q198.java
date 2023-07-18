import java.util.HashMap;
import java.util.Map;

public class Algorithm_Q198 {
    /*
     * 이중 연결 리스트에 대한 문제이다.
     * 연결리스트는 쉽게 해결하지만 이중 연결리스트에 대한 부분은 잘 나오지 않기 때문에 생각이 잘 떠오르지 않을 수 있는 문제이다.
     * 내용을 잘 이해하도록 하자.
     * https://leetcode.com/problems/lru-cache/
     * 풀이를 확인해보자.
     */

    class LRUCache {

    private int capacity;
    private Map<Integer, Node> map = new HashMap<>();
    // 최근에 쓴 것을 head로 가장 오랫동안 안쓴거를 tail로 놓고 처리
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        // 데이터를 받아온 key는 최근에 쓴 것이기 때문에 head로 갱신하고 없으면 -1 리턴
        if(!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        
        map.remove(key);
        deleteNode(node);
        addNode(node);
        map.put(key, head.next);

        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            // 이미 key가 존재하면 지금 노드는 지워 버리고 새 노드를 등록하여 head에 담는다.
            Node node = map.get(key);

            map.remove(key);
            deleteNode(node);
        }

        if(capacity == map.size()) {
            // 용량이 꽉 차 있다면, tail에 있는 노드 정리
            // 이 경우는 map에 key가 없고, map은 용량이 꽉 차 있을 때 발생한다.
            // 새로운 key로 노드를 등록하기 위해서이다.
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        // 노드를 추가
        addNode(new Node(key, value));
        map.put(key, head.next);
    }

    // 이중 연결 리스트의 노드 삭제 처리 함수
    public void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // 이중 연결 리스트의 노드 추가 처리 함수
    public void addNode(Node node) {
        Node tmp = head.next;

        node.next = tmp;
        tmp.prev = node;

        node.prev = head;
        head.next = node;
    }

    // 이중 연결 리스트 자료구조
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
