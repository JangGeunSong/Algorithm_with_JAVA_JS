import java.util.*;

public class Algorithm_Q33 {
    /*
     * 해당 문제는 어제에 이어 백트래킹 문제이다.
     * 백트래킹을 활용하면 된다는 점을 알게되면, 쉽게 문제의 접근 방법을 이해할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/generate-parentheses/
     * 풀이를 확인해보자.
     */  

    /*
     * 나의 풀이
     * 조건에 맞는 시나리오를 생각한 후 그 부분에 맞춰 논리를 진행 하였다.
     * 이런 문제 풀이에서 가장 중요한 부분은 어떻게 종료 조건까지 도달할 것인가를 고민하며 반복 하는 것이다.
     * 나는 여는 괄호와 닫는 괄호의 개수가 n개씩 있다고 가정하고, 사용하면 -1 해서 최종적으로 조건에 맞는
     * 상황일 때 여는 괄호와 닫는 괄호의 개수가 각각 0이 된 상태일 때에만 정답이 되도록 구현했다.
     */
    public List<String> generateParenthesis_mine(int n) {
        List<String> answer = new ArrayList<>();
        String str = "";

        parenthesisCombination(str, n, n, n, answer);

        return answer;
    }

    public void parenthesisCombination(String str, int n, int left, int right, List<String> answer) {
        // 만약 닫는 괄호의 수가 여는 괄호의 수 보다 적은 상태라면, 올바른 짝이 지어지지 않으므로,
        // 해당 경우에는 강제로 함수가 더 이상 진행되지 않도록 한다.
        if(right < left) {
            return;
        }

        // 위 종료 조건 이 외에는 여는 괄호의 수와 닫는 괄호의 수가 0이 되는 조건문에서 처리 되어야 하는
        // 로직이 다르기 때문에 각 조건에 따라 진행한다.
        if(left != 0 && right != 0) {
            // 여는 괄호 추가 후 조합 실행 함수 호출 (재귀)
            str = str + "(";
            parenthesisCombination(str, n, left - 1, right, answer);
            // 원상 복구 후 닫는 괄호 추가 후 조합 실행 함수 호출 (재귀)
            str = str.substring(0, str.length() - 1);
            str = str + ")";
            parenthesisCombination(str, n, left, right - 1, answer);
        } else if(left == 0 && right != 0) {
            // 만약 닫는 괄호의 수만 여분이 남았다면 전부 소모했을 때, 조건에 맞을 수 있으므로, 진행
            str = str + ")";
            parenthesisCombination(str, n, left, right - 1, answer);
        } else if(left != 0 && right == 0) {
            // 만약 닫는 괄호는 남은게 없는데 여는 괄호만 남았다면 답이 안되므로, 종료
            return;
        } else {
            // 위 제약들을 모두 넘어서서 여는 괄호와 닫는 괄호의 수가 다 0이 되었다면 정답에 삽입
            if(str.length() == n * 2) {
                answer.add(new String(str));
            }
        }
    }


    /*
     * 간단한 풀이 코드
     * 이 코드는 같은 백 트래킹이지만, 더 간결하게 처리된 버전의 코드이다.
     * 아래 로직에서는 나와는 달리 여는 괄호와 닫는 괄호가 0개에서 시작해 각 n 까지 도달하는 것을 감안하여 진행하였다.
     * 이때 핵심은 여는게 n 보다 작다면, 여는 괄호가 추가되도록 만들어 준 후 재귀가 돌고
     * 이 상태에서 닫는 괄호가 여는 괄호의 개수보다 작다면, 닫는 괄호도 추가하는 방식으로 재귀가 돌도록 설계했다.
     * 매우 간단하지만, 어 짧고 간결한 코드가 되었다.
     * 이와같은 간결한 풀이를 할 수 있도록 노력해보자.
     */
    public List<String> generateParenthesis_simpler_version(int n) {
        List<String> answer = new ArrayList<String>();
        
        if(n == 1) {
            answer.add("()");
            return answer;
        }
        
        createParenthesesPair(n, "", answer, 0, 0);
        
        return answer;
    }
    
    public void createParenthesesPair(int n, String bay, List<String> answer, int open, int close) {
        if(bay.length() == n * 2) {
            answer.add(bay);
            return;
        }
        
        if(open < n) {
            createParenthesesPair(n, bay + "(", answer, open + 1, close);
        }
        
        if(close < open) {
            createParenthesesPair(n, bay + ")", answer, open, close + 1);
        }
        
    }
}
