public class Algorithm_Q139 {
    /*
     * 이 문제는 DFS를 통해 섬의 갯수를 구할 때 조건에 맞춘 결과를 얻어야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/number-of-closed-islands/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int closedIsland(int[][] grid) {
        int answer = 0;

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                // 모든 요소를 순회할 때 현재 육지면서 사방이 물로서 close 된 섬인지 여부를 체크해 true 면 count를 올린다.
                if(grid[row][col] == 0 && closeDFS(grid, row, col)) {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    public boolean closeDFS(int[][] grid, int row, int col) {
        int rowEdge = grid.length;
        int colEdge = grid[0].length;

        // 종료 조건 1 현재 위치에서 grid의 경계값 밖에 위치하게 되면 false 처리 (사방이 물이어야 하는데 그게 아니니까)
        if(row < 0 || row >= rowEdge || col < 0 || col >= colEdge) {
            return false;
        }
        // 종료 조건 2 현재 grid 안에 값이 1이면 물이기 때문에 우선 close 된 것이므로, true 처리
        if(grid[row][col] == 1) {
            return true;
        }

        // 현재 육지에 있는 것이기 떄문에 방문 처리
        grid[row][col] = 1;

        // 왼쪽 오른쪽 위 아래 모드가 다 close 되어 있는지 여부를 check 한다
        boolean left = closeDFS(grid, row, col - 1);
        boolean right = closeDFS(grid, row, col + 1);
        boolean up = closeDFS(grid, row - 1, col);
        boolean down = closeDFS(grid, row + 1, col);

        // 모두 true 면 물에 의해 close 되어 있으므로, and를 걸어서 true여부 체크
        return left && right && up && down;
    }
}
