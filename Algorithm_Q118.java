import java.util.*;

public class Algorithm_Q118 {
    /*
     * 이 문제는 이진 탐색을 활용하여 푸는 문제다.
     * 하지만 쉬움 난이도라 단순한 순회 만으로도 문제가 해결된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/kth-missing-positive-number/
     * 풀이를 확인해보자.
     */

    // 단순 순회 하기
    public int findKthPositive_simple_iterate(int[] arr, int k) {
        // 비어있는 숫자들의 배열을 만들기 위한 ArrayList 생성
        List<Integer> missingPosArr = new ArrayList<>();
        // 현재 숫자가 어떤 값인지 알기 위한 number 값 선언
        int number = 1;

        // arr을 순회하면서 빠져 있는 값을 얻기위한 순회를 처리
        for(int i = 0; i < arr.length; i++) {
            // 현재 number가 arr[i] 보다 작으면 while 문을 돌면서 비어있는 숫자들을 리스트에 추가
            while(number < arr[i]) {
                missingPosArr.add(number);
                number += 1;
            }
            // 현재 숫자는 지금 나타난 배열의 값 + 1을 한다. 다음 해당 arr[i] 값은 이미 있어서 고려 X
            number = arr[i] + 1;
        }

        // 만약 K 보다 비어있는 숫자를 채운 리스트의 사이즈가 크거나 같다면, 
        // k - 1 번째 값을 리턴(리스트는 0 부터 시작하니까)
        if(missingPosArr.size() >= k) {
            return missingPosArr.get(k - 1);
        }

        // 위 조건에 맞지 않는다면 현재 숫자 + (k - 비어있는 배열의 크기 - 1) 로 계산
        // -1 이 붙는 이유는 위에서 arr[i]의 다음값을 고려할 수 있도록 + 1을 했기 때문이다.
        number = number + (k - missingPosArr.size() - 1);

        // 얻게된 number를 리턴
        return number;
    }

    // 이진 탐색을 활용한 풀이
    // https://leetcode.com/problems/kth-missing-positive-number/solutions/3262334/day-65-binary-seach-o-logn-time-o-1-space-easiest-beginner-friendly-sol/ 참고
    public int findKthPositive_binary_search(int[] arr, int k) {
        int n = arr.length;
        int start = 0, end = n-1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] - (mid + 1) < k)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return start + k;
    }
}
