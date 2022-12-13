public class Algorithm_Q59 {
    /*
     * 해당 문제는 DP 문제이다.
     * 이 문제를 해결하기 위해서는 memozation을 활용하면 된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-falling-path-sum/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int answer = Integer.MAX_VALUE;
        
        // 첫번째 줄은 자기 자신이 최소 경로이므로, 그냥 자기 자신을 담도록 처리한다
        for(int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
        }

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                // dp 에는 3가지 옵션이 있다.
                // dp[i][j] = matrix[i][j] + dp[i - 1][j - 1]
                // dp[i][j] = matrix[i][j] + dp[i - 1][j]
                // dp[i][j] = matrix[i][j] + dp[i - 1][j + 1]
                // 위 3가지 중 최소값 1개만 골라서 dp에 저장하면 된다.
                // 단 이전 옵션이 만약 j인덱스가 out of bound가 날 수 있으므로, 여기에 유의한다.
                int a = j == 0 ? Integer.MAX_VALUE : matrix[i][j] + dp[i - 1][j - 1];
                int b = matrix[i][j] + dp[i - 1][j];
                int c = j == matrix[i].length - 1 ? Integer.MAX_VALUE : matrix[i][j] + dp[i - 1][j + 1];
                
                dp[i][j] = Math.min(Math.min(a, b), c);
            }
        }

        // 구한 DP 들 중에서 최소값을 찾아 answer에 삽입한다.
        for(int i = 0; i < matrix[0].length; i++) {
            answer = Math.min(answer, dp[matrix.length - 1][i]);
        }

        return answer;
    }
}
