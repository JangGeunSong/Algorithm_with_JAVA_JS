import java.util.*;

public class Algorithm_Q96 {
    /*
     * 이 문제는 알파벳의 사전 순서를 다르게 했을 때 주어진 문자열들이 사전순 정렬인지 판별하는 문제다.
     * 문자열의 사전 순을 가지기 위해 hash table을 사용하면 쉽게 해결할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/verifying-an-alien-dictionary/
     * 풀이를 확인해보자.
     */

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> dictionary = new HashMap<>();
        String str1 = "";
        String str2 = "";

        // dictionary 안에 각 문자의 순서를 indexing 하여 넣어 준다.
        for(int i = 0; i < order.length(); i++) {
            dictionary.put(order.charAt(i), i);
        }

        for(int i = 0; i < words.length; i++) {
            str1 = words[i];
            for(int j = i + 1; j < words.length; j++) {
                str2 = words[j];

                for(int k = 0; k < str1.length(); k++) {
                    if(k >= str2.length()) {
                        // 만약 continue를 통해 여기까지 왔는데 str1의 길이가 더 길면
                        // null 문자가 사전 순서상 앞으로 와야 하기 때문에 사전 순서가 아닌 것으로 판별되어
                        // false를 리턴한다.
                        return false;
                    }
                    if(dictionary.get(str1.charAt(k)) < dictionary.get(str2.charAt(k))) {
                        // str1의 문자 순서가 str2의 문자 순서보다 작을때는 
                        // str1이 앞에 있는게 확실히 맞으므로 break 하고 그냥 넘어간다.
                        break;
                    } else if(dictionary.get(str1.charAt(k)) == dictionary.get(str2.charAt(k))){
                        // str1의 문자 순서가 str2의 문자 순서와 같을 때에는 
                        // 뒤의 문자를 봐야 하므로 continue 한다.
                        continue;
                    } else {
                        // 만약 위 조건과 다를 때에는 해당 str1이 str2 뒤에 와야 하는 것이기 때문에 return false를
                        // 처리한다.
                        return false;
                    }
                }
            }
        }

        // 위 모든 조건을 돌아 봤는데 false 조건에 걸리지 않는다면 true를 리턴
        return true;
    }
}
