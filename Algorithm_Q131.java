public class Algorithm_Q131 {
    /*
     * 이 문제는 DP를 통한 메모를 진행하여 답을 찾아야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-path-sum/
     * 풀이를 확인해보자.
     */

    // memozation의 기본적인 개념을 생각하며 아래 풀이를 확인해보자.
    public int minPathSum(int[][] grid) {
        // 정답 변수 선언
        int answer = 0;
        // 현재 보드 판의 최소 경로값을 각 좌표별로 지정하기 위한 변수 선언
        int[][] map = new int[grid.length][grid[0].length];
        // memozation을 위한 초기 값 선언
        map[0][0] = grid[0][0];

        // 최 상단과 가장 왼쪽 줄의 값은 항상 바로 위 혹은 바로 왼쪽 값의 합이므로, 이를 고려해서 계산한다.
        for(int i = 1; i < grid.length; i++) {
            map[i][0] = map[i - 1][0] + grid[i][0];
        }

        for(int i = 1; i < grid[0].length; i++) {
            map[0][i] = map[0][i - 1] + grid[0][i];
        }

        for(int i = 1; i < grid.length; i++) {
            for(int j = 1; j < grid[0].length; j++) {
                // 현재 map의 최소 도달 경로 값은 이전 위와 왼쪽 도달 값 중 가장 작은 값 + 현재 위치 값이다.
                map[i][j] = Math.min(map[i - 1][j], map[i][j - 1]) + grid[i][j];
            }
        }

        // 정답은 가장 우측 하단의 값이다.
        answer = map[grid.length - 1][grid[0].length - 1];

        return answer;
    }
}
