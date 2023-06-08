public class Algorithm_Q180 {
    /*
     * 이 문제는 이진 탐색으로 풀어도 되고, 그냥 완전 탐색해서 풀어도 되는 문제다.
     * 다음에 해당 문제를 이진 탐색으로도 풀어보자.
     * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
     * 풀이를 확인해보자.
     */

    public int countNegatives(int[][] grid) {
        // 배열의 길이를 잡아주고
        int n = grid.length;
        int m = grid[0].length;

        int answer = 0;

        // 2중 루프를 돌면서
        for(int i = 0; i < n; i++) {
            for(int j = m - 1; j >= 0; j--) {
                // 만약 현재 시점에서 0보다 같거나 큰 값이 나온다면, 끝점 to 지금 지점 까지 갯수가 음수니까
                // 답에 추가
                if(grid[i][j] >= 0) {
                    answer += m - j - 1;
                    break;
                }

                // 만약 0 까지 왔다면, 이게 음수 일 때 지금 row에서는 m개의 모든 원소가 전부 음수로 계산한다.
                if(j == 0 && grid[i][j] < 0) {
                    answer += m;
                }
            }
        }

        return answer;
    }
}
