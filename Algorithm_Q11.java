public class Algorithm_Q11 {
    /*
     * 해당 문제는 DP를 통해 풀어야 하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/longest-valid-parentheses/
     * 해당 DP 문제중에서도 괄호의 쌍이 맞는 sub string을 구하는 문제로 stack, dp, 그리고 문장의 특징을 통해 풀이하는 방법등이 존재한다.
     * 위 문제는 어떻게 DP로 문제가 정리되는지 등에 유의하며 내용을 확인하자
     */ 

     // DP를 사용한 풀이
     public int longestValidParentheses_for_DP(String s) {
        // DP를 이용한 풀이
        int answer = 0;
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length];

        for(int i = 1; i < dp.length; i++) {
            if(arr[i] == ')') {
                if(arr[i - 1] == '(') {
                    if(i > 2) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 0 + 2;
                    }
                } else if((i - dp[i - 1]) > 0 && arr[i - dp[i - 1] - 1] == '('){
                    // 만약 현재 위치 - 직전 위치에서의 dp 값 즉, substring 값이 0보다 크거나,
                    // 현재위치 - 직전 dp 값 즉 substring 값 - 1 위치가 쌍이 되는 괄호라면
                    if((i - dp[i - 1]) >= 2) {
                        // 현재위치 - 직전 위치에서의 dp 값이 2 이상일 때
                        dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                    } else {
                        // 2 미만일 때
                        dp[i] = dp[i - 1] + 2;
                    }
                }
                // 현재 위치에 저장해 둔 subString 값과, 지금까지 확인된 값 중 가장 큰 subString 값으로 저장한
                // answer 값을 비교해서 더 큰걸로 answer를 갱신
                answer = Math.max(answer, dp[i]);
            }
        }

        return answer;
    }

    /*
     * 문자열을 양 끝점에서 순회하면서 짝이 맞는 부분 문자열의 길이를 찾는 방법이다.
     * 아래 주석은 처음에 문제를 풀때 고민했던 부분이며, 이 내용처럼 string을 순회해서 풀이된 내용을 설명을 보고 이후 아래 구현을 진행 하였다.
     */
    public int longestValidParentheses_for_string_traverse(String s) {
        // 이 문제에 대해 해결할 때, 1차적으로는 아래와 같이 생각 했다.
        // 일단 S를 char의 배열로 만들어 줄 때
        // arr[i] == 0 => )) 였다는 의미
        // -> 만약 이때 앞에 start_val 이 숫자가 있다면, 이거는 -2 만큼 하고, string 길이를 4로 더한다.
        // -> 만약 start_val이 0이라면, 유요한 값이 아니기 때문에, 그냥 넘어간다.
        // -> 만약 start_val이 1 이면, string 길이를 2로 더하고, 여기서 start_val은 0 으로 처리하고,
        // string 길이도 0으로 잡는다 => sub string 길이 초기화
        // arr[i] != 0 => )(((( => 와 같은 의미
        // 이때는 start_val이 0이면, 그냥 해당 원소의 string 길이를 start_val로 받고, answer 갱신 한번 돌리고, string 길이 초기화
        // start_val이 1이면, 일단 string 길이 2 더하고, 나머지는 start_val로 추가
        // 만약 2 이상이면, 일단 string 길이에 2 더하고, answer 갱신 후 전체 초기화

        // 여기서 한발 더 나아가서 왼쪽과 오른쪽 끝에서 순회를 진행하면서 (는 left가 올라가고, )는 right가 올라가도록
        // 반복문 순회를 돌아 짝이 맞춰지면 진행하는 방식으로 처리했다.

        if(s.equals("")) return 0;

        int answer = Integer.MIN_VALUE;
        int subStringLen = 0;
        int left = 0;
        int right = 0;
        char[] arr = s.toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(') {
                left += 1;
            } else {
                right += 1;
            }

            if(left == right) {
                // 왼쪽 괄호 갯수 기준
                subStringLen = Math.max(subStringLen, left * 2);
            } else if(left < right) {
                // 조건을 만족하지 못하기 때문에 초기화
                left = 0;
                right = 0;
            }
        }

        answer = Math.max(answer, subStringLen);

        left = 0;
        right = 0;
        subStringLen = 0;

        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] == '(') {
                left += 1;
            } else {
                right += 1;
            }

            if(right == left) {
                // 오른쪽 괄호 갯수 기준
                subStringLen = Math.max(subStringLen, right *2);
            } else if(right < left) {
                // 조건을 만족하지 못하기 때문에 초기화
                right = 0;
                left = 0;
            }
        }

        answer = Math.max(answer, subStringLen);

        return answer;
    }
}
