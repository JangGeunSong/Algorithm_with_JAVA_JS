import java.util.*;

public class Algorithm_Q142 {
    /*
     * 이 문제는 stack을 활용한 문제다.
     * 처음보면 당황스럽지만 이면에는 쉬운 원리가 숨어 있으니 잘 기억하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/simplify-path/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public String simplifyPath(String path) {
        // 각 경로 값을 저장하기 위한 stack 변수 선언
        Stack<String> stack = new Stack<>();
        String answer = "";
        // / 를 기준으로 주어진 문자열을 잘라낸다.
        String[] paths = path.split("/");

        // 잘라낸 문자열 배열을 순회하면서 다음을 체크
        for(String ph : paths) {
            // 만약 .. 이라면 직전에 들어간 경로의 상위 경로로 가는 것이기 때문에 stack에서 peek에 있는 값을 지운다.
            if(ph.equals("..")) {
                // 이때 stack이 비어있다면 그냥 지나간다.
                if(stack.isEmpty()) {
                    continue;
                } else {
                    stack.pop();
                }
            } else if(ph.equals(".") || ph.equals("")) {
                // 만약 . 이거나 공백 "" 이라면 해당 값은 의미가 없는 값이기 때문에 패스한다.
                continue;
            } else {
                // 이 외에는 의미있는 경로 값이기 때문에 push 진행
                stack.push(ph);
            }
        }

        // stack이 모두 빌때 까지 진행
        while(!stack.isEmpty()) {
            // 현재 stack의 peek값을 pop 해서 가져온다.
            String str = stack.pop();
            // null 이거나 "" 이 아니라면 루트 경로에서 부터 올라가므로, / + str + answer 형태로 계산한다.
            if(str.equals(null) || str.equals("")) {
                continue;
            } else {
                answer = "/" + str + answer;
            }
        }

        // 만약 "" 이 answer가 되었다면 이는 루트 경로에 있음을 의미하므로, / 를 담아준다.
        if(answer.equals("")) {
            answer = "/";
        }

        return answer;
    }
}
