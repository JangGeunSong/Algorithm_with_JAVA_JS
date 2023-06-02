import java.util.*;

public class Algorithm_Q176 {
    /*
     * 이 문제는 BFS를 통해 풀이하는 문제이다.
     * 두 점 사이의 거리를 계산할 때 숫자의 크기가 int형으로 받으면 유실되어 답을 구하지 못하는 경우가 발생할 수 있음에 유의하자.
     * https://leetcode.com/problems/detonate-the-maximum-bombs/
     * 풀이를 확인해보자.
     */

    public int maximumDetonation(int[][] bombs) {
        // 하나의 폭탄이 옆의 폭탄에 영향을 미치는지 확인하기 위해서는 한개의 폭탄이 터질때 그 폭탄의 위치
        // 그리고 다른 폭탄들을 확인해서 그 폭탄의 위치를 확인해주고, 이 두 폭탄 사이의 거리를 위치를 통해
        // 체크한 후 이때 처음 폭파시킨 폭탄의 반지름과 비교해서 반지름보다 작다면, 영향을 받았음을 체크한다.
        // 그리고 영향을 받았을 때, queue에 포함시킨 후 다음 depth 에서는 이렇게 폭파시킨 폭탄을 queue에 넣었으니
        // 여기서 데이터를 체크한 후 영향 받은 곳들을 확인해 추가하면 된다.
        
        // ### 두 점 사이의 거리 = sqrt((y_2 - y_1)^2 + (x_2 - x_1)^2)
        Queue<int[]> queue = new LinkedList<>();
        // 모든 폭탄을 체크해 볼건데, 각 폭탄이 시작점이 되었을 때 현재까지 터진 폭탄의 수
        int currBlownBombs = 0;
        // 최종 정답으로 가장 많은 폭탄이 터졌을 때 그 수를 저장
        int answer = 0;
        // 해당 폭탄이 이미 폭발한 상태인지 체크하기 위한 배열
        boolean[] visited = new boolean[bombs.length];

        // BFS 처리
        for(int i = 0; i < bombs.length; i++) {
            // 새롭게 모든 폭탄을 리셋하고 터뜨릴 폭탄을 선택 한다면, 전부 미 폭파 상태로 셋업
            Arrays.fill(visited, false);

            // i 번째 폭탄을 먼저 터뜨릴 계획이므로, 해당 폭탄이 터지니까 1을 더하고, 큐에 삽입한다.
            currBlownBombs = 1;
            queue.offer(bombs[i]);
            visited[i] = true;

            // BFS 순회 진행
            while(!queue.isEmpty()) {
                // 터지는 폭탄을 큐에서 꺼내고
                int[] bomb1 = queue.poll();
                
                // 각 좌표 및 반지름을 세팅
                int x1 = bomb1[0];
                int y1 = bomb1[1];
                int r = bomb1[2];

                // 남아 있는 모든 폭탄을 순회 진행
                for(int j = 0; j < bombs.length; j++) {
                    // 이미 터진 폭탄이라면 continue 처리한다. 로직을 작동할 필요 없다
                    if(visited[j]) {
                        continue;
                    }
                    // 아직 터지지 않은 폭탄 일때는 좌표값을 setup
                    int x2 = bombs[j][0];
                    int y2 = bombs[j][1];

                    // 두 점의 좌표가 있으니까 이때 두 점 사이의 거리를 계산한다.
                    double x = x2 - x1;
                    double y = y2 - y1;
                    double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    // 얻은 두 점 사이의 거리가 현재 터뜨려지는 폭탄의 반지름보다 작거나 같다면, 지금 폭탄은
                    // 반지름 r 인 큐에서 꺼낸 폭탄이 터질때 영향 범위 안에 있는 것이므로, 폭발하게 된다.
                    if(r >= distance) {
                        // 따라서 이 폭탄은 터질 예정이므로, 큐에 삽입한다 (연쇄 폭팔 할 다른 폭탄이 있는지 체크하기 위해)
                        queue.offer(bombs[j]);
                        visited[j] = true;
                        currBlownBombs += 1;
                    }
                }

                // 최종적으로 i번째 폭탄을 골랐을 때 터지게 될 폭탄의 수와 지금까지 저장한 최대 폭파 폭탄수를
                // 비교해서 가장 큰 값을 구한다.
                answer = Math.max(answer, currBlownBombs);
            }
        }
        
        // 답을 리턴한다.
        return answer;
    }
}
