import java.util.*;

public class Algorithm_Q14 {
    /*
     * 해당 문제는 배열의 성질을 활용하여, 행의 ptr인 m_ptr과 열의 ptr인 n_ptr의 움직임에 유의하며 풀어야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/spiral-matrix/
     * 위 행렬 움직임에 대한 내용과 함께 이 문제는 해결하기 위해 debug도 함께 활용 했다.
     * 아래 풀이를 확인 하자.
     */ 

    /*
     * 2023년 5월 9일 다시 한번 일일 풀이 문제로 나오면서 해당 문제를 다시 접하게 되었다.
     * 이번에는 아래와 같이 curr ptr을 활용하지 않고, left, right, top, bottom 값을 이용하여 순회 할수록 
     * 그들이 점점 간격이 좁아지는 현상을 이용해 풀이하였다.
     * 풀이 코드를 확인해보자.
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

    // 2023년 5월 9일 풀이 코드
    public List<Integer> spiralOrder_20230509_solution(int[][] matrix) {
        // 과거에 풀었던 문제이지만 다시 한번 풀어 본다.
        // 이 문제를 해결하기 위해서는 우선 현재 오른쪽 이동인지
        // 그리고 아래로 이동인지 체크하면서 움직여야 한다.
        // 또한 한번 끝까지 이동했다면 이제 그 끝점이 계속해서 -1로 그리고 +1로 경계를 줄여주는 작업을 해야 한다.
        List<Integer> answer = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = m - 1;

       while(top <= bottom && left <= right) {
           // 현재 맨 윗줄의 맨 왼쪽에서 부터 시작하니까 오른쪽으로 이동하면서 리스트에 값을 쌓는다.
           for(int i = left; i <= right; i++) {
               answer.add(matrix[top][i]);
           }
           // 모든 값을 다 쌓았다면 맨 윗줄은 이제 더 이상 방문이 필요 없으므로, top을 1 더해준다.
           top += 1;
           
           // 이제 top + 1 위치에 있는 right에 원소부터 시작해서 바닥까지 이동 한다.
           for(int i = top; i <= bottom; i++) {
               answer.add(matrix[i][right]);
           }
           // 이제 오른쪽 끝의 모든 값을 다 방문 했으므로, 오른쪽 끝은 더 이상 갈 일이 없으므로, right는 1 빼준다.
           right -= 1;
           
           /*
            *  아래와 같이 로직이 이동하는 이유는 중복으로 또 들어가는 요소를 막기 위해서이다.
            *  중복으로 들어가는 이유는 while의 조건이 같을때에도 로직이 반복되는 경우가 있기 때문인데
            *  이때 만약 좌우 확은 위 아래중 하나의 요소는 같은 값이지만, 양 옆은 다르거나 반대의 경우
            *  이미 입력된 값이 같은 같까지 돌게되는 반복문에 의해 또 들어가는 현상이 있다.
            *  따라서 이를 막기 위해 아래 로직이 필요하다.
            */
           // 왼쪽, 그리고 위로 이동할 때에는 항상 top <= bottom 그리고 left <= right 인 경우에만 해야 한다.
           if(top <= bottom) {
               // 여기서 bottom에 있는 모든 라인을 빼준 right 부터 왼쪽으로 이동해야 하므로,
               for(int i = right; i >= left; i--) {
                   // 1씩 빼는게 왼쪽 이동임을 생각하며 반복을 돈다.
                   answer.add(matrix[bottom][i]);
               }
               // 이제 이 bottom은 다시 올 필요가 없으니 bottom에 1을 뺀다
               bottom -= 1;
           }
           
           if(left <= right) {
               // 위와 같은 논리로 이제 left 라인의 수직 방향을 역순으로 top 까지 (처음 top에서 1 더해진 값) 이동하며 
               // 리스트에 추가한다.
               for(int i = bottom; i >= top; i--) {
                   answer.add(matrix[i][left]);
               }
               // 이제 이 left 줄의 수직 방향은 영원히 올일이 없으므로, left를 1 더해준다.
               left += 1;
           }
           
       }

       // 위 과정을 top과 bottom이 같아지는 시점까지, 그리고 left와 right가 같아지는 시점까지 계속해 돌면
       // 시계방향으로 계속해서 돌아가게 됨을 알 수 있다.
       // 이렇게 얻은 결과 answer를 리턴한다.
       return answer;
    }
}
