import java.util.*;

public class Algorithm_Q140 {
    /*
     * 이 문제는 DFS를 통해 섬의 갯수를 구할 때 조건에 맞춘 결과를 얻어야 한다.
     * 어제 문제를 살짝 비틀어서 구현하면 답을 구할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/number-of-enclaves/
     * 풀이를 확인해보자.
     */

    public int numEnclaves(int[][] grid) {
        // 섬의 갯수를 구하기 위한 변수들을 선언
        int answer = 0;
        List<Integer> landNumber = new ArrayList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                // 섬의 블럭 갯수를 저장하기 위한 변수에 더미를 하나 삽입
                landNumber.add(0);
                if(grid[i][j] == 1 && isEncloseLand(grid, i, j, landNumber)) {
                    // 만약 해당 섬이 정말 enclose하다면 별다른 액션을 취하지 않고 continue
                    continue;
                } else {
                    // 만약 enclose 하지 않다면 해당 더미 블록은 삭제한다.
                    landNumber.remove(landNumber.size() - 1);
                }
            }
        }

        // 모든 리스트를 돌면서 조건에 맞는 land의 블럭수를 모두 다한다
        for(Integer number : landNumber) {
            answer += number;
        }

        return answer;
    }

    public boolean isEncloseLand(int[][] grid, int row, int col, List<Integer> landNumber) {
        // land가 enclose 한지 여부를 체크한다.
        // 종료조건 1 현재 블럭이 경계를 벗어날 때
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            // enclose 하지 않은 것이므로, false 처리
            return false;
        }

        // 종료조건 2 현재 블럭이 물이거나 혹은 방문 했을 때
        if(grid[row][col] == 0) {
            // enclose 하다 생각하고 우선 true 처리
            return true;
        }

        // 현재 블럭은 방문 한 것으로 치기 위해 0으로 (물과 같이) 처리
        grid[row][col] = 0;

        // 현재 index의 land에 (가장 마지막 인덱스가 현재 섬의 더미임) 지금 블럭을 하나 더하고 이를 set
        landNumber.set((int) (landNumber.size() - 1), (int) (landNumber.get(landNumber.size() - 1) + 1));

        // 사방이 enclose 한지 여부를 체크
        boolean left = isEncloseLand(grid, row, col - 1, landNumber);
        boolean right = isEncloseLand(grid, row, col + 1, landNumber);
        boolean up = isEncloseLand(grid, row - 1, col, landNumber);
        boolean down = isEncloseLand(grid, row + 1, col, landNumber);

        // 만약 사방이 모두 enclose 하다면 이는 true로 처리한다.
        return left && right && up && down;
    }
}
