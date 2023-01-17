public class Algorithm_Q85 {
    /*
     * DP 문제이다.
     * 오랜만의 DP 문제로 일반적인 memozation 문제인데 dp 배열까지는 필요 없는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/flip-string-to-monotone-increasing/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int minFlipsMonoIncr(String s) {
        int answer = 0;
        int numOfOne = 0;
        char[] arr = s.toCharArray();

        // 1과 0의 개수만 세면서 언제 가장 작은 숫자로 뒤집으면 되는지 체크할 것.
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '1') {
                // 만약 1이 나온다면, 일단 1의 개수만 세고 pass
                numOfOne += 1;
            } else {
                // 만약 0이 나온다면 아래와 같은 연산을 한다.
                // 1. 일단 0000 일떄 가장 마지막 0은 뒤집어도 된다. 즉, 현재 뒤집어야 할 가장 작은 숫자 + 1을 해도 된다.
                // 2. 1의 개수가 뒤에 있었다면 이 갯수가 뒤집어야할 갯수일 수 있다.
                // 1,2 중 가장 작은 값을 1개 고르면, 그게 뒤집어야 할 숫자의 갯수다.
                // 예를 들어 00110 일때 마지막 0에 도달하면
                // 일단 1이 처음 나온 시점부터 1은 2개가 되어있고, 아직 답은 0이다.
                // 여기서 0이 다시 등장 할때에는 맨 마지막꺼 뒤집기 vs 2개 뒤집기가 되서 답이 1이 된다.
                // 이런 방식으로 해서 가장 작은 값을 구하면 된다.
                // ex 0001101010101010110 일때
                // 00011 0(1) 10(2) 10(3) 10(4) 10(5) 10(6) 110(6 + 1 vs 9 ==> 7) 따라서 이 예시는 답이 7
                answer = Math.min(answer + 1, numOfOne);
            }
        }

        return answer;
    }
}
