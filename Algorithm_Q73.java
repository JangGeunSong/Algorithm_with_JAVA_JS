public class Algorithm_Q73 {
    /*
     * 문자열 처리 문제이다. 매우 쉽지만 솔직히 문제를 잘 읽어야 하는 부분이 있어 이를 놓치면 틀리기 쉽다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/detect-capital/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public boolean detectCapitalUse(String word) {
        // char array로 변경한 후 전체를 순회 진행
        char[] wordCharArr = word.toCharArray();
        // 대문자의 갯수를 세는 변수 선언
        int numOfUppercaseLetter = 0;
        // 정답 변수
        boolean answer = false;
        // 첫 문자가 대문자인지 여부 체크
        boolean isFirstIsCapital = false;

        // 모든 문자에 대해 순회를 진행
        for(int i = 0; i < wordCharArr.length; i++) {
            if(i == 0) {
                // 첫번째 문자에 대해서
                if(wordCharArr[i] >= 'A' && wordCharArr[i] <= 'Z') {
                    // 대문자면 대문자의 갯수를 1 더하고 다음으로
                    numOfUppercaseLetter += 1;
                    isFirstIsCapital = true;
                } else {
                    // 대문자가 아니라면 일단 첫 문자가 대문자가 아닌 것으로 리턴
                    isFirstIsCapital = false;
                }
            } else {
                // 첫 문자가 아닌 나머지에 대해서
                if(wordCharArr[i] >= 'A' && wordCharArr[i] <= 'Z') {
                    // 대문자라면 일단 대문자 갯수를 1 더한다
                    numOfUppercaseLetter += 1;
                }
            }
        }

        // 정답 판별 시기
        if(numOfUppercaseLetter == 1) {
            // 만약 대문자의 갯수가 1개일 때 
            if(isFirstIsCapital) {
                // 첫 문자가 대문자면 true
                answer = true;
            } else {
                // 아니면 false
                answer = false;
            }
        } else if(numOfUppercaseLetter == wordCharArr.length || numOfUppercaseLetter == 0) {
            // 만약 모든 문자가 대문자거나 어떠한 문자도 대문자가 아닐 때
            answer = true;
        } else {
            // 이 외에는 false 처리
            answer = false;
        }

        return answer;
    }
}
