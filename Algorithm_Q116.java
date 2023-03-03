public class Algorithm_Q116 {
    /*
     * 이 문제는 two pointer 와 문자열의 성질을 활용한 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int strStr(String haystack, String needle) {
        // haystack과 needle의 길이를 저장
        int n = haystack.length();
        int m = needle.length();
        String hayHole = "";
        int answer = -1;

        // 길이 비교 진행
        if(n < m) {
            // 만약 needle이 haystack 보다 길면 답이 안나오므로 -1 리턴
            return -1;
        } else if(n == m && haystack.equals(needle)) {
            // 두 문자열의 길이가 같다면 equal 일 때만 0 을리턴
            return 0;
        }

        // 위 두 조건을 만족하지 않으면 아래 로직을 진행
        // 0 부터 끝까지 돌 필요 없이 haystack 길이 - needle 길이 + 1 까지만 돌면 된다.
        // 1이 더해지는 이유는 n - m + 1 위치에서 m 까지의 부분 문자열까지가 가장 끝 지점이기 때문이다.
        // 그 이후부터는 needle 보다 부분 문자열이 짧아지기 때문에 의미가 없다.
        for(int i = 0; i < n - m + 1; i++) {
            // i 부터 i + m 까지의 부분 문자열을 구해서
            hayHole = haystack.substring(i, i + m);

            // needle 과 같다면 i를 리턴
            if(hayHole.equals(needle)) {
                return i;
            }
        }

        // 아무런 return 조건이 없다면 답이 없는 것이므로 -1을 사전에 저장한 answer를 리턴
        return answer;
    }
}
