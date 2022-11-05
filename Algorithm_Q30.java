public class Algorithm_Q30 {
    /*
     * 해당 문제는 행렬의 회전 문제이다.
     * 메모리를 더 많이 사용한다면, index의 변화되는 부분만 식을 사용해 풀이하면 된다.
     * 하지만 해당 문제는 in place로 추가 메모리를 쓰지 않고 연산해야 하므로, 어렵다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/rotate-image/
     * 풀이를 확인해보자.
     */  

    /*
     * 풀이 코드
     * 매우 아이디어가 신박하다. 위 아래로 서로 뒤집고, 그다음 대칭되는 부분만 서로 교체하면 회전이 된다.
     * 매우 좋은 전략이므로, 꼭 기억하자.
     */
    public void rotate(int[][] matrix) {
        // matrix[i].length - 1 - i => new column number
        // j => new row number
        // 위 공식을 활용하면 추가 메모리를 사용해 90도 회전한 배열을 얻을 수 있다.
        // 각도에 따라 변화하는 요소값은 index의 변화를 관찰하면서 체크하면 된다. 
        // 하지만 in place 회전을 해야 하므로, 위와 같은 처리를 할 수 없으므로, 아래 내용을 따른다.
        // 만약 시계방향이 아니라, 반 시계 방향이라면 bottom과 top의 뒤집기가 아니라, left와 right를 서로 뒤집고,
        // swap을 하면 된다.
        int tmp = 0;
        int n = matrix.length;
        // bottom 과 top을 서로 뒤집는다.
        // 주의할 점은 중간에 있는 애들도 서로 bottom과 top이 바뀌어야 한다. 
        for(int x = 0; x < n / 2; x++) {
            for(int y = 0; y < n; y++) {
                tmp = matrix[x][y];
                matrix[x][y] = matrix[n - 1 - x][y];
                matrix[n - 1 - x][y] = tmp; 
            }
        }
        
        // x, y 좌표에 index가 서로 대칭되는 요소끼리 교환한다.
        // 즉, element matrix[x][y] <=> matrix[y][x]
        // 이때, 전체를 모두 돌면, 다시 제자리로 돌아오기 때문에,
        // y의 경우 x보다 큰 경우만 swap 한다.
        // 즉 왼쪽 위에서 오른쪽 아래로 사선을 자를 때, 오른쪽 위에 있는 영역만 swap
        // 작업을 한다고 생각하면 된다.
        for(int x = 0; x < n; x++) {
            for(int y = x; y < n; y++) {
                tmp = matrix[x][y];
                matrix[x][y] = matrix[y][x];
                matrix[y][x] = tmp;
            }
        }

        return;
    }
}
