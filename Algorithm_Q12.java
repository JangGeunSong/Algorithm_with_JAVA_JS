import java.util.*;

public class Algorithm_Q12 {
    /*
     * 해당 문제는 문제의 조건을 파악하여, 원하는 형태의 답을 구하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * 위 문제는 어떻게 풀이 되는지 아래 코드로 확인하자.
     */ 

    // 해당 풀이는 문제를 확인 후 내가 직접 구현한 코드이다.
    public int lengthOfLongestSubstring_mine(String s) {
        /*
         * 해당 문제를 풀이하기 위해서는 우선 현재 문장에서 첫번째 단어부터 차례대로 Hash Map 에 저장하고, 이 단어를 key로 index를 value로 잡아두어
         * 중복되는 key가 발생했을때, 문제의 조건에 따라 정답이 되는 문자열이 아니게 된다.
         * 이때 중복되는 단어가 처음 존재했던 지점을 posison으로 잡아주고, 이 지점 이전에 존재하던 모든 문자열의 단어들을 전체 삭제 진행 한다.
         * 그리고, 현재 index를 중복으로 문자가 key인 hashmap 의 value로 삽입하여 갱신 처리한다.
         * 이후 현재 hashmap에 존재하는 단어들의 갯수가 문장의 길이와 같으므로, 이 길이와 현재까지 확인되어 최대값을 저장할 answer 변수를 비교하여, 최대값을 answer 변수에 넣는다.
         * 이 과정을 문장이 끝날동한 1회의 loop를 돌며 처리하면, 문제를 해결할 수 있다.
         */
        int answer = 0;
        char[] arr = s.toCharArray();

        Map<Character, Integer> table = new HashMap<>();
        List<Character> list = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            if(table.containsKey(arr[i])) {
                int position = table.get(arr[i]);
                Iterator<Character> itr = table.keySet().iterator();
                while(itr.hasNext()) {
                    char key = itr.next();
                    if(table.get(key) < position) {
                        list.add(key);
                    }
                }
                for(int idx = 0; idx < list.size(); idx++) {
                    table.remove(list.get(idx));
                }
                list.clear();
                table.replace(arr[i], i);
            } else {
                table.put(arr[i], i);
            }
            answer = Math.max(answer, table.size());
        }

        return answer;
    }

    // 아래 코드는 위 코드와 논리는 비슷하지만, 더 간결하게 처리된 버전이다.
    public int lengthOfLongestSubstring_short(String s) {
        int answer = 0;
        
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        
        for(int i = 0, j = 0; i < s.length(); i++) {
            if(charMap.containsKey(s.charAt(i))) {
                j = Math.max(j, charMap.get(s.charAt(i)) + 1);
            }
            
            charMap.put(s.charAt(i), i);
            answer = Math.max(answer, i + 1 - j);
        }
        
        return answer;
    }
}
