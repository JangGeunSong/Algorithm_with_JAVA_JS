public class Algorithm_Q161 {
    /*
     * 이 문제는 간단한 배열을 활용한 문제다.
     * https://leetcode.com/problems/matrix-diagonal-sum/
     * 풀이를 확인해보자.
     */

    public int diagonalSum(int[][] mat) {
        // 정답을 저장하기 위한 answer 선언
        int answer = 0;
        // matrix의 한 변의 길이를 지정
        int n = mat.length;
        // col은 다른 방향의 대각선을 구하기 위해 지정한 숫자
        // col + i 는 항상 n - 1을 유지하도록 만들면서 순회하면 이 값도 대각선이 된다.
        int col = n - 1;

        // 왼쪽 위에서 부터 오른쪽 아래로 가는 대각선 방향 덧셈
        for(int i = 0; i < n; i++) {
            answer += mat[i][i];
        }

        // 왼쪽 아래에서 부터 오른쪽 위로 가는 대각성 방향 덧셈
        // 이 방향으로 진행될때는 항상 두 index의 합이 n - 1이 되도록 유지하는게 관건이다.
        // 단 i와 col - i가 같다면 더하지 않는다. 왜나하면 이미 위의 로직에서 더한 이력이 있기 때문이다.
        for(int i = 0; i < n; i++) {
            if(col - i == i) {
                continue;
            } else {
                answer += mat[col - i][i];
            }
        }

        // 결과를 리턴한다.
        return answer;
    }
}
