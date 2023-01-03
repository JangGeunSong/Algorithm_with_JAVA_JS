public class Algorithm_Q74 {
    /*
     * 문자열 처리 문제이다. 배열로 원하는 형태의 재 정의를 한 후 풀어야 하는 문제로 잘 생각해 봐야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/delete-columns-to-make-sorted/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        // 열 형태의 문자열 배열을 새로 만들어야 하므로, 
        // 문자열의 길이 => 문자 배열의 개수
        // 문자 배열의 개수 => 신규 문자 배열의 크기 로 나타낼 수 있다.
        char[][] charArr = new char[m][n];
        char tmp = 'a';
        int answer = 0;

        // 새로운 문자 배열을 기존 배열을 바탕으로 생성
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                charArr[j][i] = strs[i].charAt(j);
            }
        }

        // 문자 배열을 돌면서 사전순으로 정렬 되어 있는지 체크
        for(int i = 0; i < m; i++) {
            tmp = charArr[i][0];
            for(int j = 1; j < n; j++) {
                // 사전순의 판별은 아스키 문자의 성질을 사용해서 'a' 가 그 다음 문자들보다 작은 성질을 사용한다.
                if(tmp > charArr[i][j]) {
                    // 만약 사전순이 아니라면 정답에 1 추가 후 내장 루프를 break
                    answer += 1;
                    break;
                } else {
                    // 그 외의 경우라면 사전순으로 배열 되어 있으므로 tmp에 현재 문자를 담고 다음으로 진행
                    tmp = charArr[i][j];
                }
            }
        }

        return answer;
    }
    
}
