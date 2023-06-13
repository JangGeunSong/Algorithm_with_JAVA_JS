import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Algorithm_Q182 {
    /*
     * 이 문제는 해시맵을 통해 풀이해야 하는 문제이다.
     * 여기서 하나 배운게 있는데, 바로 hashset의 contains는 equals를 통해 구현되어 있다는 점이다.
     * 즉 arraylist를 통해 contains를 하면 답이 나올 수가 없다. 참고하자.
     * https://leetcode.com/problems/equal-row-and-column-pairs/
     * 풀이를 확인해보자.
     */

    public int equalPairs(int[][] grid) {
        /*
         * 이 문제에서 가장 중요한 점은 ArrayList를 통해 아래 로직을 만들고 equals를 돌리거나 하면 답이 될 수 없다.
         * 그 이유는 equals의 경우 String과 같은 class는 Object의 equals를 super class로 상속받은 후 override 해서 쓰지만
         * arrayList는 그냥 reference 체크로 이 함수를 쓰고 있어 메모리 주소까지 같아야 같은 것으로 체크하기 때문이다.
         * 즉, contains 자체가 equals를 사용해 체크하기 때문에 이렇게 풀면 답이 안되므로, String을 사용해 풀이 해야 한다.
         */
        int answer = 0;
        int n = grid.length;
        // HashMap을 활용하여 해당 String으로 몇개의 데이터가 있는지 체크하기
        Map<String, Integer> set = new HashMap<>();

        // HashMap에 row로 만든 배열을 저장하면서 해당 배열을 키값으로 몇개 존재하는지 체크
        for(int[] arr : grid) {
            String a = Arrays.toString(arr);
            set.put(a, set.getOrDefault(a, 0) + 1);
        }

        // 열 만으로 배열의 수를 체크하고 이제 그 값을 통해 짝의 수를 더한다.
        // 이 짝의 수는 열로 만든 배열과 같은게 행으로 몇개가 있었는지가 짝의 수로 얻어지게 된다.
        for(int i = 0; i < n; i++) {
            int[] c = new int[n];

            for(int j = 0; j < n; j++) {
                c[j] = grid[j][i];
            }

            String a = Arrays.toString(c);

            if(set.get(a) != null) {
                answer += set.get(a);
            }
        }

        return answer;
    }
}
