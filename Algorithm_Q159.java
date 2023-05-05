public class Algorithm_Q159 {
    /*
     * 이 문제는 sliding window를 활용해 풀이하는 문제다.
     * 간단하게 풀이에 성공했으므로, 풀이를 확인해보자.
     * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
     * 풀이를 확인해보자.
     */

    // sliding window를 활용해 풀이한 코드
    public int maxVowels(String s, int k) {
        // 부 문자열은 해당 시점 ~ 해당 시점 + k 까지의 문자열을 이야기 한다.
        // 즉 이때 갖고 있는 char들이 무엇인지를 배열에 저장하고 있다면, 이를 계산해 최악 2N의 순회로 마무리 지을 수 있다.
        int answer = 0;
        int currSum = 0;
        // 문자들의 갯수를 저장할 배열
        int[] charArr = new int[26];

        // K 길이까지 일자로 진행된 문자열에서 각 문자들의 숫자를 센다.
        for(int i = 0; i < k; i++) {
            charArr[s.charAt(i) - 'a'] += 1;
        }

        // 모음들의 갯수만 세어 합산
        currSum = charArr['a' - 'a'] + charArr['e' - 'a'] + charArr['i' - 'a'] + charArr['o' - 'a'] + charArr['u' - 'a'];

        // 우선 답변에 최대값을 갱신
        answer = Math.max(currSum, answer);

        // 0 ~ k - 1 번째 까지 게산이 끝났으므로, 이제 K 부터 시작하면서 맨 끝에 문자를 K 번째부터 받아서 문자 갯수를
        // 계산하고, i - k 번째는 이 scope에서 벗어났으므로, 그 위치의 문자의 갯수를 1 줄여준다.
        // 이런 과정을 거친 후 다시 현재 상태에서의 문자들의 갯수를 계산한 후 answer 즉 최대값과 비교해 답을 구해본다.
        for(int i = k; i < s.length(); i++) {
            charArr[s.charAt(i - k) - 'a'] -= 1;
            charArr[s.charAt(i) - 'a'] += 1;

            currSum = charArr['a' - 'a'] + charArr['e' - 'a'] + charArr['i' - 'a'] + charArr['o' - 'a'] + charArr['u' - 'a'];
            answer = Math.max(currSum, answer);
        }

        // 이렇게 얻어낼 max 값이 최대치이므로, 이를 답으로 리턴한다.
        return answer;
    }
}
