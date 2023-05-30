import java.util.*;

public class Algorithm_Q174 {
    /*
     * 이 문제는 HashSet을 설계하는 문제이다. 특히, Hash 라이브러리를 쓰지 않고 구현하라는 부분에 유의해보자.
     * https://leetcode.com/problems/design-hashset/
     * 풀이를 확인해보자.
     */

    // 어떠한 해시 라이브러리도 사용하지 않고 푸는 법
    class MyHashSet_noHash {

        // 어떠한 hash값도 사용하지 않는 방법
        private int[] valueMap;
    
        public MyHashSet_noHash() {
            // 입력 할 수 있는 key의 값이 0 ~ 1000000 까지이므로, 해당 갯수만큼 선언
            // 값이 add 되면 해당 위치의 값을 1로, 아니라면 0 으로 처리한다.
            valueMap = new int[1000001];
        }
        
        public void add(int key) {
            valueMap[key] = 1;
        }
        
        public void remove(int key) {
            // remove 처리
            valueMap[key] = 0;
        }
        
        public boolean contains(int key) {
            // 만약 해당 위치의 값이 1이면 contains 한거고, 0 이면 없는거다.
            return valueMap[key] == 1 ? true : false;
        }
    }

    // Hash 라이브러리를 활용한 풀이
    class MyHashSet_Hash {

        // hash map을 사용한 풀이 방법
        private Map<Integer, Boolean> map;
    
        public MyHashSet_Hash() {
            map = new HashMap<>();
        }
        
        public void add(int key) {
            // add는 그냥 put으로 처리
            map.put(key, true);
        }
        
        public void remove(int key) {
            // remove 처리
            map.remove(key);
        }
        
        public boolean contains(int key) {
            // contains Key 를 해도 되지만, 그냥 get에서 null 이 아닐때만 정의
            if(map.get(key) != null) {
                return true;
            }
    
            return false;
        }
    }
}
