public class Algorithm_Q147 {
    /*
     * 이 문제는 간단한 문자열 처리 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/merge-strings-alternately/
     * 풀이를 확인해보자.
     */

    public String mergeAlternately(String word1, String word2) {
        // 2개의 문자열을 처리하기 위해 길이와 index를 각각 선언한다.
        String answer = "";
        int n = word1.length();
        int m = word2.length();

        int i = 0;
        int j = 0;

        // word1, word2 순으로 한 글자씩 뜯어서 문자열로 받으므로, 두 문자열이 아직 자기 길이보다 작은 index를
        // 타고 움직인다면 아래 로직과 같이 진행
        while(i < n && j < m) {
            answer += String.valueOf(word1.charAt(i));
            answer += String.valueOf(word2.charAt(j));
            i += 1;
            j += 1;
        }

        // 이후 만약 두 문자열의 길이가 다를때 더 긴 문자열이 아직 써야 할 문자열이 남았으므로,
        // 부분 문자열을 만들어서 이어서 붙여 준다.
        if(n > m) {
            answer += word1.substring(i, n);
        } else if(n < m) {
            answer += word2.substring(j, m);
        }

        return answer;
    }
}
