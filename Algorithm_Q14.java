import java.util.*;

public class Algorithm_Q14 {
    /*
     * 해당 문제는 배열의 성질을 활용하여, 행의 ptr인 m_ptr과 열의 ptr인 n_ptr의 움직임에 유의하며 풀어야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/spiral-matrix/
     * 위 행렬 움직임에 대한 내용과 함께 이 문제는 해결하기 위해 debug도 함께 활용 했다.
     * 아래 풀이를 확인 하자.
     */ 

    public List<Integer> spiralOrder(int[][] matrix) {
        // m -> 0 to n - 1, n - 1 to 1, 1 to n - 2 순으로 
        // n -> 0 to n - 1, n - 1 to 0, 1 to n - 2, n - 2 to 1 순으로
        // m 은 세로의 길이 (행의 길이), n 은 가로의 길이 (열의 길이)
        // 위와 같은 공식을 활용하여, 총 연산 횟수는 m * n 만큼 이어지는 것을 생각하고,
        // 각 down, right 이동 여부를 체크하여 왼쪽 or 오른쪽 이동을 하며, 이때, 각 이동의 끝점을 _end, _start에
        // 저장해서 문제를 처리한다.
        List<Integer> answer = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int m_start = 1;
        int n_start = 0;
        int m_end = m - 1;
        int n_end = n - 1;
        int m_ptr = 0;
        int n_ptr = 0;
        boolean down = true;
        boolean right = true;

        int count = 0;

        while(count != (m * n)) {
            answer.add(matrix[m_ptr][n_ptr]);
            count += 1;
            if(down == true && right == true) {
                if(n == 1) {
                    // 이때는 down true, right false 인것처럼 움직여야 하므로,
                    right = false;
                    m_ptr += 1;
                    continue;
                }
                n_ptr += 1;
                if(n_ptr == n_end) {
                    n_end = n_end - 1;
                    right = false;
                }
            } else if(down == true && right == false) {
                if(m == 1) {
                    // 이때는 down false, right false 인것처럼 움직여야 하므로,
                    down = false;
                    n_ptr -= 1;
                    continue;
                }
                m_ptr += 1;
                if(m_ptr == m_end) {
                    m_end = m_end - 1;
                    down = false;
                }
            } else if(down == false && right == false) {
                if(n == 1) {
                    // 이때는 down false, right true 인것처럼 움직여야 하므로,
                    right = true;
                    m_ptr -= 1;
                    continue;
                }
                n_ptr -= 1;
                if(n_ptr == n_start) {
                    n_start = n_start + 1;
                    right = true;
                }
            } else {
                // down == false && right == true
                if(m == 1) {
                    // 이때는 down true, right true 인것처럼 움직여야 하므로,
                    down = true;
                    n_ptr += 1;
                    continue;
                }
                m_ptr -= 1;
                if(m_ptr == m_start) {
                    m_start = m_start + 1;
                    down = true;
                }
            }
        }

        return answer;
    }
}
