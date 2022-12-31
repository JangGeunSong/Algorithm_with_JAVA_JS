public class Algorithm_Q72 {
    /*
     * 백트래킹 문제이다. 해당 문제는 판의 크기가 작은 편으로 처리하기 쉽다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/single-threaded-cpu/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int uniquePathsIII(int[][] grid) {
        // 위치 정보를 나타낸다 x,y 2개 좌표가 필요하므로, 0 => x축, 1 => y축 으로 정리한다.
        int[] startPos = new int[2];
        int[] endPos = new int[2];
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] answer = new int[1];

        // visited board 초기화 진행
        // 시작과 끝 위치를 반환 받는다.
        // 추가로 방문 불가능한 위치도 visited를 true 처리한다.
        // 이외의 경우는 0 뿐이므로, 여기에 대해서는 visited를 false 처리한다.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    startPos[0] = i;
                    startPos[1] = j;
                    visited[i][j] = true;
                } else if(grid[i][j] == 2) {
                    endPos[0] = i;
                    endPos[1] = j;
                    visited[i][j] = true;
                } else if(grid[i][j] == -1) {
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        // 유니크 경로를 찾을 함수 실행
        possibleUniquePath(grid, endPos, startPos[0] + 1, startPos[1], visited, answer);
        possibleUniquePath(grid, endPos, startPos[0] - 1, startPos[1], visited, answer);
        possibleUniquePath(grid, endPos, startPos[0], startPos[1] + 1, visited, answer);
        possibleUniquePath(grid, endPos, startPos[0], startPos[1] - 1 , visited, answer);

        return answer[0];
    }

    public void possibleUniquePath(int[][] grid, int[] endPos, int x, int y, boolean[][] visited, int[] answer) {
        // 종료 조건 처리
        
        // 종료 조건 1
        if(x < 0 || x >= grid.length) {
            // 행의 out of bound 이므로 return
            return;
        }
        // 종료 조건 2
        if(y < 0 || y >= grid[0].length) {
            // 열의 out of bound 이므로 return
            return;
        }
        // 종료 조건 3
        // 만약 유니크 패스로 도달 했다면
        if(allVisited(visited) && x == endPos[0] && y == endPos[1]) {
            answer[0] += 1;
            return;
        }
        if(visited[x][y] == true) {
            // 이미 방문 했거나 혹은 방문 불가능한 곳이라면
            // 아래 로직을 진행할 필요가 없으므로 종료.
            return;
        }

        // 이 외의 모든 순간에 대해 이동을 진행한다.
        // 4가지 케이스가 있다 x축 좌 우 이동, y축 좌 우 이동
        // 위 4가지 케이스로 이동하기 전 현재 노드는 방문 한 것이기 떄문에 방문 처리를 진행한다.
        // 만약 이미 방문한 곳이라면 여기는 더이상 방문하면 안되기 때문에 넘어간다.
        visited[x][y] = true;
        possibleUniquePath(grid, endPos, x + 1, y, visited, answer);
        possibleUniquePath(grid, endPos, x - 1, y, visited, answer);
        possibleUniquePath(grid, endPos, x, y + 1, visited, answer);
        possibleUniquePath(grid, endPos, x, y - 1, visited, answer);
        // 이후 모든 방문이 끝나면 방문 하지 않은 것으로 처리해 다시 되돌아 간다.
        visited[x][y] = false;
    }

    public boolean allVisited(boolean[][] visited) {
        for(int i = 0; i < visited.length; i++) {
            for(int j = 0; j < visited[0].length; j++) {
                if(!visited[i][j]) {
                    // 만약 visited[i][j] == false 라면 방문 안한것이며 이 if문을 진행한다.
                    // 따라서, 모두 방문 안한것이다. 즉 false 처리
                    return false;
                }
            }
        }

        // 여기까지 왔다면 모두 방문했기 때문에 종료한 것이므로 true를 return
        return true;
    }
}
