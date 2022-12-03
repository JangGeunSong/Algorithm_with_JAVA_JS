import java.util.*;

public class Algorithm_Q51 {
    /*
     * 해당 문제는 hash table과 list의 sorting을 사용한 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/determine-if-two-strings-are-close/
     * 풀이를 확인해보자.
     */

    public boolean closeStrings_mine(String word1, String word2) {
        // 해당 문제를 해결하기 위해 계산 방식을 확인해보면 아래 2가지는 모두 결론적으로 이런 조건이 있어야 성립된다.
        // 1. 각 단어는 같은 갯수의 문자 종류를 가지고 있어야 한다.
        // 예를 들어 word1이 a b c d e를 갖고 있다면 word2에도 개수는 달라도 a b c d e가 모두 있어야 된다.
        // 2. 각 단어의 문자들의 개수는 결론적으로 서로 다르더라도 그 개수들을 나열할 때 같은 숫자들로 구성되야 한다.
        // 예를들어 word1이 a 1개, b 2개 c 4개 라면 word2도 a b c 가 서로 개수는 달라도 1개 2개 4개씩은 가지고 있어야
        // 교환이나 문자들의 교체를 통해 같은 종류의 같은 개수의 문자들로 만들 수 있다.
        // 위 2개의 조건을 만족하면 2단어는 서로 1, 2 연산을 통해 서로를 만들어 줄 수있음을 판별할 수 있다.
        List<Integer> word1_number_list = new ArrayList<>();
        List<Integer> word2_number_list = new ArrayList<>();
        List<Character> word1_char_list = new ArrayList<>();
        List<Character> word2_char_list = new ArrayList<>();
        Map<Character, Integer> word1_map = new HashMap<>();
        Map<Character, Integer> word2_map = new HashMap<>();

        // 해시맵에 단어가 가지고 있는 문자의 종류와 개수를 저장하기 위해 hashmap 사용
        for(int i = 0; i < word1.length(); i++) {
            word1_map.put(word1.charAt(i), word1_map.getOrDefault(word1.charAt(i), 1) + 1);
        }

        for(int i = 0; i < word2.length(); i++) {
            word2_map.put(word2.charAt(i), word2_map.getOrDefault(word2.charAt(i), 1) + 1);
        }

        // list에 각각 단어에 있던 문자의 종류들과 각 문자가 있던 개수를 리스트에 담는다.
        for(Map.Entry<Character, Integer> entry : word1_map.entrySet()) {
            word1_char_list.add(entry.getKey());
            word1_number_list.add(entry.getValue());
        }

        for(Map.Entry<Character, Integer> entry : word2_map.entrySet()) {
            word2_char_list.add(entry.getKey());
            word2_number_list.add(entry.getValue());
        }

        // 리스트들을 전부 오름차순으로 정렬
        Collections.sort(word1_char_list);
        Collections.sort(word1_number_list);
        Collections.sort(word2_char_list);
        Collections.sort(word2_number_list);

        // 만약 개수의 리스트 사이즈가 안맞거나, 종류 리스트의 사이즈가 맞지 않으면 닫혀있지 않으므로 false 처리
        if(word1_number_list.size() != word2_number_list.size() || word1_char_list.size() != word2_char_list.size()) {
            return false;
        }

        // 단어에 포함된 문자의 종류가 서로 일치하는지 체크 만약 일치하지 않으면 false 처리
        for(int i = 0; i < word1_char_list.size(); i++) {
            if(!word1_char_list.get(i).equals(word2_char_list.get(i))) {
                return false;
            }
        }

        // 단어가 보유한 각 문자들의 개수가 리스트로 서로 일치하는지 체크 만약 일치하지 않으면 false 처리
        for(int i = 0; i < word1_number_list.size(); i++) {
            if(!word1_number_list.get(i).equals(word2_number_list.get(i))) {
                return false;
            }
        }

        // 위 판별을 통과하면 true로 리턴
        return true;
    }

    public boolean closeStrings_better_solution(String word1, String word2) {
		// count the English letters
        int N = 26;
        int[] arr1 = new int[N], arr2 = new int[N];
        for (char ch : word1.toCharArray())
            arr1[ch - 'a']++;
        for (char ch : word2.toCharArray())
            arr2[ch - 'a']++;
		
		// if one has a letter which another one doesn't have, dont exist
        for (int i = 0; i < N; i++) {
            if (arr1[i] == arr2[i]) {
                continue;
            }
            if (arr1[i] == 0 || arr2[i] == 0) {
                return false;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
