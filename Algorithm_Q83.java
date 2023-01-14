public class Algorithm_Q83 {
    /*
     * union find를 구현하기만 하면 바로 조건을 찾을 수 있는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // union find를 활용한 풀이를 진행하면 된다.
        // 해당 풀이를 하는 방법을 아래 find를 통해 체크한다.
        int[] dictionary = new int[26];
        String answer = "";

        // dictionary를 순회해서 숫자를 매핑
        for(int i = 0; i < dictionary.length; i++) {
            dictionary[i] = i;
        }

        // union find를 하기 위한 처리 작업을 진행
        for(int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';

            // 각 문자의 head를 찾아준다.
            int setA = find(dictionary, a);
            int setB = find(dictionary, b);

            // 두 문자열의 문자는 같은 그룹이라는 조건이므로, 해당 그룹의 머리를 찾기위해 아래 조건을 돈다.
            if(setA < setB) {
                dictionary[setB] = setA;
            } else {
                dictionary[setA] = setB;
            }
        }

        // 주어진 예시 문자열을 순회하며 위의 union find를 통해 
        // 처리한 그룹들을 활용해 원하는 조건으로 정답을 도출한다.
        for(int i = 0; i < baseStr.length(); i++) {
            int idx = baseStr.charAt(i) - 'a';
            char findSetHead = (char) (find(dictionary, idx) + 'a');

            answer += String.valueOf(findSetHead);
        }

        return answer;
    }

    // find 함수는 매개변수로 받은 원소 a의 부모 노드가 누구인지를 찾아주는 함수
    public int find(int[] dictionary, int k) {
        if(dictionary[k] != k) {
            dictionary[k] = find(dictionary, dictionary[k]);
        }

        return dictionary[k];
    }
}
