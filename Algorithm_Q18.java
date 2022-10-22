import java.util.*;

public class Algorithm_Q18 {
    /*
     * 해당 문제는 행렬을 사용하는 문제이다. 이 문제는 사용하는 공간의 크기를 서로 다르게 해서 풀이할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/set-matrix-zeroes/
     * 풀이를 확인해보자.
     */ 


    /*
     * 나의 풀이
     * 해당 풀이는 어쩃든 0이 되는 요소의 좌표값을 저장하고, 그 좌표값에 대해서 해당하는 행과 열에 모두 0 처리를 위해
     * stack을 사용해 저장한 좌표값을 하나씩 빼서 처리하는 방식을 활용함.
     * 이 방식으로는 각 좌표 값을 저장해야 하기 때문에 최악으로 O(2 * M * N) => O(M * N) 의 공간을 사용하게 된다.
     */
    public void setZeroes_mine(int[][] matrix) {
        Stack<List<Integer>> set = new Stack<>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    set.push(list);
                }
            }
        }

        while(!set.isEmpty()) {
            List<Integer> elemList = set.pop();
            int x = elemList.get(0);
            int y = elemList.get(1);

            for(int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }

            for(int i = 0; i < matrix[x].length; i++) {
                matrix[x][i] = 0;
            }
        }
    }

    /*
     * 상수개의 공간만을 활용하여 처리하는 풀이
     * 해당 풀이의 경우 첫째 행과 열의 요소중 0이 존재하는지 먼저 체크한 이후
     * 각 행렬의 요소중 0이 확인되면 그 좌표의 행과 열의 0번째 index 요소값을 0으로 처리하고,
     * 이후 전체 행렬을 1번째 요소부터 체크할 때 각 행과 열의 0번쨰 요소를 체크해서 만약 둘 중 하나라도 0이면
     * 0으로 처리하도록 진행한다.
     * 이후 첫째 행과 열의 요소중 0이 존재하는지 여부를 확인해서 true 라면 그 첫째 행과 열을 각각 전체 0으로 처리한다.
     */
    public void setZeroes(int[][] matrix) {
        // 행렬 처리를 위해 변수를 2개만 사용하여 풀이하는 방법
        boolean isFirstRowZero = false;
        boolean isFirstcolZero = false;
        
        // 첫째 열에 존재하는 요소 중 0의 존재 여부 체크
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0) {
                isFirstcolZero = true;
            }
        }
        
        // 첫째 행에 존재하는 요소 중 0의 존재 여부 체크
        for(int i = 0; i < matrix[0].length; i++) {
            if(matrix[0][i] == 0) {
                isFirstRowZero = true;
            }
        }

        // 각 행렬의 요소 중 0이 확인되면 그 좌표의 행과 열의 0번째 index의 요소값을 0으로 처리
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 첫째 행과 첫째 열은 이 행렬의 나머지 요소들의 줄이 0으로 처리되기 위해 확인할 지표이므로, 첫째 행과 열
        // 제외한 나머지에 대해 만약 행이나 열의 첫번째 요소가 0이라면 이 요소도 0으로 변경
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 만약 첫번째 열의 값 중 하나라도 0이라면, 이 열의 모든 값을 0으로 처리
        if(isFirstcolZero) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        // 만약 첫번째 행의 값 중 하나라도 0이라면, 이 행의 모든 값을 0으로 처리
        if(isFirstRowZero) {
            for(int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}
