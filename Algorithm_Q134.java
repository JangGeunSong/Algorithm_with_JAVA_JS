import java.util.*;

public class Algorithm_Q134 {
    /*
     * 이 문제는 DP를 통한 메모를 진행하여 답을 찾아야 하는 문제이다.
     * DP 라고 하지만 상당히 어려운 구현을 선보여야 하기 때문에 답을 보면서 구현을 진행했다.
     * https://leetcode.com/problems/scramble-string/solutions/3357439/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
     * 위 링크를 참고 하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/scramble-string/
     * 풀이를 확인해보자.
     */

    // String key에 대해서 boolean 값의 DP 메모를 위한 변수를 선언
    Map<String, Boolean> dp = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // 문자열 1의 길이를 갖고 온다.
        int n = s1.length();

        // 종료 조건 1 만약 문자열 2의 길이가 1번 길이와 같지 않을때
        if(s2.length() != n) {
            return false;
        }
        
        // 종료 조건 2 만약 문자열 1과 2가 같을때
        if(s2.equals(s1)) {
            return true;
        }

        // 종료 조건 3 문자열의 길이가 1일때
        if(n == 1) {
            return false;
        }

        // key 값 = 문자열 1 + 공백 + 문자열 2
        String key = s1 + " " + s2;

        // 만약 DP에 해당 키로 이미 값이 존재하면 이를 받아서 리턴
        if(dp.containsKey(key)) {
            return dp.get(key);
        }

        // 반복문을 돌면서 다음을 진행
        for(int i = 1; i < n; i++) {
            // swap 없이 하위 문자열들을 섞을때
            boolean withoutSwap = (
                isScramble(s1.substring(0, i), s2.substring(0, i))
                &&
                isScramble(s1.substring(i), s2.substring(i))
            );

            // 위 조건에서 true 가 받아진다면, 이는 scrambling 했을때 
            // s1 - scrambling -> s2 가 만들어 진다는 것이므로 true를 put 하고
            // true를 리턴한다.
            if(withoutSwap) {
                dp.put(key, true);
                return true;
            }

            // swap 해서 하위 문자열들을 섞을때
            // swap 한다는 것은 하위 문자열들이 
            // ---(a) ----------(b)
            // ==========(c) ===(d)
            // 위에서 a, d 와 b, c를 섞는 것과 같다.
            boolean withswap = (
                isScramble(s1.substring(0, i), s2.substring(n - i))
                &&
                isScramble(s1.substring(i), s2.substring(0, n - i))
            );

            // 위에서 해당 값이 true면 랜덤하게 섞고 swap 한 경우에 원하는 
            // s1 - scrambling -> s2 가 된다는 것이므로, dp에 true를 넣고 리턴한다.
            if(withswap) {
                dp.put(key, true);
                return true;
            }
        }
        // 위 상황이 모두 아니라면 scrambling 할때 s1 -> s2 가 되지 못한다는 것이므로, false를 넣고 리턴
        dp.put(key, false);
        return false;
    }
}
