public class Algorithm_Q201 {
    /*
     * DP 문제로 어떻게 문제를 쪼개서 풀이를 해 나가는지 이해하는게 필요하다.
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
     * 풀이를 확인해보자.
     */

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] costs = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            costs[i][0] = costs[i - 1][0] + s1.charAt(i - 1);
        }
        
        for(int j = 1; j <= n; j++) {
            costs[0][j] = costs[0][j - 1] + s2.charAt(j - 1);
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    costs[i][j] = costs[i - 1][j - 1];
                } else {
                    costs[i][j] = Math.min(
                        s1.charAt(i - 1) + costs[i - 1][j],
                        s2.charAt(j - 1) + costs[i][j - 1]
                    );
                }
            }
        }
        
        return costs[m][n];
    }
}
