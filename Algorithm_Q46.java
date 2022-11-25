import java.util.*;

public class Algorithm_Q46 {
    /*
     * 해당 문제는 백트래킹 문제이다. 방문 조건을 잘 체크하면 쉽게 해결할 수 있는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/word-search/
     * 풀이를 확인해보자.
     */

    // 나의 풀이 => DFS + visited 함수를 활용한 방문 처리 진행
    public boolean exist(char[][] board, String word) {
        // 정답인 answer는 함수가 돌아도 그 값이 변경되어 들어갈 수 있도록 pass by reference 로 처리되게 배열로 생성
        boolean[] answer = {false};
        boolean[][] visited = new boolean[board.length][board[0].length];

        // start 지점이 보드의 모든 점에서 부터 시작되니까 2중 루프를 main 함수 안에서는 돌아야함
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                checkStringExist(board, word, "", i, j, 0, answer, visited);
                for(int k = 0; k < board.length; k++) {
                    Arrays.fill(visited[k], false);
                }
            }
        }

        // answer가 길이 1의 배열이므로, 0번째 인덱스를 통해 정답을 리턴한다
        return answer[0];
    }

    // 백트래킹을 위한 함수 생성
    public void checkStringExist(char[][] board, String word, String str, int row, int col, int strIdx, boolean[] answer, boolean[][] visited) {
        // 종료 조건 1 만약 현재 str이 word와 같다면 더 이상 로직을 진행할 이유가 없으므로 answer에 답을 넣고 종료 
        if(str.equals(word)) {
            answer[0] = true;
            return;
        }
        
        // 종료 조건 2 row가 배열의 범위를 벗어날 때
        if(row > board.length - 1 || row < 0) {
            return;
        }

        // 종료조건 3 col이 배열의 범위를 벗어날 때
        if(col > board[0].length - 1 || col < 0) {
            return;
        }

        // 종료 조건 4 만약 해당 row, col 위치에 이미 방문한 적이 있다면 바로 종료
        if(visited[row][col]) {
            return;
        }

        // 만약 row, col 위치의 문자가 현재 타겟 단어의 strIdx 위치의 문자와 같다면 if 조건문을 들어가고 아니면 패스한다
        if(board[row][col] == word.charAt(strIdx)) {
            str += String.valueOf(board[row][col]);
            visited[row][col] = true;

            // 현재 만들어진 str이 word와 같다면 true 만들고 함수 종료
            if(str.equals(word)) {
                answer[0] = true;
                return;
            }
            
            // 위 조건이 만족하지 않는다면, 상 하 좌 우 로 한칸씩만 이동한 후 진행
            checkStringExist(board, word, str, row + 1, col, strIdx + 1, answer, visited);
            checkStringExist(board, word, str, row - 1, col, strIdx + 1, answer, visited);
            checkStringExist(board, word, str, row, col + 1, strIdx + 1, answer, visited);
            checkStringExist(board, word, str, row, col - 1, strIdx + 1, answer, visited);

            // 위 상하좌우 모든 경우를 다 돌았다면 이제 이 위치는 방문하지 않은 것으로 다시 마킹 처리 한다
            visited[row][col] = false;
        } else {
            // if 문을 들어가지 않는다면 이 이상의 백트래킹은 의미가 없으므로 그냥 함수 종료
            return;
        }
    }
}
