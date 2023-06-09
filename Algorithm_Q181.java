public class Algorithm_Q181 {
    /*
     * 이 문제는 이진 탐색으로 풀어도 되고, 그냥 완전 탐색해서 풀어도 되는 문제다.
     * 이진 탐색과 완전 탐색 2가지 버전으로 모두 풀어 보았다. 참고하자.
     * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
     * 풀이를 확인해보자.
     */

    // 이진 탐색을 통한 풀이 O(nlogn)
    public char nextGreatestLetter_for_binary_search(char[] letters, char target) {
        // 이진 탐색 계산하기
        char answer = letters[0];
        // 이진 탐색은 양 끝점과 그 중점을 활용해 절반씩 검색 범위를 줄여 나가는 테크닉이다.
        // 이를 고려해 3개의 변수를 선언해 풀이에 활용한다.
        int left = 0;
        int right = letters.length;
        int mid = 0;

        // 양 끝점이 교차하기 전 까지 계산을 진행
        while(left < right) {
            // 양 끝점의 중점을 잡고
            mid = (left + right) / 2;

            // 만약 중점이 이 문제에서는 해당 배열의 문자이다.
            // 이 문자와 target의 뺄셈을 진행할 때, 해당 문자가 더 크다면 0보다 큰 숫자가 나올 것이다.
            // 이러면 해당 문자의 오른쪽 문자는 탐색에서 필요가 없으므로, right를 mid로 갱신한다.
            // 문제가 가장 작은 그러나 사전 순서로 쳤을 때 target 보다 큰 문자를 찾기 때문이다.
            if(letters[mid] - target > 0) {
                right = mid;
            } else {
                // 만약 해당 문자가 target과 같거나 target 보다 사전 순서상 앞에 오는 문자라면
                // 검색 범위를 left 즉 왼쪽 끝에서 부터 줄여 나가야 한다.
                // 이떄 주의할 점은 mid 그 자체도 정답이 될 수 없는 상황이기 때문에 + 1 을 하여 탐색 범위를 줄인다.
                left = mid + 1;
            }
        }
        
        // 만약 left 가 배열의 길이보다 더 커진 상황일 때에는 답이 없는 것이기 때문에 첫번째 문자를 리턴한다.
        if(left >= letters.length) {
            return letters[0];
        }

        // 그게 아니라면 left에 있는 문자를 리턴한다.
        answer = letters[left];

        return answer;
    }

    // 완전 탐색을 통한 풀이 O(n)
    public char nextGreatestLetter_for_brute_force(char[] letters, char target) {
        char answer = letters[0];

        for(char c : letters) {
            // 처음으로 target 보다 더 큰 값이 나타난다면 그 값을 answer로 처리하고 종료한다.
            if(target - c < 0) {
                answer = c;
                break;
            }
        }

        return answer;
    }
}
