public class Algorithm_Q37 {
    /*
     * 해당 문제는 단어 변환에 대한 횟수를 미리 기록해 두면서 정답을 찾아야 하는 DP 문제이다.
     * 그 과정까지 도달하는게 어렵기 때문에, 이 유형은 기억해 두는게 좋다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/edit-distance/
     * 풀이를 확인해보자.
     */  

    // 정답 코드
    public int minDistance(String word1, String word2) {
        // Edit distance 를 구하기 위해서는 표를 그려놓고 생각해 봐야 한다.
        // 이 연산에서 중요한 것은 h 와 "" 가 존재할때 h혹은 ""를 만들기 위한 연산 횟수를
        // 미리 기록하고 다음에 단어가 하나 더 늘어났을때, 그 기록을 계속해서 해 나가면
        // 끝에는 결과가 얻어진다는 점을 사용하여 문제를 풀면 된다.
        int answer = 0;
        int m = word1.length();
        int n = word2.length();
        int[][] costs = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            costs[i][0] = i;
        }

        for(int i = 0; i <= n; i++) {
            costs[0][i] = i;
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    costs[i + 1][j + 1] = costs[i][j]; 
                } else {
                    int a = costs[i][j];
                    int b = costs[i][j + 1];
                    int c = costs[i + 1][j];

                    costs[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    costs[i + 1][j + 1] += 1;
                }
            }
        }

        answer = costs[m][n];

        return answer;
    }

    // 틀린 나의 풀이.
    // 순회를 통해 해결하려 했으나, 그 과정이 쉽지 않았다.
    // 위의 원리를 이해하지 못해 진행할 수 없었던 부분이었다.
    public int minDistance_mine_wrong_answer(String word1, String word2) {
        // remove, replace, insert를 하는 조건은 무엇인가 확인해야 함.
        // remove, replace, insert는 모두 일치하지 않는 단어의 개수를 세고 그 단어 수 만큼
        // 연산 해야 하는 숫자이다.
        // 이는 왼쪽, 오른쪽 모두 순회해서 최대값을 구해야 하고, 그 이후 최대 문장의 길이 - 일치 단어수
        // 를 계산 하면 정답을 얻을 수 있다.
        int answer = 0;
        int idx = 0;
        int maxSize = 0;
        int matchedNumLeft = 0;
        int matchedNumRight = 0;
        char[] arr_word1 = word1.toCharArray();
        char[] arr_word2 = word2.toCharArray();
        
        if(arr_word1.length == 0) {
            return arr_word2.length;
        } else if(arr_word2.length == 0) {
            return arr_word1.length;
        }

        for(int i = 0; i < word1.length(); i++) {
            if(arr_word1[i] == arr_word2[idx]) {
                idx += 1;
                matchedNumLeft += 1;
            }
            if(idx == arr_word2.length) {
                break;
            }
        }

        idx = arr_word2.length - 1;

        for(int i = word1.length() - 1; i >= 0; i--) {
            if(arr_word1[i] == arr_word2[idx]) {
                matchedNumRight += 1;
                idx -= 1;
            }
            if(idx == -1) {
                break;
            }
        }

        maxSize = Math.max(word1.length(), word2.length());

        if(matchedNumLeft > matchedNumRight) {
            answer = maxSize - matchedNumLeft;
        } else {
            answer = maxSize - matchedNumRight;
        }

        return answer;
    }
}