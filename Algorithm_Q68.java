import java.util.*;

public class Algorithm_Q68 {
    /*
     * 해당 문제는 구현만 하면 되는 문제이다. 적절한 자료구조를 찾기위해 신경써보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/implement-trie-prefix-tree/
     * 풀이를 확인해보자.
     */

    // 풀이 코드 -> 과거 2022년 12월 26일 나의 풀이 
    class Trie_mine {

      // 문제의 요구사항대로 구현하는데 필요한 부분은 HashMap이 적절할 것으로 생각된다.
      // 왜냐하면 search에 O(1), 조건을 찾는데 kyket만 받으면 O(n) 만 사용하면 되기 떄문이다.
      private Map<String, Integer> trieMap;

      public Trie_mine() {
          trieMap = new HashMap<>();
      }

      public void insert(String word) {
          trieMap.put(word, 1);
      }

      public boolean search(String word) {
          return trieMap.containsKey(word);
      }

      public boolean startsWith(String prefix) {
          boolean result = false;

          for(String key : trieMap.keySet()) {
              if(key.startsWith(prefix)) {
                  result = true;
              }
          }

          return result;
      }
  }

  // 2023-03-17 새롭게 문제로 다시 만나 공부한 더 빠른 구현 버전 -> true / false 기반의 design 문제로
  // https://leetcode.com/problems/implement-trie-prefix-tree/solutions/3305923/image-explanation-complete-stepwise-diagrams-c-java-python/
  // 위 경로를 참고하여 풀이 작성
  class Trie_fast_version {
    
    // 아래 정의한 TrieNode를 필드로 선언
    protected TrieNode root;

    // 생성자
    public Trie_fast_version() {
        this.root = new TrieNode();
    }
    
    // insert
    // 해당 단어의 문자들을 순서대로 지나가면서 root = '' 에서 출발
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            // index는 아스키 문자에 의해 element 문자 - 'a'로 처리
            int index = c - 'a';
            // 해당 인덱스에서 null 이면
            if(node.children[index] == null) {
                // 해당 인덱스에서 자식 노드로서 하나 생성
                node.children[index] = new TrieNode();
            }
            // 이 node를 현재 node로 갱신
            node = node.children[index];
        }
        // 단어가 끝나고 난 후 가장 마지막에 위치한 node는 문장의 마지막 문자이므로, isCompletedWord 를 true로 처리
        node.isCompletedWord = true;
    }
    
    // search
    public boolean search(String word) {
        // 위 insert를 통해서 탐색 진행
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            // 만약 노드들을 타고 내려가다가 index를 받아 자식을 얻었는데, 이 값이 null 이면 해당 단어는
            // insert된 적이 없다. 왜냐하면 insert 할 때 모든 문자가 각 문자 node를 만들면서 해당
            // 자식 배열 안의 index에 새로 node를 만들기 떄문이다.
            if(node.children[index] == null) {
                // 따라서 search는 false
                return false;
            }
            node = node.children[index];
        }

        // 그게 아니라면 현재 여기로 올 것이므로, 이 단어는 insert된 것이니 true를 리턴
        return node.isCompletedWord;
    }
    
    // prefix가 들어올 때 이게 해당 트리를 탐색해서 포함 되는지를 확인해야 한다.
    // 즉 search와 완전히 같으며, 이때는 prefix만큼 문자를 돌 때 children이 해당 문자의 index에서
    // 한번도 null이 나오지 않는다면, 이는 포함된 것이므로 true를 리턴, 아니라면 false를 리턴한다. 
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    // trie를 조건에 맞게 커스텀한 자료구조
    public class TrieNode {
        // isCompletedWord 는 해당 node 가 TrieNode[] 인 children 으로서 생성될 때 가장 마지막
        // 즉 leaf 가 되면 true가 된다
        public boolean isCompletedWord;
        // 아래 children은 현재 node에서 자식노드인 TrieNode들의 위치가 알파벳이므로 26자 이다.
        // 문자들이 현재 node에서 자식으로 존재하면 그 index에 new TrieNode를 해서 만들 것이다.
        public TrieNode[] children;

        // TrieNode 자료구조 초기화 -> 현재 자신이 자식 노드일지 알 수 없으므로, isCompletedWord는 flase
        // children은 당연히 다 null 로 26개의 배열 초기화
        public TrieNode() {
            this.isCompletedWord = false;
            this.children = new TrieNode[26];
        } 
    }
}

  /**
   * Your Trie object will be instantiated and called as such:
   * Trie obj = new Trie();
   * obj.insert(word);
   * boolean param_2 = obj.search(word);
   * boolean param_3 = obj.startsWith(prefix);
   */
}
