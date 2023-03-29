import java.util.*;

public class Algorithm_Q133 {
    /*
     * 이 문제는 DP를 통한 메모를 진행하여 답을 찾아야 하는 문제이다.
     * 정렬을 하고, 정렬된 배열 속에서 누적 합을 구해야 한다. 누적합의 경우 정확도를 위해 이중 루프를 돌았는데, 사실 그렇게 안해도 상관은 없다.
     * https://leetcode.com/problems/reducing-dishes/solutions/3353418/python-java-c-simple-solution-easy-to-understand/?orderBy=hot
     * 위 경로는 누적합을 구하는 방법이 소개되어 있으니 참고해보자. (풀이 코드에는 활용하지 않았다.)
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/reducing-dishes/
     * 풀이를 확인해보자.
     */

    // 풀이 1. 퀵 정렬을 사용한 풀이 (내림차순 정렬 후 로직 진행)
    public int maxSatisfaction_quickSortVersion(int[] satisfaction) {
        // 방법 1 reverse order로 배열 정렬 후 로직 진행
        int n = satisfaction.length;
        int[] dp = new int[n + 1];
        int answer = 0;

        // 아래 참고한 퀵 소트를 기반으로 구현해 배열을 크기 역순으로 재 정렬 (nlogn)
        quickSort(satisfaction, 0, n - 1);

        dp[0] = satisfaction[0];

        // 배열을 돌면서 dp에는 이전 DP 값 + 현재 위치까지 모든 음식의 만족도 값을 전부 한번씩 더한 결과를 저장
        for(int i = 1; i < n; i++) {
            dp[i] = dp[i - 1];
            for(int j = 0; j <= i; j++) {
                dp[i] += satisfaction[j];
            }
        }

        // DP에 모든 값을 돌면서 가장 큰 값을 획득
        for(int val : dp) {
            answer = Math.max(answer, val);
        }

        return answer;
    }

    // 해당 문제는 quick sort 가 필요한 문제로 아래 quick 소트 방법을 찾아서 추가 구현 하였다.
    public int partition(int[] arr, int left, int right) {
        int pivot = 0;
        int tmp = 0;
        int low = left;
        int high = right + 1;
        pivot = arr[low];

        do{
            /* list[low]가 피벗보다 크면 계속 low를 증가 */
            do {
                low++; // low는 left+1 에서 시작
            } while (low <= right && arr[low] > pivot);

            /* list[high]가 피벗보다 작으면 계속 high를 감소 */
            do {
                high--; //high는 right 에서 시작
            } while (high >= left && arr[high] < pivot);

            // 만약 low와 high가 교차하지 않았으면 list[low]를 list[high] 교환
            if(low<high){
                tmp = arr[low];
                arr[low] = arr[high];
                arr[high] = tmp;
            }
        } while (low < high);

        tmp = arr[left];
        arr[left] = arr[high];
        arr[high] = tmp;

        return high;
    }

    public void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int pivot = partition(arr, left, right);

            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    // 풀이 2. Arrays 내 sort 함수를 통한 풀이 (오름차순 정렬)
    public int maxSatisfaction_Arrays_sort_solution(int[] satisfaction) {
        // 방법 2 오름차순으로 배열 정렬 후 로직 진행
        int n = satisfaction.length;
        int[] dp = new int[n + 1];
        int answer = 0;

        // Array.sort를 통해 오름차순 정렬
        Arrays.sort(satisfaction);
    
        // 0번 DP 값에는 이 배열의 마지막 값을 넣는다.
        dp[0] = satisfaction[n - 1];

        // 배열을 돌면서 dp에는 이전 DP 값 + 현재 위치까지 모든 음식의 만족도 값을 전부 한번씩 더한 결과를 저장
        for(int i = 1; i < n; i++) {
            dp[i] = dp[i - 1];
            // 역순으로 돌기 때문에 가장 마지막 원소 + ... + n - i - 1 번째 원소 까지 더한다
            for(int j = n - 1; j >= n - i - 1; j--) {
                dp[i] += satisfaction[j];
            }
        }

        // DP에 모든 값을 돌면서 가장 큰 값을 획득
        for(int val : dp) {
            answer = Math.max(answer, val);
        }

        return answer;
    }
}
