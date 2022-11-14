public class Algorithm_Q37 {
    /*
     * 해당 문제는 단어 변환에 대한 횟수를 미리 기록해 두면서 정답을 찾아야 하는 DP 문제이다.
     * 그 과정까지 도달하는게 어렵기 때문에, 이 유형은 기억해 두는게 좋다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/edit-distance/
     * 풀이를 확인해보자.
     */  

    public int minDistance(String word1, String word2) {
        // Edit distance 를 구하기 위해서는 표를 그려놓고 생각해 봐야 한다.
        // 이 연산에서 중요한 것은 h 와 "" 가 존재할때 h혹은 ""를 만들기 위한 연산 횟수를
        // 미리 기록하고 다음에 단어가 하나 더 늘어났을때, 그 기록을 계속해서 해 나가면
        // 끝에는 결과가 얻어진다는 점을 사용하여 문제를 풀면 된다.
        int answer = 0;
        int m = word1.length();
        int n = word2.length();
        int[][] costs = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            costs[i][0] = i;
        }

        for(int i = 0; i <= n; i++) {
            costs[0][i] = i;
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    costs[i + 1][j + 1] = costs[i][j]; 
                } else {
                    int a = costs[i][j];
                    int b = costs[i][j + 1];
                    int c = costs[i + 1][j];

                    costs[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    costs[i + 1][j + 1] += 1;
                }
            }
        }

        answer = costs[m][n];

        return answer;
    }
}