import java.util.*;

public class Algorithm_Q34 {
    /*
     * 해당 문제는 DP를 활용하여 반복 계산을 없에는데 가장 기초적인 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/pascals-triangle/
     * 풀이를 확인해보자.
     */  

    /*
     * 나의 풀이
     * 나의 풀이는 파스칼 삼각형의 정의를 볼 때, 
     * 만약 tri[i][j] 라면 바로 직전 윗칸의 자기 자신 인덱스 요소 + 바로 왼쪽 인덱스 요소 로 계산이 됨을 알 수 있었다.
     * 그리고, 양 끝점은 무조건 1로 고정된다.
     * 이 점을 응용하여 아래 풀이를 작성하였다.
     */
    public List<List<Integer>> generate_mine(int numRows) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list = null;
        
        // i가 1부터 시작하기 떄문에 numRows까지 진행되야 한다.
        // 그리고, 이것 때문에 answer에는 0번 인덱스 부터 채워진다는 것을 생각해서
        // answer에서 list를 가져올 때, i 값을 생각해야 한다.
        // 그래서 반드시 answer에서 get을 할 떄에는 -2를 해야한다.
        for(int i = 1; i <= numRows; i++) {
            list = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(j == 0 || j == i - 1) {
                    list.add(1);
                } else {
                    list.add(answer.get(i - 2).get(j - 1) + answer.get(i - 2).get(j));
                }
            }
            answer.add(list);
        }

        return answer;
    }

    /*
     * 다른 풀이
     * 위 풀이는 i번째 인덱스와 현재 시작이 1부터인 점 때문에 i 인덱스가 -2 가 되어야 해서 다소 혼란스럽다.
     * 아래 풀이는 그것 보다는 더 쉽게 되어 있으므로 참고하자.
     */
    public List<List<Integer>> generate_easier(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> row, pre = null;
		for (int i = 0; i < numRows; ++i) {
			row = new ArrayList<Integer>();
			for (int j = 0; j <= i; ++j) {
				if (j == 0 || j == i) {
					row.add(1);
                } else {
					row.add(pre.get(j - 1) + pre.get(j));
                }
            }
			pre = row;
			res.add(row);
		}
		return res;
    }
}
