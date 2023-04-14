public class Algorithm_Q144 {
    /*
     * 이 문제는 2차원 DP를 활용한 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * 풀이를 확인해보자.
     */
    public int longestPalindromeSubseq(String s) {
        // 문자열의 길이를 n으로 받는다.
        int n = s.length();
        // 서브 시퀀스의 회문 길이 저장을 위해 dp를 선언
        // 첫번째 [] 는 시작 문자의 위치, 두번째 []는 종료 문자의 위치이고 이 2개 문자 사이의 최대 회문길이가
        // dp[i][j] 이다.
        // 즉 0 ~ 4 까지의 회문 길이는 dp[0][4] 로 얻는다.
        int[][] dp = new int[n][n];

        // n x n 행렬에서 대각선은 자기 자신만 존재하는 것이기 때문에 항상 길이 1로 처리
        for(int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            // i + 1 부터 j 가 n 이 되기 전 까지 체크
            for(int j = i + 1; j < n; j++) {
                // 만약 i 번째 문자와 j 번째 문자가 같다면
                if(s.charAt(i) == s.charAt(j)) {
                    // i ~ j 까지 문자의 회문 길이는 직전 dp 값인 i + 1 ~ j - 1 까지의 회문 길이에 2를 더한 값이다.
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // 그게 아니라면
                    // i + 1 ~ j 까지의 회문 길이 저장값 vs i ~ j - 1 까지의 회문 길이 저장값 중 가장 큰 값을 저장한다.
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 이렇게 얻은 값을 통해 첫번째 문자 ~ 마지막 문자 까지의 회문 길이의 최종 값을 dp에서 가져온다.
        return dp[0][n - 1];
    }
}
