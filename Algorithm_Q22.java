public class Algorithm_Q22 {
    /*
     * 해당 문제는 DP를 통해 풀이하는 방법과, 수학적으로 조합을 활용해 계산해 답을 얻는 방법 2가지 풀이가 가능한 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/unique-paths/
     * 이 문제는 내가 푼 버전은 DP를 통한 memozation을 통해 풀이했고, 조합에 대한 계산은 검색으로 식을 찾아 구현한 것을 참고하여 풀었다.
     * 풀이를 확인해보자.
     */

    // DP를 사용한 풀이
    public int uniquePaths_DP_version(int m, int n) {
        // 과거의 기록을 저장하면 중복되는 계산을 회피할 수 있으므로, DP를 사용하여 직전 2개 위치의 값을 읽어 더해가며
        // 현재 위치의 값을 얻는다.
        int answer = 1;
        int[][] grid = new int[m][n];
        // 0, 0 에서는 도달 방법이 1가지다.
        grid[0][0] = 1;

        // 첫 번째 행의 모든 타일은 가는 방법이 1가지다.
        for(int i = 0; i < n; i++) {
            grid[0][i] = 1;
        }

        // 첫 번째 열의 모든 타일은 가는 방법이 1가지다.
        for(int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }

        // 이외의 모든 타일들은 가는 방법이 아래 공식과 같다.
        // 현재 타일 = 같은 열 직전 행의 가는 방법의 수 + 같은 행 직전 열의 가는 방법의 수
        // 이 값을 더해주면서 진행하면, 만약 51, 8 를 간다고 했을때
        // grid[51][8] = grid[50][8] + grid[51][7] 이 되고 2개의 값은 이미 저장되어 있어 그냥 읽어서 더하면 된다.
        for(int y = 1; y < n; y++) {
            for(int x = 1; x < m; x++) {
                grid[x][y] = grid[x - 1][y] + grid[x][y - 1];
            }
        }

        // 원하는 위치의 가는 방법의 수를 얻는다.
        answer = grid[m - 1][n - 1];

        // 결과값을 리턴한다.
        return answer;
    }

    // 수학적으로 조합 계산을 활용한 풀이
    public int uniquePaths_combination_mathmatics_version(int m, int n) {
        /*
         * 경우의 수를 활용한 계산 방법을 이용
         * 만약 m, n의 타일을 가진다면, 우리는 오른쪽 끝으로 가기 위해 총 m + n개의 타일을 가야 한다.
         * 이때 총 가는 방법의 수 = (m + n개 중 m개를 선택하는 경우의 수) 
         *                        + (m + n개 중 n개를 선택하는 경우의 수)
         * 이때 주의할 점은 m, n칸일때 실제 계산은 가로 세로 1칸씩 빼고 계산 해야 한다.
         * 왜냐하면 이미 1, 1에 위치하고 있기 때문이다.
         * 문제에서도 grid[m - 1][n - 1]을 구하라는 이야기가 있으므로, 이는 즉
         * Combination(((m - 1) + (n - 1)), m - 1) == Combination(((m - 1) + (n - 1)), n - 1)
         * 이다.
         * 따라서 이때 결과 값은
         * ((m - 1) + (n - 1))! / (m - 1)! * (n - 1)!
         * 주의할 점은 factorial 결과값이 int 형보다 크기가 크므로 반드시 double로 계산하고,
         * 리턴할 때 int로 캐스팅 한다.
         */
        double answer = 1;
        int N = m + n - 2;
        int r = n - 1;
        
        // N 개에서 r개를 고르는 조합의 수에 분자 값은(N - r + i)로 계산하는 이유는
        // N!/r!(N-r)! = (N * N - 1 * N - 2 * ... * N - r + 1) / r! 로 계산되기 때문에
        // 이 분자를 간단하게 반복문으로 N - r + i 로 계산한 것이다.
        for(int i=1; i<=r; i++){
            answer = answer * (N - r + i)/i; 
        }

        return (int)answer;
    }
}
