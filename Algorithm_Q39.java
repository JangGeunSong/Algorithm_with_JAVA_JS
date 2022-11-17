import java.util.*;

public class Algorithm_Q39 {
    /*
     * 해당 문제는 꽤 난이도 있는 백트래킹 문제이다. 원하는 조건을 잘 만들었으나 나의 풀이가 잘 먹히지 않는다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/n-queens/
     * 풀이를 확인해보자.
     */  
    static public void main(String[] args) {
        solveNQueens(3);
    }
    
    // 나의 풀이
    // 조건은 맞게 쓴거 같은데 답이 안나오고 이상하게 떨어진다.
    // 해당 풀이는 visited를 피하여 풀기 위해 작성한 답안이다.
    // 좀 더 풀어서 답을 만들어 낼 예정이다.
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        List<String> list = new ArrayList<>();

        findNQueenBoard(n, 0, 0, answer, list);

        return answer;
    }


    public static void findNQueenBoard(int n, int piece, int lineNumber, List<List<String>> answer, List<String> list) {
        if(lineNumber == n) {
            if(piece == n) {
                answer.add(new ArrayList<>(list));
            }
        }

        int queen_x = 0;
        int queen_y = 0;

        int y_position = 0;

        boolean isCatched = false;

        String line = "";

        for(int i = lineNumber; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // line string과 isCatched 를 초기화 한다.
                line = "";
                isCatched = false;
                for(int k = 0; k < list.size(); k++) {
                    queen_x = k;
                    queen_y = list.get(k).indexOf('Q');
                    if(
                        queen_x == i || 
                        queen_y == j || 
                        Math.abs(queen_y - queen_x) == Math.abs(j - i) ||
                        Math.abs(queen_y + queen_x) == Math.abs(j + i)
                        ) {
                        // 해당 조건에 들어온다면, 직선 or 대각선으로 잡히는 위치에 존재할 때의 조건이다.
                        // x = i, y = j는 직선에 들어올 때, x + y == i + j 는 왼쪽 아래 대각선일 떄,
                        // y - x == j - i 는 오른쪽 아래 대각선 일때
                        isCatched = true;
                    } else {
                        // 위 condition에 도달하지 않는다면, 잡힐 일이 없으므로 continue 한다.
                        continue;
                    }
                }
                // 위 반복문을 통해 확인해 보았을 때, isCatched가 false가 된다면, 현재 i, j 위치에서
                // 문제 조건을 만족하기 때문에, 이때의 j를 y_position으로 잡는다.
                // 그리고 퀸 말을 두고 재귀로 다음으로 넘어간다.
                for(int pos = 0; pos < n; pos++) {
                    line += ".";
                }
                if(!isCatched) {
                    y_position = j;
                    char[] arr = line.toCharArray();
                    arr[y_position] = 'Q';
                    list.add(String.valueOf(arr));
                    findNQueenBoard(n, piece + 1, lineNumber + 1, answer, list);
                    list.remove(list.size() - 1);
                } else {
                    // 만약 잡히는 위치에 있다면, continue 해서 다음 위치로 이동한다.
                    // 일단 안 잡히는 케이스에도 line은 하나 지나갔다 치고, 진행한다.
                    // 이때는 말을 놓지 않았다는 점을 체크하여 진행한다.
                    list.add(line);
                    findNQueenBoard(n, piece, lineNumber + 1, answer, list);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    /*
     * visited 배열을 만들어서 풀이하는 방식
     * 이 방식을 피하기 위해 위의 풀이를 만들었으나 제대로 되지 않았다.
     */
    public List<List<String>> solveNQueens_solution_use_visited_arr(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> answer = new ArrayList<>();
        queens(board,0,answer);
        return answer;
    }

    void queens(boolean[][] board,int row,List<List<String>> answer){
        
        if(row==board.length){
            List<String> res = insert(board);
            answer.add(res);
            return;
        }
        
        // Placing Queens and Checking for every row and column
        for(int col=0;col<board.length;col++){
            //place queen if Safe
            if(isSafe(board,row,col)){
                board[row][col] = true;
                queens(board,row+1,answer); //Recursive call
                board[row][col] = false;    // Backtrack
            }
        }
    }
    
    boolean isSafe(boolean[][] board, int row ,int col){
        
        // Vertical row
        for(int i=0;i<row;i++){
            if(board[i][col])
                return false;
        }
        
        // Left diagonal
        int maxLeft = Math.min(row,col);
        for(int i=1;i<=maxLeft;i++){
            if(board[row-i][col-i]){
                return false;
            }
        }
        
        // Right diagonal
        int maxRight = Math.min(row,board.length-col-1);
        for(int i=1;i<=maxRight;i++){
            if(board[row-i][col+i]){
                return false;
            }
        }
        return true;
    }
     
    List<String> insert(boolean[][] board){
        List<String> ans = new ArrayList<>();
        for(boolean[] row: board){
            String S = "";
            for(boolean element:row){
                if(element){
                    S += "Q";
                }
                else
                    S += ".";
            }
            ans.add(S);
        }
        return ans;
    }
}
