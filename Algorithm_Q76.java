import java.util.*;

public class Algorithm_Q76 {
    /*
     * 그리디 문제이다. 그리디를 풀기 위해 배열 정렬을 어떻게 해야할지 고민해야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        int[][] arr = new int[n][2];
        int answer = 0;
        int largeXEnd = 0;

        // 파라미터 배열의 수정이 있어야 하므로, 복사를 진행한다.
        for(int i = 0; i < n; i++) {
            arr[i][0] = points[i][0];
            arr[i][1] = points[i][1];
        }

        // 2차원 배열의 정렬을 진행 우선 x_start 순으로 정렬하되, 만약 x_start가 같다면 x_end 크기로 내림차순 정렬
        Arrays.sort(arr, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // 일단 첫번째 풍선은 무조건 터뜨려야 하므로, 
        // largeXStart = 첫번째 풍선의 x 축 시작
        // largeXEnd = 첫번째 풍선의 X 축 끝 
        // 화살 1개 소모 -> answer + 1
        largeXEnd = arr[0][1];
        answer += 1;
        // 이후 반복문을 돌면서 만약 다음 풍선의 x축 시작이 현재 가장 큰 x축의 끝 즉, 가장 처음 풍선을 터뜨릴 때
        // x축 끝에 값 사이에 걸린다면 이 화살 하나로 모두 터뜨릴 수 있다. 여기서 핵심은 교차점이 있어야 한다는 점이다.
        // 따라서 가장 큰 X end 값은 현재 x end 와 지금 풍선의 x end 중 작은 값을 고른다.
        // 이후 교차점이 없으면 화살 하나를 더 소모하고 x end를 그 풍선의 끝 값으로 갱신한다.
        for(int i = 1; i < n; i++) {
            if(arr[i][0] <= largeXEnd) {
                largeXEnd = Math.min(largeXEnd, arr[i][1]);
            } else {
                answer += 1;
                largeXEnd = arr[i][1];
            }
        }

        return answer;
    }
}
