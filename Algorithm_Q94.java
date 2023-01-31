import java.util.*;

public class Algorithm_Q94 {
    /*
     * 이 문제는 DP와 문제 조건에 따른 결과값을 구하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/best-team-with-no-conflicts/
     * 풀이를 확인해보자.
     */

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int answer = Integer.MIN_VALUE;
        // entry is 0 index => player's age and 1 index => that player's score
        int[][] teamEntry = new int[n][2];
        int[] dp = new int[n];

        // 나이, 점수 2차원 배열 초기화
        for(int i = 0; i < n; i++) {
            teamEntry[i][0] = ages[i];
            teamEntry[i][1] = scores[i];
        }

        // 배열을 나이순 오름차순 -> 나이 같으면 점수 오름 차순 정렬 진행
        Arrays.sort(teamEntry, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 만약 나이가 같다면, 점수를 기준으로 오름차순 정렬 진행
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        dp[0] = teamEntry[0][1];

        // 나이순 & 점수순 으로 정렬 해 두었으므로, 나이 계산은 같냐 아니냐만 체크한다.
        for(int i = 1; i < n; i++) {
            int score = teamEntry[i][1];
            for(int idx = 0; idx < i; idx++) {
                if(teamEntry[i][1] >= teamEntry[idx][1]) {
                    // 0 ~ i 까지는 현재 선수보다 어리기 때문에 for문을 돌아서 처리한다.
                    // 이때 현재 선수의 점수보다 해당 선수의 점수가 크거나 같다면
                    // 과거 점수의 합 + 현재 선수의 점수 vs 현재 score 두개를 비교해 가장 큰 값을 받는다
                    // 이는 해당 선수가 과거 선수와 점수 비교를 할 때 합산이 가능하기 때문이다.
                    score = Math.max(score, dp[idx] + teamEntry[i][1]);
                }
                // 위 과정을 거쳐 얻은 값을 dp에 저장한다.
                dp[i] = score;
            }
        }

        // dp 중 가장 큰 값을 정답으로 리턴한다.
        for(int i = 0; i < n; i++) {
            answer = Math.max(dp[i], answer);
        }

        return answer;
    }
}
