public class Algorithm_Q112 {
    /*
     * 이 문제는 처음 보는 데이터 구조에 대한 구현을 요구하는 문제다.
     * 설명이 복잡하기 때문에 잘 읽고 구현해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/construct-quad-tree/
     * 풀이를 확인해보자.
     */

    // 해당 문제의 구현은
    // https://leetcode.com/problems/construct-quad-tree/solutions/3234579/day-58-devide-and-conquer-easiest-beginner-friendly-sol/
    // 위 링크를 통해 체크 하고 구현 하였다.

    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }

    private Node construct(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        if(rowStart > rowEnd || colStart > colEnd) {
            // 종료 조건 -> 사분면을 탐색하는데 행과 열의 시작점이 끝점보다 크면 탐색이 더 이상 안되므로 종료
            return null;
        }
        // 해당 노드가 4개의 자식이 있다면 false 그게 아니라면 true 이므로, 일단 false 조건이 아니면 true로 둔다
        // 또한 문제에서 모든 그리드에 값을 탐색할 때 현재 행에서 모든 값이 start 값과 같다면
        // 자식들은 전부 null로 처리되고 isLeaf는 true로 리턴하라고 되어 있으므로, 초반에는 일단 모두 같다 치고
        // true로 초기화 한다.
        boolean isLeaf = true;
        int val = grid[rowStart][colStart];

        for(int i = rowStart; i <= rowEnd; i++) {
            for(int j = colStart; j <= colEnd; j++) {
                if(grid[i][j] != val) {
                    isLeaf = false;
                    break;
                }
            }
        }

        if(isLeaf) {
            // 노드가 val이 1이면 val 필드에 true, 0이면 false를 처리하므로, 첫번째 파라미터는 val == 1 로 true, false
            // 정리를 한다.
            return new Node(val == 1, true, null, null, null, null);
        }

        // 위 조건에 걸리지 않는다면, 이제 그리드를 4개의 분면으로 쪼개서 처리 해야 하므로, 각 행과 열의 절반 지점을 구한다.
        int rowMid = (rowEnd + rowStart) / 2;
        int colMid = (colEnd + colStart) / 2;

        // 각 4분면의 자식 노드를 구해준다.
        Node topLeft = construct(grid, rowStart, colStart, rowMid, colMid);
        Node topRight = construct(grid, rowStart, colMid + 1, rowMid, colEnd);
        Node bottomLeft = construct(grid, rowMid + 1, colStart, rowEnd, colMid);
        Node bottomRight = construct(grid, rowMid + 1, colMid + 1, rowEnd, colEnd);
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    // 위에서 사용한 Node 데이터 구조 클래스
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        
        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };


}
