import java.util.*;

public class Algorithm_Q61 {
    /*
     * 해당 문제는 stack을 사용하면 쉽게 해결할 수 있는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int evalRPN(String[] tokens) {
        // 문제의 연산을 해결하기 위해 숫자를 담는 stack을 하나 사용한다.
        // 해당 스택에서 값을 뽑으면 만약 token이 연산자가 나오게 되면 pop을 2번 해서
        // 2번째 pop 한 값 (연산자) 1번째 pop 한 값으로 계산한다.
        // 왜냐하면 가장 마지막에 push한 값은 연산자 다음에 오는 숫자이기 때문이다.
        Stack<String> numbers = new Stack<>();
        int answer = 0;
        int a = 0;
        int b = 0;
        for(int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")) {
                a = Integer.parseInt(numbers.pop());
                b = Integer.parseInt(numbers.pop());
                numbers.push(String.valueOf(b + a));
            } else if(tokens[i].equals("-")) {
                a = Integer.parseInt(numbers.pop());
                b = Integer.parseInt(numbers.pop());
                numbers.push(String.valueOf(b - a));
            } else if(tokens[i].equals("*")) {
                a = Integer.parseInt(numbers.pop());
                b = Integer.parseInt(numbers.pop());
                numbers.push(String.valueOf(b * a));
            } else if(tokens[i].equals("/")) {
                a = Integer.parseInt(numbers.pop());
                b = Integer.parseInt(numbers.pop());
                numbers.push(String.valueOf(b / a));
            } else {
                numbers.push(tokens[i]);
            }
        }

        answer = Integer.parseInt(numbers.pop());

        return answer;
    }
}
