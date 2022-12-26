public class Algorithm_Q68 {
    /*
     * 해당 문제는 구현만 하면 되는 문제이다. 적절한 자료구조를 찾기위해 신경써보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/implement-trie-prefix-tree/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    class Trie {

      // 문제의 요구사항대로 구현하는데 필요한 부분은 HashMap이 적절할 것으로 생각된다.
      // 왜냐하면 search에 O(1), 조건을 찾는데 kyket만 받으면 O(n) 만 사용하면 되기 떄문이다.
      private Map<String, Integer> trieMap;

      public Trie() {
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

  /**
   * Your Trie object will be instantiated and called as such:
   * Trie obj = new Trie();
   * obj.insert(word);
   * boolean param_2 = obj.search(word);
   * boolean param_3 = obj.startsWith(prefix);
   */
}
