import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithm_Q184 {
    /*
     * 이 문제는 어려운 DFS 및 BST 제작을 묻는 문제이다.
     * 도전에 가까운 문제로 이해부터 풀이까지 모두 한번 보도록 하자.
     * https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/
     * 풀이를 확인해보자.
     */
    private long mod = (long)1e9 + 7;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int m = nums.length;

        // 현재 존재하는 m x m 표 안에서 파스칼의 삼각형(이항계수 계산 테이블)을 만든다.
        table = new long[m][m];

        // 이 삼각형은 높이와 대각선이 모두 1로 세팅된다 (그 이유는 nC0, nCn 이 모두 1이기 때문이다 (조합에서))
        for(int i = 0; i < m; ++i) {
            table[i][0] = table[i][i] = 1;
        }

        // 그리고 나머지 조합의 경우의 수도 모두 구하는데, 이때는 표 안에서 이렇게 값이 구해진다.
        // 이항계수 (i, j)는 (i - 1, j)  + (i - 1, j - 1) 이다. 그리고 나면 n개 중 k 개를 구하는 조합의 수가
        // 이 테이블의 table[n][k]로 얻어 진다.
        for(int i = 2; i < m; i++) {
            for(int j = 1; j < i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % mod;
            }
        }

        List<Integer> arrList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // DFS를 통해 값을 얻는다.
        return (int)((dfs(arrList) - 1) % mod);
    }

    // 배열을 리스트로 변환해 받은 후 해당 리스트로 같은 BST를 만드는 경우의 수를 구하는 DFS를 진행
    public long dfs(List<Integer> list) {
        int m = list.size();
        
        // 리스트의 길이가 보다 작다면, 나열 순서는 딱 1가지 뿐이므로, 1을 리턴
        if(m < 3) {
            return 1;
        }

        List<Integer> leftNode = new ArrayList<>();
        List<Integer> rightNode = new ArrayList<>();

        // 리스트의 맨 첫번째 값이 root 노드일때, 작으면 left 노드 리스트로 크면 right 노드 리스트로 이동
        for(int i = 1; i < m; ++i) {
            if(list.get(i) < list.get(0)) {
                leftNode.add(list.get(i));
            } else {
                rightNode.add(list.get(i));
            }
        }

        // 각 서브 노드들의 경우의 수를 구한다.
        long leftWay = dfs(leftNode) % mod;
        long rightWay = dfs(rightNode) % mod;

        //left와 right는 크기 순서대로 자른 후 또 경우의 수를 구했는데, 이는 양쪽이 독립적으로 시행 되었으므로, 곱한다.
        // 그리고 현재 위치 즉, 이 리스트의 사이즈에서의 파스칼의 삼각형 테이블에서 값을 얻는다.
        // 이때 위치는 m - 1, leftnode.size 로 구한다.
        return (((leftWay * rightWay) % mod) * table[m - 1][leftNode.size()]) % mod;
    }
}
