import java.util.*;

public class Algorithm_Q175 {
    /*
     * 이 문제는 BFS를 통해 풀이하는 문제이다.
     * 8방향에 대해 모두 체크해야 한다는 점에 유의하며 풀이를 진행해보자.
     * https://leetcode.com/problems/shortest-path-in-binary-matrix/
     * 풀이를 확인해보자.
     */

    public int shortestPathBinaryMatrix(int[][] grid) {
        // BFS 모델을 통한 풀이를 진행한다.
        int n = grid.length;
        int m = grid[0].length;
        int row = 0;
        int col = 0;
        int numOfAdjNodes = 0;
        int currNumOfAdjNodes = 0;
        int answer = 1;

        boolean[][] visited = new boolean[n][m];

        Queue<Data> queue = new LinkedList<>();

        // 만약 지금 0,0 에서 진입 자체가 불가능할 경우 논리 진행이 안되므로 바로 -1 리턴
        if(grid[row][col] == 1) {
            return -1;
        }

        // 가장 처음 시작점을 offer 처리
        queue.offer(new Data(grid[row][col], row, col));
        // 최종 인접 노드의 수를 1로 초기화
        numOfAdjNodes = 1;
        visited[row][col] = true;
    
        while(!queue.isEmpty()) {
            Data node = queue.poll();
            // 큐에서 하나 뺄 때 지금 depth에서의 최종 인접 노드의 수에서 1을 뺀다.
            numOfAdjNodes -= 1;

            // 지금 row, col을 담고
            row = node.row;
            col = node.col;

            // 해당 값이 원하는 가장 오른쪽 아래 끝에 도달하여 있다면 이때의 depth를 리턴
            if(row == n - 1 && col == m - 1) {
                return answer;
            }

            // 위 조건이 맞지 않는다면, 0 인 값에 대해서만 큐에 인접 노드로 삽입 처리
            // 8방향 모두를 체크해야 하는점을 명심하면서 아래 로직을 진행한다.
            if(row > 0 && col > 0) {
                // 왼쪽 위 노드 체크
                if(grid[row - 1][col - 1] == 0 && visited[row - 1][col - 1] == false) {
                    queue.offer(new Data(grid[row - 1][col - 1], row - 1, col - 1));
                    visited[row - 1][col - 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(row > 0) {
                // 위 노드 체크
                if(grid[row - 1][col] == 0 && visited[row - 1][col] == false) {
                    queue.offer(new Data(grid[row - 1][col], row - 1, col));
                    visited[row - 1][col] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(row > 0 && col < m - 1) {
                // 오른쪽 위 노드 체크
                if(grid[row - 1][col + 1] == 0 && visited[row - 1][col + 1] == false) {
                    queue.offer(new Data(grid[row - 1][col + 1], row - 1, col + 1));
                    visited[row - 1][col + 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(col < m - 1) {
                // 오른쪽 노드 체크
                if(grid[row][col + 1] == 0 && visited[row][col + 1] == false) {
                    queue.offer(new Data(grid[row][col + 1], row, col + 1));
                    visited[row][col + 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(row < n - 1 && col < m - 1) {
                // 오른쪽 아래 노드 체크
                if(grid[row + 1][col + 1] == 0 && visited[row + 1][col + 1] == false) {
                    queue.offer(new Data(grid[row + 1][col + 1], row + 1, col + 1));
                    visited[row + 1][col + 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(row < n - 1) {
                // 아래 노드 체크
                if(grid[row + 1][col] == 0 && visited[row + 1][col] == false) {
                    queue.offer(new Data(grid[row + 1][col], row + 1, col));
                    visited[row + 1][col] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(row < n - 1 && col > 0) {
                // 왼쪽 아래 노드 체크
                if(grid[row + 1][col - 1] == 0 && visited[row + 1][col - 1] == false) {
                    queue.offer(new Data(grid[row + 1][col - 1], row + 1, col - 1));
                    visited[row + 1][col - 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            if(col > 0) {
                // 왼쪽 노드 체크
                if(grid[row][col - 1] == 0 && visited[row][col - 1] == false) {
                    queue.offer(new Data(grid[row][col - 1], row, col - 1));
                    visited[row][col - 1] = true;
                    currNumOfAdjNodes += 1;
                }
            }

            // 현재 depth에서의 인접 노드 수가 0이 되었다면, 여태까지 신규 depth의 인접 노드수를 다시 세팅한다.
            if(numOfAdjNodes == 0) {
                numOfAdjNodes = currNumOfAdjNodes;
                currNumOfAdjNodes = 0;
                // depth를 1 올려준다.
                answer += 1;
            }
        }
        
        // 모든 BFS를 통해 순회 했는데도 answer가 떨어지지 않는다면 답이 없는 것이므로 -1을 리턴한다.
        return -1;
    }

    public class Data {
        public int signal;
        public int row;
        public int col;

        public Data(int signal, int row, int col) {
            this.signal = signal;
            this.row = row;
            this.col = col;
        }
    }
}
