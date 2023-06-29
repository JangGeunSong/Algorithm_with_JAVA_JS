import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Algorithm_Q190 {
    /*
     * 이 문제는 높은 난이도의 BFS + bit 연산 문제이다.
     * https://leetcode.com/problems/shortest-path-to-get-all-keys/
     * 문제 해결을 위해 어떤 고민을 해야 하는지
     * https://leetcode.com/problems/shortest-path-to-get-all-keys/solutions/3695269/beats-100-chatgpt-c-java-python-bfs-approach/
     * 위 경로를 참고 하자
     * 풀이를 확인해보자.
     */

    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();

        Map<Character, Integer> key_bit = new HashMap<>();

        int bit_start = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(Character.isLowerCase(grid[i].charAt(j))) {
                    key_bit.put(grid[i].charAt(j), bit_start);
                    bit_start += 1;
                }
            }
        }

        int mask_end = (1 << bit_start) - 1;
        int mask_size = (1 << bit_start);

        boolean[][][] memo = new boolean[n][m][mask_size];

        int[] start = new int[3];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i].charAt(j) == '@') {
                    // 시작점을 찾아 mask를 0 으로 메모 처리
                    start = new int[] {i,j,0};
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        int step = 0;

        while(!q.isEmpty()) {
            // 저장된 방문 가능한 경로만큼 큐를 빼서 진행해야 함.
            int size = q.size();

            for(int k = 0; k < size; k++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];
                int mask = curr[2];
                
                // 배열의 범위를 벗어나는 위치라면 그냥 넘어감
                if(row < 0 || row >= n || col < 0 || col >= m) {
                    continue;
                }

                if(grid[row].charAt(col) == '#') {
                    // 벽인 경우 어차피 지나가지 못하므로 continue 처리
                    continue;
                }

                if(Character.isUpperCase(grid[row].charAt(col))) {
                    // 대문자 즉 자물쇠라면, 지금 현재 자물쇠에 맞는 열쇠가 있는지 체크하고 있다면 진행하고
                    // 아니면 continue 처리 이때 열쇠 유무는 bit로 저장된게 1씩 증가한 값이고
                    // 이를 mask로 1 을 bit shift 해서 얻었기 때문에 이를 활용해서 & 연산 했을때 만약
                    // 있다면 1 없으면 false 이니까 0 임을 응용하여 처리한다.
                    if((mask & (1 << key_bit.get(Character.toLowerCase(grid[row].charAt(col))))) == 0) {
                        continue;
                    }
                }

                if(Character.isLowerCase(grid[row].charAt(col))) {
                    // 만약 열쇠를 찾은 거라면 열쇠 하나를 추가한다.
                    // 이는 or 연산을 통해 없으면 true로 떨어지는 것을 활용한다.
                    mask |= (1 << key_bit.get(grid[row].charAt(col)));
                }

                if(mask == mask_end) {
                    // 만약 모든 열쇠를 다 얻었다면 step을 리턴
                    return step;
                }

                // 만약 현재 row col 위치에 mask 값인 상태에서 true면 방문을 한 것이기 때문에 다시 방문을 안하도록 처리한다.
                if(memo[row][col][mask]) {
                    continue;
                }

                // 해당 위치를 아직 방문 안했던 거라면 true 처리로 방문 했음을 표시
                memo[row][col][mask] = true;

                // 행렬의 상 하 좌 우 를 해당 mask 즉 열쇠를 들고 모두 방문 시작
                q.offer(new int[] {row + 1, col, mask});
                q.offer(new int[] {row - 1, col, mask});
                q.offer(new int[] {row, col + 1, mask});
                q.offer(new int[] {row, col - 1, mask});
            }
            // 위 처리가 끝나면 시작점에서 한 step 밟은 것으로 처리
            step += 1;
        }

        // 모든 반복을 돌았는데 원하는 답이 없다면 경로가 없는 것이기 때문에 -1을 리턴한다.
        return -1;
    }
}
